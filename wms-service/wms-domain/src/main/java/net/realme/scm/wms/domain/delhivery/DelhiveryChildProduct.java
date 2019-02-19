
package net.realme.scm.wms.domain.delhivery;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;

/**
 * @author 91000044
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DelhiveryChildProduct implements Serializable {

    private static final long serialVersionUID = 6886878194255733398L;


    private Integer comboQuantity;
    private String number;
    private String name;
    private String sku;
    private String description;

    private String hsnCode;
    private String url;

    private Boolean imeiRequired;


    private Boolean serialNumberRequired;

    private Integer length;
    private Integer width;
    private Integer height;
    private Integer weight;
    private Integer mrp;

    public Integer getComboQuantity() {
        return comboQuantity;
    }

    public void setComboQuantity(Integer comboQuantity) {
        this.comboQuantity = comboQuantity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getImeiRequired() {
        return imeiRequired;
    }

    public void setImeiRequired(Boolean imeiRequired) {
        this.imeiRequired = imeiRequired;
    }

    public Boolean getSerialNumberRequired() {
        return serialNumberRequired;
    }

    public void setSerialNumberRequired(Boolean serialNumberRequired) {
        this.serialNumberRequired = serialNumberRequired;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getMrp() {
        return mrp;
    }

    public void setMrp(Integer mrp) {
        this.mrp = mrp;
    }
}