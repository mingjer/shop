package net.realme.scm.wms.domain.delhivery;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author 91000044
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DelhiveryInvoiceDetails implements Serializable {

    private static final long serialVersionUID = 2026594065062332952L;

    private String invoiceNumber;

    private String referenceNumber;


    private String invoiceDate;
    private String invoiceLink;

    private String imei;

    private BigDecimal mrp = BigDecimal.ZERO;

    private BigDecimal discount = BigDecimal.ZERO;

    private BigDecimal unitTaxes = BigDecimal.ZERO;

    private BigDecimal totalTaxes = BigDecimal.ZERO;

    private BigDecimal advancePayment = BigDecimal.ZERO;

    private BigDecimal cgstAmount = BigDecimal.ZERO;

    private BigDecimal cgstPercentage = BigDecimal.ZERO;

    private BigDecimal codAmount = BigDecimal.ZERO;

    private BigDecimal cstPercentage = BigDecimal.ZERO;

    private BigDecimal grossValue = BigDecimal.ZERO;

    private BigDecimal igstAmount = BigDecimal.ZERO;

    private BigDecimal igstPercentage = BigDecimal.ZERO;

    private BigDecimal netAmount = BigDecimal.ZERO;

    private BigDecimal roundOff = BigDecimal.ZERO;

    private BigDecimal sgstAmount = BigDecimal.ZERO;

    private BigDecimal sgstPercentage = BigDecimal.ZERO;

    private BigDecimal shippingPrice = BigDecimal.ZERO;

    private BigDecimal taxPercentage = BigDecimal.ZERO;

    private BigDecimal totalCst = BigDecimal.ZERO;

    private BigDecimal totalPrice = BigDecimal.ZERO;

    private BigDecimal totalVat = BigDecimal.ZERO;

    private BigDecimal unitPrice = BigDecimal.ZERO;

    private BigDecimal vatPercentage = BigDecimal.ZERO;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceLink() {
        return invoiceLink;
    }

    public void setInvoiceLink(String invoiceLink) {
        this.invoiceLink = invoiceLink;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public BigDecimal getMrp() {
        return mrp;
    }

    public void setMrp(BigDecimal mrp) {
        this.mrp = mrp;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getUnitTaxes() {
        return unitTaxes;
    }

    public void setUnitTaxes(BigDecimal unitTaxes) {
        this.unitTaxes = unitTaxes;
    }

    public BigDecimal getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(BigDecimal totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    public BigDecimal getAdvancePayment() {
        return advancePayment;
    }

    public void setAdvancePayment(BigDecimal advancePayment) {
        this.advancePayment = advancePayment;
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

    public BigDecimal getCodAmount() {
        return codAmount;
    }

    public void setCodAmount(BigDecimal codAmount) {
        this.codAmount = codAmount;
    }

    public BigDecimal getCstPercentage() {
        return cstPercentage;
    }

    public void setCstPercentage(BigDecimal cstPercentage) {
        this.cstPercentage = cstPercentage;
    }

    public BigDecimal getGrossValue() {
        return grossValue;
    }

    public void setGrossValue(BigDecimal grossValue) {
        this.grossValue = grossValue;
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

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public BigDecimal getRoundOff() {
        return roundOff;
    }

    public void setRoundOff(BigDecimal roundOff) {
        this.roundOff = roundOff;
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

    public BigDecimal getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(BigDecimal shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public BigDecimal getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(BigDecimal taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public BigDecimal getTotalCst() {
        return totalCst;
    }

    public void setTotalCst(BigDecimal totalCst) {
        this.totalCst = totalCst;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalVat() {
        return totalVat;
    }

    public void setTotalVat(BigDecimal totalVat) {
        this.totalVat = totalVat;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getVatPercentage() {
        return vatPercentage;
    }

    public void setVatPercentage(BigDecimal vatPercentage) {
        this.vatPercentage = vatPercentage;
    }
}