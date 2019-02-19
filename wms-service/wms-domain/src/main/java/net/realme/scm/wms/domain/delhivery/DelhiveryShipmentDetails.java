
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
public class DelhiveryShipmentDetails implements Serializable {

    private static final long serialVersionUID = -5086473779930074026L;

    private String paymentMode;

    private String packingSlipLink;

    private String shipmentNumber;

    private String shippingLevel;

    private String sortingCode;

    private String waybillNumber;

    private String courier;

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPackingSlipLink() {
        return packingSlipLink;
    }

    public void setPackingSlipLink(String packingSlipLink) {
        this.packingSlipLink = packingSlipLink;
    }

    public String getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }

    public String getShippingLevel() {
        return shippingLevel;
    }

    public void setShippingLevel(String shippingLevel) {
        this.shippingLevel = shippingLevel;
    }

    public String getSortingCode() {
        return sortingCode;
    }

    public void setSortingCode(String sortingCode) {
        this.sortingCode = sortingCode;
    }

    public String getWaybillNumber() {
        return waybillNumber;
    }

    public void setWaybillNumber(String waybillNumber) {
        this.waybillNumber = waybillNumber;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }
}