package net.realme.mall.user.impl;

import net.realme.framework.cache.redis.RedisCache;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.dto.TopicDto;
import net.realme.mall.basics.facade.TopicService;
import net.realme.mall.basics.third.facade.AwsNotifyService;
import net.realme.mall.user.common.NotificationConst;
import net.realme.mall.user.facade.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static net.realme.mall.user.common.NotificationConst.Cache.INDEX_SUB_EMAIL_SET;
import static net.realme.mall.user.common.NotificationConst.Cache.SKU_RESERVE_OUT_STOCK_SET;
import static net.realme.mall.user.common.NotificationConst.RetCode.*;

/**
 * Created by 91000156 on 2018/9/10 16:22
 */
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private TopicService topicService;

    @Autowired
    private AwsNotifyService awsNotifyService;

    @Value("${aws.subscribe.topic.prefix}")
    private String topicPrefix;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 一个手机号，一天最多发十次短信
     *
     * @param phoneNumber
     * @return
     */
    @Override
    public boolean isIllegalAttack(String phoneNumber, String ip) {
        // 初始化合法请求
        boolean illegalFlg = true;
        String maxCacheKey = NotificationConst.Send.SEND_MAX_ATTACK__CACHE_KEY + phoneNumber;
        // 设置请求间隔缓存Key
        String intervalCacheKey = NotificationConst.Send.SEND_CLICK_ATTACK_CACHE_KEY + ip;
        // 两次点击的时间是否小于间隔时间
        Object intervalObject = redisCache.get(intervalCacheKey);
        // 一个IP一个Key,超期时间即为请求间隔
        if(intervalObject != null){
            logger.error("request speed too quick");
            // 直接返回
            return illegalFlg;
        }
        // 查询此手机号当天已发送消息的次数
        Integer sendTimes = (Integer) redisCache.get(maxCacheKey);
        // 大于最大发送次数，则直接返回非法
        if (sendTimes != null && sendTimes >= NotificationConst.Send.SEND_MAX_NUM) {
            logger.error("phone number : {} request time > max_send_times", phoneNumber);
            return illegalFlg;
        }
        if (sendTimes == null) {
            // 设置初始值
            redisCache.set(maxCacheKey, new Integer(1), NotificationConst.Send.SEND_INTERVAL_TTL);
        } else {
            // 次数+1再更新
            sendTimes = sendTimes + 1;
            redisCache.set(maxCacheKey,sendTimes);
        }
        // 一个用户在一个IP地址下不能频繁发起请求
        redisCache.set(intervalCacheKey, "exists", NotificationConst.Send.IP_INTERVAL_TTL);
        return !illegalFlg;
    }

    @Override
    public ResultT<Boolean> isIllegalSubAttack(String ip) {
        // 设置请求间隔缓存Key
        String intervalCacheKey = NotificationConst.Sub.SUB_ATTACK_CACHE_KEY + ip;
        // 设置最大请求次数Key
        String maximumCacheKey = NotificationConst.Sub.SUB_MAX_ATTACK__CACHE_KEY + ip;
        // 查询此IP已请求的次数
        Integer totalTimes = (Integer) redisCache.get(maximumCacheKey);
        Object intervalObject = redisCache.get(intervalCacheKey);
        if(totalTimes != null){
            if(totalTimes >= NotificationConst.Sub.MAX_REQ_TIMES){
                logger.error("ip : {} request time > max_req_times", ip);
                return ResultT.fail();
            }
        }
        // 一个IP一个Key,超期时间即为请求间隔
        if(intervalObject != null){
            logger.debug("request speed too quick");
            // 直接返回
            return ResultT.fail();
        }
        if (totalTimes == null) {
            // 一个email地址一个key，超期时间一分钟，期间内超过10次视为黑名单，只能下个周期再请求
            redisCache.set(maximumCacheKey,new Integer(1),NotificationConst.Sub.MAX_NUM_TTL);
        } else {
            // 次数+1再更新
            totalTimes = totalTimes + 1;
            redisCache.set(maximumCacheKey,totalTimes);
        }
        // 同一个IP地址请求间隔为5秒,防止快速编造无用邮箱恶意刷库
        redisCache.set(intervalCacheKey, "exists",NotificationConst.Sub.IP_INTERVAL_TTL);
        return ResultT.success();
    }

    @Override
    public ResultT<String> frontPageEmailSub(String email) {
        // topic名称组合
        String topic = topicPrefix + NotificationConst.Topic.INDEX_EMAIL_SUB;
        // 检查此Topic是否已存在
        ResultT<TopicDto> topicResult = topicService.getTopicByName(topic);
        TopicDto topicDto = (topicResult != null) ? topicResult.getData() : null;
        // 不存在则创建Topic
        if (topicResult == null || topicDto == null) {
            // 发起创建Topic操作
            String topicArn = awsNotifyService.createSNSTopic(topic);
            // 创建后，记录到数据库中
            topicDto = new TopicDto();
            topicDto.setTopic(topic);
            topicDto.setTopicArn(topicArn);
            topicDto.setCreateTime(System.currentTimeMillis());
            topicDto.setSubscribeType(NotificationConst.SubType.INDEX_SUB);
            topicDto.setDescription(NotificationConst.SubType.INDEX_SUB_DESC);
            topicService.insertTopic(topicDto);
        }
        // 存在Topic后发起订阅
        String subArn = awsNotifyService.subscribeToTopic(topicDto.getTopicArn(), AwsNotifyService.SubscribeProtocol.SUB_EMAIL, email);
        // 订阅成功，则将订阅邮箱存入到缓存集合中
        String key = INDEX_SUB_EMAIL_SET;
        long ret = redisCache.addValueToSet(key, email);
//        if(ret <= 0){
//            return ResultT.fail(REDIS_OPERATED_FAILED, REDIS_OPERATED_FAILED_MSG);
//        }
        return ResultT.success(subArn);
    }

    /**
     * 库存不足时进行商品预约
     *
     * @param subscriber
     * @return
     */
    @Override
    public ResultT<String> productReserveSub(String skuId, String subscriber) {
        // 根据skuId拼订阅的Topic
        String topic = topicPrefix + String.format(NotificationConst.Topic.SKU_OUT_STOCK_NOTIFY, skuId);
        // 检查此Topic是否已存在
        ResultT<TopicDto> topicResult = topicService.getTopicByName(topic);
        TopicDto topicDto = (topicResult != null) ? topicResult.getData() : null;
        // 不存在则创建Topic
        if (topicResult == null || topicDto == null) {
            // 发起创建Topic操作
            String topicArn = awsNotifyService.createSNSTopic(topic);
            // 创建后，记录到数据库中
            topicDto = new TopicDto();
            topicDto.setTopic(topic);
            topicDto.setTopicArn(topicArn);
            topicDto.setCreateTime(System.currentTimeMillis());
            topicDto.setSubscribeType(NotificationConst.SubType.RESERVE_SUB);
            topicDto.setDescription(NotificationConst.SubType.RESERVE_SUB_DESC);
            topicService.insertTopic(topicDto);
        }
        // 存在后，发起订阅
        String subArn = awsNotifyService.subscribeToTopic(topicDto.getTopicArn(), AwsNotifyService.SubscribeProtocol.SUB_SMS, subscriber);
        // 订阅完成后，将订阅者记录到缓存集合中
        String key = String.format(SKU_RESERVE_OUT_STOCK_SET, skuId);
        long ret = redisCache.addValueToSet(key, subscriber);
//        if(ret <= 0){
//            return ResultT.fail(REDIS_OPERATED_FAILED, REDIS_OPERATED_FAILED_MSG);
//        }
        return ResultT.success(subArn);
    }

    @Override
    public ResultT<String> publishMessageToTopic(String topic, String message) {
        // 从数据库中查询topic对应的arn
        ResultT<TopicDto> topicResult = topicService.getTopicByName(topicPrefix + topic);
        TopicDto topicDto = (topicResult != null) ? topicResult.getData() : null;
        if (topicDto == null) {
            return ResultT.fail(NO_SUB_TOPIC_CODE, NO_SUB_TOPIC_MSG);
        }
        String topicArn = topicDto.getTopicArn();
        String msgId = awsNotifyService.sendSMSMessageToTopic(topicArn, message);
        return ResultT.success(msgId);
    }

    @Override
    public ResultT<String> sendAddressSMSToUser(String phoneNumber, String msgContent) {
        // 基于asw发送短消息
        String msgId = awsNotifyService.sendSMSMessageToUser(phoneNumber, msgContent);
        return ResultT.success(msgId);
    }


}
