package net.realme.scm.wms.domain;

public class WmsShippingNotifyDto {
    private Integer id;

    /**
     * 订单id
     */
    private String orderNo;

    /**
     * 仓库地址
     */
    private String fulfillmentCenter;

    /**
     * 运输时间
     */
    private String deliveredAt;

    /**
     * 发货时间
     */
    private String invoiceUrl;

    /**
     * 运单号
     */
    private String waybill;

    /**
     * 默认delhivery
     */
    private String vendor;

    /**
     * 创建时间
     */
    private Long createdAt;

    /**
     * 更新时间
     */
    private Long updatedAt;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单id
     *
     * @return order_no - 订单id
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单id
     *
     * @param orderNo 订单id
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * 获取仓库地址
     *
     * @return fulfillment_center - 仓库地址
     */
    public String getFulfillmentCenter() {
        return fulfillmentCenter;
    }

    /**
     * 设置仓库地址
     *
     * @param fulfillmentCenter 仓库地址
     */
    public void setFulfillmentCenter(String fulfillmentCenter) {
        this.fulfillmentCenter = fulfillmentCenter == null ? null : fulfillmentCenter.trim();
    }

    /**
     * 获取运输时间
     *
     * @return delivered_at - 运输时间
     */
    public String getDeliveredAt() {
        return deliveredAt;
    }

    /**
     * 设置运输时间
     *
     * @param deliveredAt 运输时间
     */
    public void setDeliveredAt(String deliveredAt) {
        this.deliveredAt = deliveredAt == null ? null : deliveredAt.trim();
    }

    /**
     * 获取发货时间
     *
     * @return invoice_url - 发货时间
     */
    public String getInvoiceUrl() {
        return invoiceUrl;
    }

    /**
     * 设置发货时间
     *
     * @param invoiceUrl 发货时间
     */
    public void setInvoiceUrl(String invoiceUrl) {
        this.invoiceUrl = invoiceUrl == null ? null : invoiceUrl.trim();
    }

    /**
     * 获取运单号
     *
     * @return waybill - 运单号
     */
    public String getWaybill() {
        return waybill;
    }

    /**
     * 设置运单号
     *
     * @param waybill 运单号
     */
    public void setWaybill(String waybill) {
        this.waybill = waybill == null ? null : waybill.trim();
    }

    /**
     * 获取默认delhivery
     *
     * @return vendor - 默认delhivery
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * 设置默认delhivery
     *
     * @param vendor 默认delhivery
     */
    public void setVendor(String vendor) {
        this.vendor = vendor == null ? null : vendor.trim();
    }

    /**
     * 获取创建时间
     *
     * @return created_at - 创建时间
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置创建时间
     *
     * @param createdAt 创建时间
     */
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取更新时间
     *
     * @return updated_at - 更新时间
     */
    public Long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 设置更新时间
     *
     * @param updatedAt 更新时间
     */
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }
}