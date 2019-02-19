package net.realme.scm.wms.domain.delhivery;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.scm.wms.domain.delhivery
 *
 * @author 91000044
 * @date 2018/9/20 15:48
 */
public enum  DelhiveryOrderStatus {

    PAK("PAK","packed"),
    SHP("SHP","shipped");

    private String value;
    private String display;

    DelhiveryOrderStatus(String value,String display) {
        this.value = value;
        this.display=display;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}
