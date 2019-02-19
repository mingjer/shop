package net.realme.mall.basics.test;

import net.realme.mall.basics.Application;
import net.realme.mall.basics.third.facade.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
public class EmailTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void testTranslate() {
        System.out.println("==================================");
        String[] array = {"lichao@realme.net","longhui.tang@realme.net"};
        //emailService.send("To Manys", "manu", array);
        System.out.println("=======================================");
    }
}
