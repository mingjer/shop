import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.product.Application;
import net.realme.mall.product.domain.ProductDto;
import net.realme.mall.product.domain.ProductListQuery;
import net.realme.mall.product.domain.request.ProductGetUpdDto;
import net.realme.mall.product.domain.request.SpuAttrUpdate;
import net.realme.mall.product.facade.ProductSpuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 91000156 on 2018/8/28 17:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductSpuApiTest {

    @Autowired
    private ProductSpuService productService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testAdd(){
        ProductDto productDto =new ProductDto();
        //productDto.setProductId(1);
        productDto.setBrandCode("bar test");
        productDto.setCategoryCode("cat test");
        productDto.setDescription("desc test");
        productDto.setName("realme 22");
        productDto.setSequence(1);
        productDto.setUpdatedTime(1312L);
        productDto.setShelfStatus((byte)0);
        productDto.setStatus((byte)0);
        HashMap<Integer, List<String>> options = new HashMap<>();
        List a = new ArrayList();
        a.add("霸王金");
        a.add("赤炎白");
        options.put(1,a);
        List b = new ArrayList();
        b.add("999G + 3G");
        b.add("888K + 3 K");
        options.put(2,b);
        productDto.setOptions(options);
        productDto.setStatus((byte)0);
        // 实物商品、虚拟商品
        productDto.setType((byte)1);
        productDto.setCreatedTime(System.currentTimeMillis());
        productDto.setCreatedBy(123333);
        ResultT<Integer> resultT = productService.add(productDto);
        logger.info("===================================="+resultT.getCode());
    }
    @Test
    public void testGet(){
        Integer productId = 40;
        ResultT<ProductGetUpdDto> productDtoResultT = productService.get(productId);
        logger.info("====================================" + productDtoResultT.getData().getProductId());
    }

    @Test
    public void testStatus(){
        Integer productId = 33;
        byte status = 0 ;
        int userId=6952;
        ResultT<Integer> productDtoResultT = productService.status(productId,status,userId);
        logger.info("====================================" + productDtoResultT.getCode());
    }

    @Test
    public void testList(){
        ProductListQuery productListQuery = new ProductListQuery();
//        productListQuery.setProductId();
        //productListQuery.setBrandCode("realme");
        productListQuery.setCategoryCode("手机-样机");
        //productListQuery.setProductName("realme 22");
        productListQuery.setPage(1);
        productListQuery.setLimit(10);
        ResultT<ResultList<ProductDto>> productDtoResultT = productService.list(productListQuery);
        logger.info("===");
    }

    @Test
    public void testUpdate(){
        ProductGetUpdDto productGetUpdDto =new ProductGetUpdDto();
        productGetUpdDto.setProductId(40);
        productGetUpdDto.setBrandCode("xxx99p");
        productGetUpdDto.setCategoryCode("cat test");
        productGetUpdDto.setDescription("desc test");
        productGetUpdDto.setName("realme 22");
        HashMap<Integer, List<SpuAttrUpdate>> options = new HashMap<>();
        List a = new ArrayList();
        SpuAttrUpdate spuAttrUpdate = new SpuAttrUpdate();
        spuAttrUpdate.setId(238);
        spuAttrUpdate.setAttrVal("霸金");
        a.add(spuAttrUpdate);
        SpuAttrUpdate spuAttrUpdate1 = new SpuAttrUpdate();
        spuAttrUpdate1.setId(239);
        spuAttrUpdate1.setAttrVal("走卒");
        a.add(spuAttrUpdate1);
        SpuAttrUpdate spuAttrUpdate2 = new SpuAttrUpdate();
        spuAttrUpdate2.setId(0);
        spuAttrUpdate2.setAttrVal("赤白");
        a.add(spuAttrUpdate2);
        options.put(1,a);
        List b = new ArrayList();
        SpuAttrUpdate spuAttrUpdate3 = new SpuAttrUpdate();
        spuAttrUpdate3.setId(241);
        spuAttrUpdate3.setAttrVal("99T+3M");
        b.add(spuAttrUpdate3);
        SpuAttrUpdate spuAttrUpdate4 = new SpuAttrUpdate();
        spuAttrUpdate4.setId(240);
        spuAttrUpdate4.setAttrVal("888K + 3 K");
        b.add(spuAttrUpdate4);
        options.put(2,b);
        productGetUpdDto.setOptions(options);
        productGetUpdDto.setStatus((byte)0);
        // 实物商品、虚拟商品
        productGetUpdDto.setType((byte)1);
        productGetUpdDto.setCreatedTime(System.currentTimeMillis());
        productGetUpdDto.setCreatedBy(123333);
        ResultT<Integer> resultT = productService.update(productGetUpdDto);
        logger.info("===================================="+resultT.getCode());
    }

    @Test
    public void testDelete(){
        Integer productId =39;
        int userId=63;
        ResultT<Integer> ao = productService.deleteSpuInfo(productId,userId);
        logger.info("====");
    }

    @Test
    public void testSpuCount(){
        String productName = "yongliang.li2";
        ResultT<Integer> ao = productService.getSpuCountByName(productName);
        logger.info("====");
    }

}
