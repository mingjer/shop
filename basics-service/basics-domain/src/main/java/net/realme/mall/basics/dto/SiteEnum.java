package net.realme.mall.basics.dto;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.basics.dto
 *
 * @author 91000044
 * @date 2018/8/3 9:17
 */
public enum SiteEnum {

    //主要用于数据存储(相当于默认值)
    DEFAULT_SITE("default", "DEFAULT"),
    //主要用于部署
    GLOBAL_SITE("global", "GLOBAL");

    private String value;
    private String name;

    SiteEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
