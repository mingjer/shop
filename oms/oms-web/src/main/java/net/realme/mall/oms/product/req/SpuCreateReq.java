package net.realme.mall.oms.product.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.product.req
 *
 * @author 91000044
 * @date 2018/8/23 13:47
 */
@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpuCreateReq implements Serializable {

    private static final long serialVersionUID = -8381611225981821774L;

    private String categoryCode;
    private String brandCode;
    private String name;
    private String description;
    // 1 实体产品 2 虚拟产品 3 套装
    private Byte type;
    // 状态，0上架 1下架
    private Byte shelfStatus;
    // 更新商品属性组
    private HashMap<Integer, List<String>> options;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(Byte shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    public HashMap<Integer, List<String>> getOptions() {
        return options;
    }

    public void setOptions(HashMap<Integer, List<String>> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "SpuCreateReq{" +
                "categoryCode='" + categoryCode + '\'' +
                ", brandCode='" + brandCode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", shelfStatus=" + shelfStatus +
                ", options=" + options +
                '}';
    }
}
