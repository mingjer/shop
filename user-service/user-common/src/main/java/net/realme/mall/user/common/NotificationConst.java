package net.realme.mall.user.common;

/**
 * Created by 91000156 on 2018/9/13 19:35
 */
public interface NotificationConst {

    // 订阅相关的全局变量
    interface Sub {
        String SUB_ATTACK_CACHE_KEY = "sub_interval_";
        String SUB_MAX_ATTACK__CACHE_KEY = "sub_maximum_";
        // 按照请求频率限制攻击
        Integer IP_INTERVAL_TTL = 10;
        // 按照总次数限制的攻击
        Integer MAX_NUM_TTL = 60;
        // 单位频次最高请求次数
        Integer MAX_REQ_TIMES = 5;
    }

    interface Send {
        // 手机发送次数CacheKey
        String SEND_MAX_ATTACK__CACHE_KEY = "send_msg_maximum_";
        // 点击发送的频率间隔，不能过快点击
        String SEND_CLICK_ATTACK_CACHE_KEY = "send_interval_";
        // 按照总发送次数限制的攻击
        Integer SEND_MAX_NUM = 10;
        // 最大发送数的计算周期，秒
        Integer SEND_INTERVAL_TTL = 86400;
        // 按照请求频率限制攻击
        Integer IP_INTERVAL_TTL = 5;
    }

    // 订阅主题
    interface Topic {
        // 官网首页订阅的Topic
        String INDEX_EMAIL_SUB = "topic_index_email_subscribe";
        // 商品库存不足时预约Topic
        String SKU_OUT_STOCK_NOTIFY = "topic_sku_reserve_%s";
    }

    // 缓存相关Key
    interface Cache {
        // 存储官网首页订阅的邮箱
        String INDEX_SUB_EMAIL_SET = "index_sub_email_set";
        // 存储预约sku的用户
        String SKU_RESERVE_OUT_STOCK_SET = "sku_reserve_set_%s";
    }

    interface SubType {
        // 1.官网首页订阅
        Byte INDEX_SUB = 1;
        String INDEX_SUB_DESC = "官网首页订阅";
        // 2.商品预约订阅
        Byte RESERVE_SUB = 2;
        String RESERVE_SUB_DESC = "商品预约订阅";
    }

    interface RetCode {
        int NO_SUB_TOPIC_CODE = 10001;
        String NO_SUB_TOPIC_MSG = "have not subscribed topic";
        int REDIS_OPERATED_FAILED = 10002;
        String REDIS_OPERATED_FAILED_MSG = "redis operated failed";
    }
}
