package net.realme.mall.basics.test;

import net.realme.mall.basics.Application;
import net.realme.mall.basics.third.facade.AwsNotifyService;
import net.realme.mall.basics.third.facade.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/** 
 * @Author: 91000156
 * @Date: 2018/8/24 11:20
 * @Description:
 */  
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AwsNotifyTest {

    @Autowired
    private AwsNotifyService awsNotifyService;

    @Test
    public void testCreateTopic() {
        System.out.println("==================================");
        String phoneNumber = "+8618588969069";
        String topic = "topic:index:email:subscribe12";
        String topicArn = awsNotifyService.createSNSTopic(topic);
        //awsNotifyService.notificationMsgToUser(phoneNumber,"测试手机预约");
        System.out.println("=======================================:" + topicArn);
    }

    @Test
    public void testSubscribe() {
        System.out.println("==================================");
        String phoneNumber = "+8618588969069";
        String email = "longhui.tang@realme.com";
        String email1 = "lichao@realme.com";
        String email2 = "xingfan@realme.com";
        String topicArn = "arn:aws:sns:us-east-1:628314549591:realme_developer_test";
        awsNotifyService.subscribeToTopic(topicArn, AwsNotifyService.SubscribeProtocol.SUB_SMS ,phoneNumber);
        awsNotifyService.subscribeToTopic(topicArn,AwsNotifyService.SubscribeProtocol.SUB_EMAIL,email);
        awsNotifyService.subscribeToTopic(topicArn,AwsNotifyService.SubscribeProtocol.SUB_EMAIL,email1);
        awsNotifyService.subscribeToTopic(topicArn,AwsNotifyService.SubscribeProtocol.SUB_EMAIL,email2);
        System.out.println("=======================================");
    }

    @Test
    public void testSendMsg() {
        System.out.println("==================================");
        String messageInfo = "realme developer send msg";
        String topicArn = "arn:aws:sns:us-east-1:628314549591:realme_developer_test";
        awsNotifyService.sendSMSMessageToTopic(topicArn, messageInfo);
        System.out.println("=======================================");
    }

    @Test
    public void testSendMsgToUser() {
        System.out.println("==================================");
        String phoneNumber = "+8618898361626";
        awsNotifyService.sendSMSMessageToUser(phoneNumber,"测试手机预约");
        System.out.println("=======================================");
    }

    @Test
    public void testUnSubscribe() {
        System.out.println("==================================");
        String subTopicArn = "arn:aws:sns:us-east-1:628314549591:basic-test:646c2144-4c91-4809-9263-d912dd813eb3";
        awsNotifyService.unsubscribeToTopic(subTopicArn);
        System.out.println("=======================================");
    }

    @Test
    public void testGetSubscribeList() {
        System.out.println("==================================");
        String topicArn = "arn:aws:sns:us-east-1:628314549591:realme_developer_test";
        awsNotifyService.getSubscribeTopic(topicArn);
        System.out.println("=======================================");
    }
}
