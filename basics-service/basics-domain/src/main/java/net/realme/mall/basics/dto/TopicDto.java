package net.realme.mall.basics.dto;

import java.io.Serializable;

/**
 * Created by 91000156 on 2018/9/13 20:33
 */
public class TopicDto implements Serializable {

    private static final long serialVersionUID = -8194950931012514375L;

    /**
     * 自增长ID
     */
    private Integer id;

    /**
     * 订阅的主题
     */
    private String topic;

    /**
     * aws对应的topic识别码
     */
    private String topicArn;

    /**
     * 订阅类型 1.官网首页订阅，2.商品预约订阅
     */
    private Byte subscribeType;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 状态：0 正常，-1 已删除
     */
    private Byte status;

    /**
     * 获取自增长ID
     *
     * @return id - 自增长ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增长ID
     *
     * @param id 自增长ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订阅的主题
     *
     * @return topic - 订阅的主题
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 设置订阅的主题
     *
     * @param topic 订阅的主题
     */
    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    /**
     * 获取aws对应的topic识别码
     *
     * @return topic_arn - aws对应的topic识别码
     */
    public String getTopicArn() {
        return topicArn;
    }

    /**
     * 设置aws对应的topic识别码
     *
     * @param topicArn aws对应的topic识别码
     */
    public void setTopicArn(String topicArn) {
        this.topicArn = topicArn == null ? null : topicArn.trim();
    }

    /**
     * 获取订阅类型 1.官网首页订阅，2.商品预约订阅
     *
     * @return subscribe_type - 订阅类型 1.官网首页订阅，2.商品预约订阅
     */
    public Byte getSubscribeType() {
        return subscribeType;
    }

    /**
     * 设置订阅类型 1.官网首页订阅，2.商品预约订阅
     *
     * @param subscribeType 订阅类型 1.官网首页订阅，2.商品预约订阅
     */
    public void setSubscribeType(Byte subscribeType) {
        this.subscribeType = subscribeType;
    }

    /**
     * 获取商品描述
     *
     * @return description - 商品描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置商品描述
     *
     * @param description 商品描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取状态：0 正常，-1 已删除
     *
     * @return status - 状态：0 正常，-1 已删除
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态：0 正常，-1 已删除
     *
     * @param status 状态：0 正常，-1 已删除
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}
