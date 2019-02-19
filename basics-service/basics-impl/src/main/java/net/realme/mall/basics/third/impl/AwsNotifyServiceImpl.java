package net.realme.mall.basics.third.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.*;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.third.facade.AwsNotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/** 
 * @Author: 91000156
 * @Date: 2018/8/24 11:18
 * @Description:
 */  
@Service
public class AwsNotifyServiceImpl implements AwsNotifyService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${aws.sns.key}")
    private String awsKey;

    @Value("${aws.sns.secret}")
    private String awsSecret;

    private static  AmazonSNS snsClient = null;

    private Map<String, MessageAttributeValue> smsAttributes;

    private Map<String, MessageAttributeValue> emailAttributes;

    @PostConstruct
    public void init(){
        AWSCredentials awsCredentials = new AWSCredentials() {
            @Override
            public String getAWSAccessKeyId() {
                return awsKey; // 带有发短信权限的 IAM 的 ACCESS_KEY
            }

            @Override
            public String getAWSSecretKey() {
                return awsSecret; // 带有发短信权限的 IAM 的 SECRET_KEY
            }
        };
        AWSCredentialsProvider provider = new AWSCredentialsProvider() {
            @Override
            public AWSCredentials getCredentials() {
                return awsCredentials;
            }

            @Override
            public void refresh() {
            }
        };
        try {
            snsClient = AmazonSNSClientBuilder.standard().withCredentials(provider).withRegion("us-east-1").build();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public String createSNSTopic(String topic) {
        // 创建Topic的信息
        CreateTopicRequest createTopicRequest = new CreateTopicRequest(topic);
        // 打印请求信息
        logger.info("Create topic request: " + snsClient.getCachedResponseMetadata(createTopicRequest));
        // 发起创建请求
        CreateTopicResult result = snsClient.createTopic(createTopicRequest);
        // 打印输出结果
        logger.info("Create topic result: " + result);
        return result.getTopicArn();
    }

    @Override
    public String subscribeToTopic(String topicArn, String subType, String phoneNumber) {
        // 创建订阅信息
        SubscribeRequest subscribeRequest = new SubscribeRequest(topicArn, subType, phoneNumber);
        // 打印请求信息
        logger.info("Subscribe request: " + snsClient.getCachedResponseMetadata(subscribeRequest));
        // 发起订阅请求
        SubscribeResult subscribeResult = snsClient.subscribe(subscribeRequest);
        // 打印输出结果
        logger.info("Subscribe result: " + subscribeResult);
        return subscribeResult.getSubscriptionArn();
    }

    @Override
    public void unsubscribeToTopic(String topicArn) {
        // 创建退订信息
        UnsubscribeRequest unsubscribeRequest = new UnsubscribeRequest(topicArn);
        // 打印请求信息
        logger.info("Un-Subscribe request: " + snsClient.getCachedResponseMetadata(unsubscribeRequest));
        // 发起退订删Topic请求
        UnsubscribeResult unsubscribe = snsClient.unsubscribe(unsubscribeRequest);
        // 打印输出结果
        logger.info("Un-Subscribe result: " + unsubscribe);
    }

    @Override
    public String sendSMSMessageToTopic(String topicArn, String message) {
        PublishResult result = snsClient.publish(new PublishRequest()
                .withTopicArn(topicArn)
                .withMessage(message)
                .withMessageAttributes(getDefaultSMSAttributes()));
        logger.info("Send Message ID: "+result.getMessageId());
        return result.getMessageId();
    }

    @Override
    public String sendSMSMessageToUser(String phoneNumber, String msgContent) {
        String msgId = "";
        PublishResult result = snsClient.publish(
                new PublishRequest()
                        .withMessage(msgContent)
                        .withPhoneNumber(phoneNumber)
        );
        if (result != null) {
            msgId = result.getMessageId();
            logger.info("Sent SMS message ID: {}", msgId);
        }
        return msgId;
    }

    @Override
    public ResultT<Boolean> getSubscribeTopic(String topic){
        ListSubscriptionsResult result = snsClient.listSubscriptions();
        for (Subscription sub : result.getSubscriptions()) {
            logger.info("sub topic arn: "+sub.getSubscriptionArn());
        }
        return ResultT.fail();
    }

    public Map<String, MessageAttributeValue> getDefaultSMSAttributes(){
        if (smsAttributes == null) {
            smsAttributes = new HashMap<>();
            smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                    .withStringValue("1")
                    .withDataType("String"));
            smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
                    .withStringValue("Transactional")
                    .withDataType("String"));
        }
        return smsAttributes;
    }

    public Map<String, MessageAttributeValue> getSendEmailAttributes(){
        if (emailAttributes == null) {
            emailAttributes = new HashMap<>();
            emailAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                    .withStringValue("1")
                    .withDataType("String"));
            emailAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
                    .withStringValue("Promotional")
                    .withDataType("String"));
        }
        return emailAttributes;
    }
}
