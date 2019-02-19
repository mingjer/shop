package net.realme.mall.oms.cms.ftl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.realme.mall.oms.domain.cms.CmsPageDto;
import net.realme.mall.oms.exception.OmsException;
import net.realme.mall.oms.util.PathManager;
import net.realme.mall.product.domain.response.ProductDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by 91000156 on 2018/9/6 17:09
 */
@Component
public class ProductDetailPageEngine {

    @Autowired
    private Configuration configuration;

    @Autowired
    private PathManager pathManager;

    /**
     * 完成商品详情页面的渲染工作
     *
     * @param cmsPageDto
     * @param siteCode
     * @param root
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public String detailPageRender(CmsPageDto cmsPageDto, String siteCode, ModelMap root) throws IOException, TemplateException {
        //phase1开始，解析section和最外层的variable
        Template template = new Template(cmsPageDto.getName(), new StringReader(cmsPageDto.getContent()), configuration);
        StringWriter stringWriter = new StringWriter();
        template.process(root, stringWriter);

        //phase1解析完后，开始解析phase1中section内嵌的variable (这时整个template再无sec指令)
        String phase1Result = stringWriter.toString();
        stringWriter = new StringWriter();
        template = new Template(cmsPageDto.getName(), new StringReader(phase1Result), configuration);
        template.process(root, stringWriter);
        String phase2Result = stringWriter.toString();

        //phase2解析完后，最后再解析phase2中variable内嵌的t9n (这时整个template再无var指令)
        stringWriter = new StringWriter();
        template = new Template(cmsPageDto.getName(), new StringReader(phase2Result), configuration);
        template.process(root, stringWriter);
        return stringWriter.toString();
    }

    /**
     * 生成HTML文件并以文件的形式保存到服务器上
     *
     * @param cmsPageDto
     * @param detailResponse
     * @param modelMap
     * @return
     * @throws IOException
     * @throws TemplateException
     * @throws OmsException
     */
    public String detailPageDeploy(CmsPageDto cmsPageDto, ProductDetailResponse detailResponse, ModelMap modelMap) throws IOException, TemplateException, OmsException {
        // 渲染得到结果页面
        String html = detailPageRender(cmsPageDto, detailResponse.getSiteCode(), modelMap);
        // 获取商品详情页面的自定义uri
        String skuUri = detailResponse.getUserDefinedUrl();
        skuUri = convertUri(skuUri);
        // 存放路径：定义+站点名称+SPU-ID+文件名称
        Path htmlFile = Paths.get(pathManager.getShoppingSavePageDir(), detailResponse.getSiteCode(), skuUri);
        // 文件不存在，则创建
        if (!htmlFile.getParent().toFile().exists()) {
            Files.createDirectories(htmlFile.getParent());
        }
        try (RandomAccessFile stream = new RandomAccessFile(htmlFile.toString(), "rw")) {
            try (FileChannel channel = stream.getChannel()) {
                try (FileLock lock = channel.tryLock()) {
                    if (lock != null) {
                        stream.setLength(0);
                        stream.write(html.getBytes("UTF-8"));
                    }
                }
            }
        }
        return html;
    }

    /**
     * 商品下架后将对应的sku页面覆盖重写为下架页面
     *
     * @param siteCode
     * @param skuUri
     * @throws IOException
     * @throws OmsException
     */
    public String overWriteSkuPageContent(String siteCode, String skuUri) throws IOException, OmsException {
        // 获取商品异常页面的保存路径
        Path errPageHtmlFile = Paths.get(pathManager.getShoppingErrPageSaveDir(), siteCode, pathManager.getShoppingErrPageFileName());
        // 转化为正确格式
        skuUri = convertUri(skuUri);
        // 获取需要重写到的sku页面的保存路径
        Path skuPageHtmlFile = Paths.get(pathManager.getShoppingSavePageDir(), siteCode, skuUri);
        // 初始化返回值
        String errPageContent = "";
        // 第一次部署便置为已下架，则创建文件夹
        if (skuPageHtmlFile.getParent().toFile().exists()) {
            // 存在则先删除掉
            if(skuPageHtmlFile.toFile().exists()){
                // 删除后重新创建
                Files.delete(skuPageHtmlFile);
            }
        }else {
            Files.createDirectories(skuPageHtmlFile.getParent());
        }
        //  读取errPage文件
        try (RandomAccessFile errPageStream = new RandomAccessFile(errPageHtmlFile.toString(), "r");
             RandomAccessFile skuPageStream = new RandomAccessFile(skuPageHtmlFile.toString(), "rw")) {
            try (FileChannel errPageChannel = errPageStream.getChannel();
                 FileChannel skuPageChannel = skuPageStream.getChannel()) {
                // 读缓冲区
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                try (FileLock lock = skuPageChannel.tryLock()) {
                    if (lock != null) {
                        while (true) {
                            // pos=0,limit=capacity，作用是让channel从pos开始放数据
                            byteBuffer.clear();
                            int readCode = errPageChannel.read(byteBuffer);
                            // -1时读取结束
                            if (readCode == -1) {
                                break;
                            }
                            // 这两步相当于 buffer.flip()
                            byteBuffer.limit(byteBuffer.position());
                            byteBuffer.position(0);
                            // 让chanel写入pos - limit之间的数据
                            skuPageChannel.write(byteBuffer);
                        }
                    }
                }
                errPageContent = "off-line.html";
            }
        }
        return errPageContent;
    }

    // 删除sku后，删除对应的商品详情页面
    public void deleteSkuDetailPage(String skuUri, String siteCode) throws IOException, OmsException {
        // 获取商品详情页面的自定义uri
        skuUri = convertUri(skuUri);
        // 确定旧有商详页面存放的路径：定义+站点名称+SPU-ID+文件名称
        Path skuPageHtmlFile = Paths.get(pathManager.getShoppingSavePageDir(), siteCode, skuUri);
        // 先确定父级目录是否存在，不存在则肯定没有下属文件
        if (skuPageHtmlFile.getParent().toFile().exists()) {
            // 页面随着sku的删除而删除
            if (skuPageHtmlFile.toFile().exists()) {
                Files.delete(skuPageHtmlFile);
            }
        }
    }

    /**
     * 将uri转变为正确的形式
     *
     * @param uri
     * @return
     */
    public String convertUri(String uri) {
        if ("/".equals(uri)) {
            uri = "index.html";
        } else if (uri.endsWith("/")) {
            uri = uri + "index.html";
        } else if (!uri.endsWith(".html")) {
            uri = uri + ".html";
        }
        return uri;
    }
}
