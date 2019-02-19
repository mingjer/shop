package net.realme.scm.wms.domain.delhivery;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.scm.wms.domain.delhivery
 *
 * @author 91000044
 * @date 2018/9/20 15:33
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DelhiveryInventoryNotification implements Serializable {

    private static final long serialVersionUID = 78553768190713308L;

    private List<NotifiedInventory> inventory;

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class NotifiedInventory implements Serializable {
        private static final long serialVersionUID = 8697276730634834454L;
        private String sku;
        private String fulfillmentCenterName;
        private Integer qty;

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getFulfillmentCenterName() {
            return fulfillmentCenterName;
        }

        public void setFulfillmentCenterName(String fulfillmentCenterName) {
            this.fulfillmentCenterName = fulfillmentCenterName;
        }

        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }

        @Override
        public String toString() {
            return "NotifiedInventory{" +
                    "sku='" + sku + '\'' +
                    ", fulfillmentCenterName='" + fulfillmentCenterName + '\'' +
                    ", qty=" + qty +
                    '}';
        }
    }

    public List<NotifiedInventory> getInventory() {
        return inventory;
    }

    public void setInventory(List<NotifiedInventory> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "DelhiveryInventoryNotification{" +
                "inventory=" + inventory +
                '}';
    }
}
