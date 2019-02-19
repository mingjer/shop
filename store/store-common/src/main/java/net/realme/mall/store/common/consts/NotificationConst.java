package net.realme.mall.store.common.consts;

/**
 * Created by 91000156 on 2018/9/14 19:46
 */
public interface NotificationConst {

    interface RetCode {
        // 未知错误
        Integer EXCEPTION_ERROR = 10000;
        String EXCEPTION_ERROR_MSG = "product.exception.error";
        // 服务调用失败
        Integer SERVICE_INVOKE_FAILED = 10001;
        String SERVICE_INVOKE_FAILED_MSG = "product.service.invoke.failed";
        // 接口参数校验失败
        Integer VALIDATE_FAILED = 10002;
        String VALIDATE_FAILED_MSG = "product.param.validate.failed";
        // 发送失败
        Integer SEND_MSG_FAILED = 10004;
        String SEND_MSG_FAILED_MSG = "product.message.send.failed";
        // 订阅失败
        Integer SUBSCRIBE_FAILED = 10005;
        String SUBSCRIBE_FAILED_MSG = "product.message.subscribe.failed";
        // 没有服务网点
        Integer SERVICE_SITE_NONE = 12002;
        String SERVICE_SITE_NONE_MSG = "product.service.sit.none";
        // 发布失败
        Integer PUBLISH_FAILED = 10006;
        String PUBLISH_FAILED_MSG = "product.message.publish.failed";
    }
    interface SubType {
        // 1.官网首页订阅
        Integer INDEX_SUB = 1;
        // 2.商品预约订阅
        Integer RESERVE_SUB = 2;
    }

    // 订阅主题
    interface Topic {
        // 官网首页订阅的Topic
        String INDEX_EMAIL_SUB = "topic_index_email_subscribe";
        // 商品库存不足时预约Topic
        String SKU_OUT_STOCK_NOTIFY = "topic_sku_reserve_%s";
    }
}
