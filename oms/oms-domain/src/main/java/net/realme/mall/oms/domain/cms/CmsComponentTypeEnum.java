package net.realme.mall.oms.domain.cms;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.domain.cms
 *
 * @author 91000044
 * @date 2018/7/30 20:47
 */
public enum CmsComponentTypeEnum {

    TYPE_SECTION("sec", "模块"),
    TYPE_VARIABLE("var", "变量");

    private String value;
    private String name;

    CmsComponentTypeEnum(String value, String name) {
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

    public static Map<String, String> getValues() {
        Map<String, String> values = new LinkedHashMap<>();
        values.put(TYPE_SECTION.getValue(), TYPE_SECTION.getName());
        values.put(TYPE_VARIABLE.getValue(), TYPE_VARIABLE.getName());
        return values;
    }
}
