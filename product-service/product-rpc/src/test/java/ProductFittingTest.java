import net.realme.mall.product.Application;
import net.realme.mall.product.domain.ProductFittingDto;
import net.realme.mall.product.facade.ProductFittingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 91000156 on 2018/9/18 11:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductFittingTest {

    @Autowired
    private ProductFittingService productFittingService;

    @Test
    public void batchAddTest(){
        Integer skuId = 123;
        List<Integer> fittingIdList = new ArrayList<>();
        fittingIdList.add(1234);
        fittingIdList.add(12345);
        fittingIdList.add(12355);
        productFittingService.addProductFitting(skuId, fittingIdList);
    }

    @Test
    public void testFittingCount(){
        Integer skuId = 1234;
        int count = productFittingService.getFittingCountByMainSkuId(skuId);
    }

    @Test
    public void testFittingDtoById(){
        Integer mainSkuId = 123;
        Integer partSkuId = 12345;
        ProductFittingDto fittingDto = productFittingService.getFittingDtoById(mainSkuId,partSkuId);
    }

    @Test
    public void testFittingIds(){
        Integer mainSkuId = 151;
        List<Integer> list = productFittingService.getFittingIdsByMainSkuId(mainSkuId);
    }

    @Test
    public void testDelete(){
        Integer mainId = 123;
        Integer partId = null;
        productFittingService.deleteFittingInfo(mainId,partId);
    }
}
