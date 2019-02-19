package net.realme.mall.product.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.product.domain
 * SPU信息Model:插入时使用
 *
 * @author 91000044
 * @date 2018/8/23 10:46
 */
public class ProductDto implements Serializable {

    private static final long serialVersionUID = -2216263200815881018L;

    private Integer productId;

    private String name;

    private String brandCode;

    private String categoryCode;

    private Byte type;

    private String description;

    private Integer sequence;

    private Long createdTime;

    private Long updatedTime;

    private Integer createdBy;

    private Integer updatedBy;

    private Byte shelfStatus;

    private Byte status;

    private String extraParams;

    private Map<Integer, List<String>> options = new HashMap<>();

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode == null ? null : brandCode.trim();
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Byte getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(Byte shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getExtraParams() {
        return extraParams;
    }

    public void setExtraParams(String extraParams) {
        this.extraParams = extraParams == null ? null : extraParams.trim();
    }

    public Map<Integer, List<String>> getOptions() {
        return options;
    }

    public void setOptions(Map<Integer, List<String>> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                //"productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", brandCode='" + brandCode + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", sequence=" + sequence +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", shelfStatus=" + shelfStatus +
                ", status=" + status +
                ", extraParams='" + extraParams + '\'' +
                ", options=" + options +
                '}';
    }
}
