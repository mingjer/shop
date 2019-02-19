package net.realme.mall.user.facade;

import net.realme.framework.util.dto.ResultT;

/**
 * Created by 91000156 on 2018/9/10 15:39
 */
public interface NotificationService {

    // 服务网点管理，根据填写手机号发短信
    ResultT<String> sendAddressSMSToUser(String phoneNumber, String msgContent);

    // 发短信时防止非法攻击
    boolean isIllegalAttack(String phoneNumber, String ip);

    // 判断订阅时是否为非法攻击
    ResultT<Boolean> isIllegalSubAttack(String ip);

    // 官网首页的邮件订阅
    ResultT<String> frontPageEmailSub(String email);

    // 库存不足时预约订阅
    ResultT<String> productReserveSub(String skuId, String subscriber);

    // 推送消息到订阅主题
    ResultT<String> publishMessageToTopic(String topic, String message);
}
