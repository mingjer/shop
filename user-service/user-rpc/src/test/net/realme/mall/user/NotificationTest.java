package net.realme.mall.user;

import net.realme.framework.util.dto.ResultT;
import net.realme.mall.user.facade.NotificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static net.realme.mall.user.common.NotificationConst.Topic.INDEX_EMAIL_SUB;

/**
 * Created by 91000156 on 2018/9/10 17:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class NotificationTest {

    @Autowired
    private NotificationService sendMsgService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testSendMsg() {
        String ip = "127.0.0.1";
        String phoneNumber = "+8618588969069";
        String msgContent = "user service test 19:29 ";
        if (!sendMsgService.isIllegalAttack(phoneNumber, ip)) {
            String msgId = sendMsgService.sendAddressSMSToUser(phoneNumber, msgContent).getData();
            logger.info(msgId);
        }
    }

    @Test
    public void frontTest() {
        String email = "lichao@realme.com";
        ResultT<String> resultT = sendMsgService.frontPageEmailSub(email);
        logger.info("");
    }

    @Test
    public void reserveTest() {
        String skuId = "140";
        String phoneNumber = "+8613610046511";
        ResultT<String> resultT = sendMsgService.productReserveSub(skuId,phoneNumber);
        logger.info("");
    }

    @Test
    public void pushTest() {
        String topic = "topic_sku_reserve_140";
        String msgContent = "user service test 19:29 ";
        ResultT<String> resultT = sendMsgService.publishMessageToTopic(topic,msgContent);
    }

    @Test
    public void subIllegal() {
        String ip = "127.0.0.1";
        ResultT<Boolean> resultT = sendMsgService.isIllegalSubAttack(ip);
        logger.info("");
    }
}
