package net.realme.scm.wms.domain.delhivery;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import net.realme.scm.wms.domain.PushProductPayload;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DelhiveryProduct extends PushProductPayload {

    private static final long serialVersionUID = 2241853869280032110L;

    private String clientKey;
    private String supplierKey;
    private String number;
    private String name;
    private String sku;
    private String description;
    private String hsnCode;
    private String type;
    private String url;
    private String catalogue;
    private Boolean imeiRequired;
    private Boolean serialNumberRequired;
    private Date expiryDate;
    private Integer length;
    private Integer width;
    private Integer height;
    private Integer weight;
    private Integer mrp;

    private List<DelhiveryChildProduct> childProducts;


    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public String getSupplierKey() {
        return supplierKey;
    }

    public void setSupplierKey(String supplierKey) {
        this.supplierKey = supplierKey;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(String catalogue) {
        this.catalogue = catalogue;
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

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
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

    public List<DelhiveryChildProduct> getChildProducts() {
        return childProducts;
    }

    public void setChildProducts(List<DelhiveryChildProduct> childProducts) {
        this.childProducts = childProducts;
    }
}