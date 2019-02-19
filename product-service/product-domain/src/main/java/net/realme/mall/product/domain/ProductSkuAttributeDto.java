package net.realme.mall.product.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 91000156 on 2018/8/29 15:48
 */
public class ProductSkuAttributeDto implements Serializable {

    private static final long serialVersionUID = -2511028301134240759L;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 产品sku id
     */
    private Integer skuId;

    /**
     * 属性id
     */
    private String attrId;

    /**
     * 属性值id
     */
    private String attrValId;

    /**
     * 创建时间
     */
    private Long createdTime;

    /**
     * 更新时间
     */
    private Long updatedTime;

    /**
     * 状态：0-上架，1-已下架
     */
    private Byte shelfStatus;

    /**
     * 状态：0可用，1不可用
     */
    private Byte status;

    /**
     * 用户所选的sku属性，attr_id:attr_val_id
     */
    private Map<String, String> option = new HashMap<>();

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    /**
     * 获取属性id
     *
     * @return attr_id - 属性id
     */
    public String getAttrId() {
        return attrId;
    }

    /**
     * 设置属性id
     *
     * @param attrId 属性id
     */
    public void setAttrId(String attrId) {
        this.attrId = attrId == null ? null : attrId.trim();
    }

    /**
     * 获取属性值id
     *
     * @return attr_val_id - 属性值id
     */
    public String getAttrValId() {
        return attrValId;
    }

    /**
     * 设置属性值id
     *
     * @param attrValId 属性值id
     */
    public void setAttrValId(String attrValId) {
        this.attrValId = attrValId == null ? null : attrValId.trim();
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Long getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取更新时间
     *
     * @return updated_time - 更新时间
     */
    public Long getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 设置更新时间
     *
     * @param updatedTime 更新时间
     */
    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * 获取状态：0可用，1不可用
     *
     * @return status - 状态：0可用，1不可用
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态：0可用，1不可用
     *
     * @param status 状态：0可用，1不可用
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    public Map<String, String> getOption() {
        return option;
    }

    public void setOption(Map<String, String> option) {
        this.option = option;
    }

    public Byte getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(Byte shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    @Override
    public String toString() {
        return "ProductSkuAttributeDto{" +
                "productId=" + productId +
                ", skuId=" + skuId +
                ", attrId='" + attrId + '\'' +
                ", attrValId='" + attrValId + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", shelfStatus=" + shelfStatus +
                ", status=" + status +
                ", option=" + option +
                '}';
    }
}
