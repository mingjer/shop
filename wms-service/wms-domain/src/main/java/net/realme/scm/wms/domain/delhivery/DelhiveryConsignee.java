
package net.realme.scm.wms.domain.delhivery;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;

/**
 * @author 91000044
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DelhiveryConsignee implements Serializable{

    private static final long serialVersionUID = 8089412625905069074L;
    private String name;
    private String state;
    private String city;
    private String address2;
    private String address1;
    private String country;
    private Long pincode;
    private Long phone1;
    private Integer phone2;
    private String email;
    /**
     * Consignee gstin
     */
    private Long gstin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public Long getPhone1() {
        return phone1;
    }

    public void setPhone1(Long phone1) {
        this.phone1 = phone1;
    }

    public Integer getPhone2() {
        return phone2;
    }

    public void setPhone2(Integer phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getGstin() {
        return gstin;
    }

    public void setGstin(Long gstin) {
        this.gstin = gstin;
    }
}