package net.realme.mall.basics.test;

import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.Application;
import net.realme.mall.basics.dto.TranslationDto;
import net.realme.mall.basics.facade.TranslationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.basics.test
 *
 * @author 91000044
 * @date 2018/8/3 10:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TranslationTest {

    @Autowired
    private TranslationService translationService;

    @Test
    public void testTranslate() {
        ResultT<List<TranslationDto>> data = translationService.listValues("aaddee", "cn");
        System.out.println(data);
    }
}
