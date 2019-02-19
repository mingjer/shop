package net.realme.scm.wms.domain.delhivery;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.scm.wms.domain.delhivery
 *
 * @author 91000044
 * @date 2018/9/20 15:11
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DelhiveryOrderNotification implements Serializable {
    private static final long serialVersionUID = 5005664687263643625L;


    private List<NotifiedOrderLines> orderlines;

    public List<NotifiedOrderLines> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(List<NotifiedOrderLines> orderlines) {
        this.orderlines = orderlines;
    }


    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class NotifiedOrderLines implements Serializable {
        private static final long serialVersionUID = 7143219887486368852L;
        private String status;
        private String orderId;
        private String orderLineId;
        private String fulfillmentCenter;
        private String clientStore;
        private BigDecimal weight;
        private Date shippingDate;
        private String shippingId;
        private String courier;
        private String waybill;

        private List<NotifiedProduct> products;

        private NotifiedInvoiceDetails invoiceDetails;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderLineId() {
            return orderLineId;
        }

        public void setOrderLineId(String orderLineId) {
            this.orderLineId = orderLineId;
        }

        public String getFulfillmentCenter() {
            return fulfillmentCenter;
        }

        public void setFulfillmentCenter(String fulfillmentCenter) {
            this.fulfillmentCenter = fulfillmentCenter;
        }

        public String getClientStore() {
            return clientStore;
        }

        public void setClientStore(String clientStore) {
            this.clientStore = clientStore;
        }

        public BigDecimal getWeight() {
            return weight;
        }

        public void setWeight(BigDecimal weight) {
            this.weight = weight;
        }

        public Date getShippingDate() {
            return shippingDate;
        }

        public void setShippingDate(Date shippingDate) {
            this.shippingDate = shippingDate;
        }

        public String getShippingId() {
            return shippingId;
        }

        public void setShippingId(String shippingId) {
            this.shippingId = shippingId;
        }

        public String getCourier() {
            return courier;
        }

        public void setCourier(String courier) {
            this.courier = courier;
        }

        public String getWaybill() {
            return waybill;
        }

        public void setWaybill(String waybill) {
            this.waybill = waybill;
        }

        public List<NotifiedProduct> getProducts() {
            return products;
        }

        public void setProducts(List<NotifiedProduct> products) {
            this.products = products;
        }

        public NotifiedInvoiceDetails getInvoiceDetails() {
            return invoiceDetails;
        }

        public void setInvoiceDetails(NotifiedInvoiceDetails invoiceDetails) {
            this.invoiceDetails = invoiceDetails;
        }
    }


    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class NotifiedProduct implements Serializable {
        private static final long serialVersionUID = -1767606752487679734L;

        private String status;
        private String prodSku;
        private String prodNum;
        private Integer prodQty;
        private String prodName;
        private String secondarySerialNumber;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getProdSku() {
            return prodSku;
        }

        public void setProdSku(String prodSku) {
            this.prodSku = prodSku;
        }

        public String getProdNum() {
            return prodNum;
        }

        public void setProdNum(String prodNum) {
            this.prodNum = prodNum;
        }

        public Integer getProdQty() {
            return prodQty;
        }

        public void setProdQty(Integer prodQty) {
            this.prodQty = prodQty;
        }

        public String getProdName() {
            return prodName;
        }

        public void setProdName(String prodName) {
            this.prodName = prodName;
        }

        public String getSecondarySerialNumber() {
            return secondarySerialNumber;
        }

        public void setSecondarySerialNumber(String secondarySerialNumber) {
            this.secondarySerialNumber = secondarySerialNumber;
        }

        @Override
        public String toString() {
            return "NotifiedProduct{" +
                    "status='" + status + '\'' +
                    ", prodSku='" + prodSku + '\'' +
                    ", prodNum='" + prodNum + '\'' +
                    ", prodQty=" + prodQty +
                    ", prodName='" + prodName + '\'' +
                    ", secondarySerialNumber='" + secondarySerialNumber + '\'' +
                    '}';
        }
    }

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class NotifiedInvoiceDetails implements Serializable {
        private static final long serialVersionUID = -4403584004204473618L;

        private String invoiceNumber;
        private Date invoiceDate;
        private String referenceNumber;
        private String invoiceUrl;
        private BigDecimal mrp;
        private BigDecimal grossValue;
        private BigDecimal discount;
        private BigDecimal shippingPrice;
        private BigDecimal netAmount;
        private BigDecimal codAmount;
        private BigDecimal totalPrice;
        private BigDecimal cgstAmount;
        private BigDecimal cgstPercentage;
        private BigDecimal igstAmount;
        private BigDecimal igstPercentage;
        private BigDecimal sgstAmount;
        private BigDecimal sgstPercentage;

        public String getInvoiceNumber() {
            return invoiceNumber;
        }

        public void setInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
        }

        public Date getInvoiceDate() {
            return invoiceDate;
        }

        public void setInvoiceDate(Date invoiceDate) {
            this.invoiceDate = invoiceDate;
        }

        public String getReferenceNumber() {
            return referenceNumber;
        }

        public void setReferenceNumber(String referenceNumber) {
            this.referenceNumber = referenceNumber;
        }

        public String getInvoiceUrl() {
            return invoiceUrl;
        }

        public void setInvoiceUrl(String invoiceUrl) {
            this.invoiceUrl = invoiceUrl;
        }

        public BigDecimal getMrp() {
            return mrp;
        }

        public void setMrp(BigDecimal mrp) {
            this.mrp = mrp;
        }

        public BigDecimal getGrossValue() {
            return grossValue;
        }

        public void setGrossValue(BigDecimal grossValue) {
            this.grossValue = grossValue;
        }

        public BigDecimal getDiscount() {
            return discount;
        }

        public void setDiscount(BigDecimal discount) {
            this.discount = discount;
        }

        public BigDecimal getShippingPrice() {
            return shippingPrice;
        }

        public void setShippingPrice(BigDecimal shippingPrice) {
            this.shippingPrice = shippingPrice;
        }

        public BigDecimal getNetAmount() {
            return netAmount;
        }

        public void setNetAmount(BigDecimal netAmount) {
            this.netAmount = netAmount;
        }

        public BigDecimal getCodAmount() {
            return codAmount;
        }

        public void setCodAmount(BigDecimal codAmount) {
            this.codAmount = codAmount;
        }

        public BigDecimal getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
        }

        public BigDecimal getCgstAmount() {
            return cgstAmount;
        }

        public void setCgstAmount(BigDecimal cgstAmount) {
            this.cgstAmount = cgstAmount;
        }

        public BigDecimal getCgstPercentage() {
            return cgstPercentage;
        }

        public void setCgstPercentage(BigDecimal cgstPercentage) {
            this.cgstPercentage = cgstPercentage;
        }

        public BigDecimal getIgstAmount() {
            return igstAmount;
        }

        public void setIgstAmount(BigDecimal igstAmount) {
            this.igstAmount = igstAmount;
        }

        public BigDecimal getIgstPercentage() {
            return igstPercentage;
        }

        public void setIgstPercentage(BigDecimal igstPercentage) {
            this.igstPercentage = igstPercentage;
        }

        public BigDecimal getSgstAmount() {
            return sgstAmount;
        }

        public void setSgstAmount(BigDecimal sgstAmount) {
            this.sgstAmount = sgstAmount;
        }

        public BigDecimal getSgstPercentage() {
            return sgstPercentage;
        }

        public void setSgstPercentage(BigDecimal sgstPercentage) {
            this.sgstPercentage = sgstPercentage;
        }

        @Override
        public String toString() {
            return "NotifiedInvoiceDetails{" +
                    "invoiceNumber='" + invoiceNumber + '\'' +
                    ", invoiceDate='" + invoiceDate + '\'' +
                    ", referenceNumber='" + referenceNumber + '\'' +
                    ", invoiceUrl='" + invoiceUrl + '\'' +
                    ", mrp=" + mrp +
                    ", grossValue=" + grossValue +
                    ", discount=" + discount +
                    ", shippingPrice=" + shippingPrice +
                    ", netAmount=" + netAmount +
                    ", codAmount=" + codAmount +
                    ", totalPrice=" + totalPrice +
                    ", cgstAmount=" + cgstAmount +
                    ", cgstPercentage=" + cgstPercentage +
                    ", igstAmount=" + igstAmount +
                    ", igstPercentage=" + igstPercentage +
                    ", sgstAmount=" + sgstAmount +
                    ", sgstPercentage=" + sgstPercentage +
                    '}';
        }
    }

}
