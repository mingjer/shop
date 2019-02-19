package net.realme.mall.oms.order.rsp;

import java.io.Serializable;

/**
 * OMS订单详情-发票信息
 */
public class OrderDetailInvoiceRsp implements Serializable {

    /**
     * 发票链接
     */
    private String invoiceUrl;

    public String getInvoiceUrl() {
        return invoiceUrl;
    }

    public void setInvoiceUrl(String invoiceUrl) {
        this.invoiceUrl = invoiceUrl;
    }
}
