package net.realme.scm.wms.domain.delhivery;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import net.realme.scm.wms.domain.PushOrderPayload;

import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.scm.wms.domain.delhivery
 *
 * @author 91000044
 * @date 2018/9/19 20:47
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DelhiveryOrder extends PushOrderPayload {

    private static final long serialVersionUID = 3342455889306655308L;

    @JsonSerialize(using = ToStringSerializer.class)
    private String orderNumber;


    private String orderDate;

    private DelhiveryConsignee consignee;


    private List<DelhiverySubOrder> subOrders;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public DelhiveryConsignee getConsignee() {
        return consignee;
    }

    public void setConsignee(DelhiveryConsignee consignee) {
        this.consignee = consignee;
    }

    public List<DelhiverySubOrder> getSubOrders() {
        return subOrders;
    }

    public void setSubOrders(List<DelhiverySubOrder> subOrders) {
        this.subOrders = subOrders;
    }
}
