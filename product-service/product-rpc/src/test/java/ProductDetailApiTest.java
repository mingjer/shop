import net.realme.mall.product.Application;
import net.realme.mall.product.dao.ProductDetailPageMapper;
import net.realme.mall.product.domain.response.ProductDetailFittings;
import net.realme.mall.product.domain.response.ProductDetailResponse;
import net.realme.mall.product.domain.response.SkuLiveStatusResponse;
import net.realme.mall.product.facade.ProductDetailService;
import net.realme.mall.product.model.SkuDetailUrisView;
import net.realme.mall.product.model.ProductAttributesView;
import net.realme.mall.product.model.SkuFittingView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by 91000156 on 2018/9/5 14:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductDetailApiTest {

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private ProductDetailPageMapper detailPageMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testDetailResponse(){
        int skuId = 140;
        ProductDetailResponse productDetailResponse = productDetailService.getDetailPageInfo(skuId);
        logger.info("====");
    }

    @Test
    public void testPriceSymbol(){
        String siteCode = "China";
        String abc = detailPageMapper.getPriceSymbolMapper(siteCode);
        logger.info("====");
    }

    @Test
    public void testAttrGroup(){
        int productId = 38;
        List<ProductAttributesView> skuAttrGroupInfo = detailPageMapper.getSkuAttrListByType(productId,1);
        logger.info("====");
    }

    @Test
    public void testJumpUri(){
        int productId = 38;
        List<SkuDetailUrisView> skuAttrGroupInfo = detailPageMapper.getJumpUriInfo(productId);
        logger.info("====");
    }

    @Test
    public void testDetailStatus(){
        int skuId = 140;
        SkuLiveStatusResponse liveStatus = productDetailService.getSkuLiveStatusInfo(skuId);
        logger.info("====");
    }

    @Test
    public void testDetailFitting(){
        int skuId = 151;
        List<ProductDetailFittings> list = productDetailService.getSkuFittings(skuId);
    }
}
