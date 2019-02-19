package net.realme.mall.basics.third.facade;

import net.realme.framework.util.dto.ResultT;

/**
 * @Author: 91000156
 * @Date: 2018/8/24 11:16
 * @Description:
 */  
public interface AwsNotifyService {

    interface SubscribeProtocol {
        // 发邮件的订阅类型
        String SUB_EMAIL = "email";
        String SUB_EMAIL_JSON = "email-json";
        // 发短信的订阅类型
        String SUB_SMS = "sms";
    }

    // 创建主题
    String createSNSTopic(String topic);

    // 为主题添加订阅
    String subscribeToTopic(String topicArn, String subType, String phoneNumber);

    // 退订指定的已订阅主题（删除主题）
    void unsubscribeToTopic(String topicArn);

    // 向主题发送消息
    String sendSMSMessageToTopic(String topicArn, String message);

    // 发出sns消息到用户
    String sendSMSMessageToUser(String phoneNumber,String msgContent);

    // 获取已订阅的主题
    ResultT<Boolean> getSubscribeTopic(String topic);

}
