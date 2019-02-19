package net.realme.scm.wms.test;

import net.realme.framework.util.dto.ResultT;
import net.realme.scm.Application;
import net.realme.scm.wms.domain.delhivery.DelhiveryProduct;
import net.realme.scm.wms.impl.DelhiveryIntegrationImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.basics.test
 *
 * @author 91000044
 * @date 2018/8/4 18:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class WmsTest {

    @Resource(name = "delhivery")
    private DelhiveryIntegrationImpl delhiveryIntegration;


    @Test
    public void testCreateProduct() {

        DelhiveryProduct delhiveryProduct = new DelhiveryProduct();
        delhiveryProduct.setNumber("954124235325325");
        delhiveryProduct.setHsnCode("954124235325325");
        delhiveryProduct.setName("ff");
        delhiveryProduct.setSku("954124235325325");

        ResultT<Boolean> ret =  delhiveryIntegration.pushProduct(delhiveryProduct);
        System.out.println(ret.getCode());
        System.out.println(ret.getMsg());
    }

}
