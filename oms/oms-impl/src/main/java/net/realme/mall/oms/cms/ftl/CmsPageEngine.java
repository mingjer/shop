package net.realme.mall.oms.cms.ftl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.realme.mall.oms.consts.PageConst;
import net.realme.mall.oms.domain.cms.CmsPageDto;
import net.realme.mall.oms.exception.OmsException;
import net.realme.mall.oms.util.PathManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: ftl
 *
 * @author 91000044
 * @date 2018/8/1 11:45
 */
@Component
public class CmsPageEngine {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Configuration configuration;

    @Autowired
    private PathManager pathManager;

    private ModelMap root;

    @PostConstruct
    public void init() {
        root = new ModelMap();
        root.addAttribute("cdnUrlPrefix", pathManager.getPageCdnPrefix());
    }

    /**
     * 分三阶段渲染，先解析section，再解析variable，最后解析t9n
     *
     * @param cmsPageDto
     * @param siteCode
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public String render(CmsPageDto cmsPageDto, String siteCode) throws IOException, TemplateException {
        root.addAttribute("siteCode", siteCode);
        root.addAttribute("siteUrlPrefix", pathManager.getSitePrefix(siteCode));
        root.addAttribute("pageUri", cmsPageDto.getUri());
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
     * 生成页面
     *
     * @param cmsPageDto
     * @param siteCode
     * @throws IOException
     * @throws TemplateException
     */
    public String generate(CmsPageDto cmsPageDto, String siteCode, Byte domainType) throws IOException, TemplateException, OmsException {
        String html = render(cmsPageDto, siteCode);
        String uri = cmsPageDto.getUri();
        if("/".equals(uri)) {
            uri = "index.html";
        } else if (uri.endsWith("/")) {
            uri = uri + "index.html";
        } else if (!uri.endsWith(".html")) {
            uri = uri + ".html";
        }
        Path htmlFile = null;
        if (domainType == PageConst.DomainType.DOMAIN_BUY) {
            htmlFile = Paths.get(pathManager.getShoppingSavePageDir(), (String) root.get("siteCode"), uri);
        } else {
            htmlFile = Paths.get(pathManager.getCmsPageSaveDir(), (String) root.get("siteCode"), uri);
        }
        logger.info("htmlFile [{}]",htmlFile.toString());
        if (!htmlFile.getParent().toFile().exists()) {
            Files.createDirectories(htmlFile.getParent());
        }
        try (RandomAccessFile stream = new RandomAccessFile(htmlFile.toString(), "rw")) {
            try (FileChannel channel = stream.getChannel()){
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
}
