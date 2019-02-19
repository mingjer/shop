package net.realme.mall.basics.dto;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.basics.dto
 *
 * 行政区域划分类型
 * @author 91000044
 * @date 2018/8/28 15:26
 */
public enum AdministrativeDivisionType {

    /**
     * 国家
     */
    COUNTRY("country"),
    /**
     * 省/邦
     */
    PROVINCE("province"),
    /**
     * 市
     */
    CITY("city"),
    /**
     * 县
     */
    COUNTY("county"),
    /**
     * 镇
     */
    TOWN("town");

    private String value;

    AdministrativeDivisionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
