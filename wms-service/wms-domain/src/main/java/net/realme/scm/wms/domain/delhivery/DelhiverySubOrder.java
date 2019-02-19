package net.realme.scm.wms.domain.delhivery;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * @author 91000044
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DelhiverySubOrder implements Serializable {

    private static final long serialVersionUID = -3560282799746062898L;

    private Long subOrderNumber;

    private String accessKey;

    private String fulfillmentCenter;

    /**
     * Seller gstin
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String gstin;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String expectedShipDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String dispatchAfterDate;

    private DelhiveryProductDetails productDetails;

    private DelhiveryShipmentDetails shipmentDetails;

    private DelhiveryInvoiceDetails invoiceDetails;

    public Long getSubOrderNumber() {
        return subOrderNumber;
    }

    public void setSubOrderNumber(Long subOrderNumber) {
        this.subOrderNumber = subOrderNumber;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getFulfillmentCenter() {
        return fulfillmentCenter;
    }

    public void setFulfillmentCenter(String fulfillmentCenter) {
        this.fulfillmentCenter = fulfillmentCenter;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getExpectedShipDate() {
        return expectedShipDate;
    }

    public void setExpectedShipDate(String expectedShipDate) {
        this.expectedShipDate = expectedShipDate;
    }

    public String getDispatchAfterDate() {
        return dispatchAfterDate;
    }

    public void setDispatchAfterDate(String dispatchAfterDate) {
        this.dispatchAfterDate = dispatchAfterDate;
    }

    public DelhiveryProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(DelhiveryProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public DelhiveryShipmentDetails getShipmentDetails() {
        return shipmentDetails;
    }

    public void setShipmentDetails(DelhiveryShipmentDetails shipmentDetails) {
        this.shipmentDetails = shipmentDetails;
    }

    public DelhiveryInvoiceDetails getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(DelhiveryInvoiceDetails invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }
}