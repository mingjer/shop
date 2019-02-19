package net.realme.mall.oms.order.rsp;

import java.io.Serializable;

/**
 * OMS订单详情-收货信息
 */
public class OrderDetailConsigneeRsp implements Serializable {

    private String province;

    private String city;

    /**
     * 详细地址
     */
    private String address1;

    private String pinCode;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}
