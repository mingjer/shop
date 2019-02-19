package net.realme.mall.store.domain.order.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * 提交订单收货地址信息
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubmitOrderAddressReq  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
//    private String address1;
//    private String address2;
//    private String provinceName;
//    private String cityName;
//    private String email;
//    private String fullName;
//    private String phoneCallingCodes;
//    private String phoneNumber;
//    private String pinCode;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
