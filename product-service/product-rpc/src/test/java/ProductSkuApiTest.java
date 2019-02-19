import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.product.Application;
import net.realme.mall.product.domain.SkuListQuery;
import net.realme.mall.product.domain.request.ProductSkuDto;
import net.realme.mall.product.domain.request.ProductSkuAttributeRequest;
import net.realme.mall.product.domain.request.SpuInfoOnSkuRequest;
import net.realme.mall.product.domain.response.ProductSkuResponse;
import net.realme.mall.product.domain.response.SelSkuAttrGroupResponse;
import net.realme.mall.product.domain.response.GetSkuAttrGroupResponse;
import net.realme.mall.product.facade.ProductSkuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 91000156 on 2018/8/29 14:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductSkuApiTest {

    @Autowired
    private ProductSkuService productSkuService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 添加SKU信息
    @Test
    public void addSkuInfo(){
        ProductSkuDto productSkuDto = new ProductSkuDto();
        productSkuDto.setProductId(33);
        productSkuDto.setPrice(new BigDecimal(99.9));
        productSkuDto.setCountdownWithin("1");
        productSkuDto.setErpCode("abc-realme");
        productSkuDto.setMobilePackImage("wwww.image.com");
        productSkuDto.setProductName("realme2");
        productSkuDto.setSkuName("22oppo");
        List<ProductSkuAttributeRequest> skuAttributeRequests = new ArrayList<>();
        ProductSkuAttributeRequest p = new ProductSkuAttributeRequest();
        p.setAttrId("1");
        p.setAttrValId("1");
        skuAttributeRequests.add(p);
        ProductSkuAttributeRequest pp = new ProductSkuAttributeRequest();
        pp.setAttrId("2");
        pp.setAttrValId("1");
        skuAttributeRequests.add(pp);

        productSkuDto.setSelOption(skuAttributeRequests);
        productSkuDto.setPackHeight(22.22);
        productSkuDto.setPackWeight(33.33);
        productSkuDto.setPackLength(11.11);
        productSkuDto.setPackWidth(0.94);
        productSkuDto.setTimeZone("UTF-8");
        productSkuDto.setUserDefinedUrl("www.baidu.c");
        productSkuDto.setSubTitle("aaa");
        productSkuDto.setSeoKeywords("a b n");
        productSkuDto.setShortDesc("g");
        productSkuService.addSkuInfo(productSkuDto);
        logger.info("===");
    }

    @Test
    public void getDefaultSkuAttrGroup() {
        Integer productId=33;
        ResultT<GetSkuAttrGroupResponse> result = productSkuService.getDefaultSkuAttrGroup(productId);
        logger.info("===");
    }

    @Test
    public void getSelSkuAttrGroup() {
        Integer productId=33;
        Integer skuId = 138;
        ResultT<SelSkuAttrGroupResponse> result = productSkuService.getSelSkuAttrGroup(productId,skuId);
        logger.info("===");
    }

    // 根据SKU-ID获取SKU信息
    @Test
    public void  getSkuInfoById(){
        Integer skuId = 140;
        ResultT<ProductSkuResponse> skuResponse = productSkuService.getSkuInfoById(skuId);
        logger.info("===");
    }

    @Test
    public void deleteSkuInfo(){
        Integer skuId =138;
        int userId=63;
        ResultT<Integer> ao = productSkuService.deleteSkuInfo(skuId,userId);
        logger.info("====");
    }

    // 更新商品SKU的上架、下架状态信息
    @Test
    public void updateSkuStatus(){
        Integer skuId =144;
        byte shelfStatus = 1;
        int userId=999;
        ResultT<Integer> ao = productSkuService.updateSkuStatus(skuId,shelfStatus,userId);
        logger.info("====");
    }

    // 更新商品SKU的信息
    @Test
    public void  updateSkuInfo(){
        ProductSkuDto productSkuDto = new ProductSkuDto();
        productSkuDto.setSkuId(138);
        productSkuDto.setProductId(33);
        productSkuDto.setPrice(new BigDecimal(66666333.12));
        productSkuDto.setCountdownWithin("1");
        productSkuDto.setErpCode("xxxx++!!!!!");
        productSkuDto.setMobilePackImage("wwww.image.com");
        productSkuDto.setSkuName("6687");
        productSkuDto.setProductName("lalal");
        List<ProductSkuAttributeRequest> skuAttributeRequests = new ArrayList<>();
        ProductSkuAttributeRequest p = new ProductSkuAttributeRequest();
        p.setAttrId("1");
        p.setAttrValId("2");
        skuAttributeRequests.add(p);
        ProductSkuAttributeRequest pp = new ProductSkuAttributeRequest();
        pp.setAttrId("2");
        pp.setAttrValId("2");
        skuAttributeRequests.add(pp);

        productSkuDto.setSelOption(skuAttributeRequests);
        productSkuDto.setPackHeight(22.21112);
        productSkuDto.setPackWeight(33.3311);
        productSkuDto.setPackLength(11.1111);
        productSkuDto.setPackWidth(0.9411);
        productSkuDto.setTimeZone("UTF-8");
        productSkuDto.setUserDefinedUrl("www.baidu.c");
        productSkuDto.setSubTitle("fdgdgdgdgdg");
        productSkuDto.setSeoKeywords("xzczczczczc");
        productSkuDto.setShortDesc("g41615615615616165156156156");
        productSkuService.updateSkuInfo(productSkuDto);
        logger.info("===");
    }
//
    // 获取商品的SKU信息
    @Test
   public void getSkuList(){
        SkuListQuery query = new SkuListQuery();
//        query.setBrandCode("");
//        query.setCategoryCode("");
//        query.setErpCode("");
        //query.setProductId("sadads");
        query.setProductName("la");
//        query.setSkuId("");
        //query.setSkuName("la");
        ResultT<ResultList<ProductSkuResponse>> resultListResultT = productSkuService.getSkuList(query);
        logger.info("===");
    }

    @Test
    public void editSpuInfo(){
        SpuInfoOnSkuRequest spuInfoOnSkuRequest = new SpuInfoOnSkuRequest();
        spuInfoOnSkuRequest.setItemSitesUrl("www.oppo.com");
        spuInfoOnSkuRequest.setSkuId(138);
        spuInfoOnSkuRequest.setSpecWeights("[1,3,4]");
        ResultT<Integer> resultListResultT = productSkuService.editSpuAttrOnSkuPage(spuInfoOnSkuRequest,15);
        logger.info("===");
    }

    @Test
    public void batchAddFittingTest(){
        Integer skuId = 123;
        List<Integer> fittingIdList = new ArrayList<>();
        fittingIdList.add(1234);
        fittingIdList.add(12345);
        fittingIdList.add(12355);
        fittingIdList.add(1233355);
        productSkuService.editSkuFitting(skuId,fittingIdList);
    }

    @Test
    public void testUriExists(){
        String siteCode = "in";
        String userDefinedUri = "123";
        boolean flg = productSkuService.isUserDefinedUriExist(siteCode,userDefinedUri).getData();
        logger.info(""+flg);
    }
}
