package net.realme.mall.oms.product.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 91000156 on 2018/9/18 15:09
 */
public class EditFittingReq implements Serializable {

    private static final long serialVersionUID = 1525803040093591768L;

    @JsonProperty(value = "main_sku_id")
    private Integer mainSkuId;

    @JsonProperty(value = "part_sku_id")
    private List<Integer> partSkuId;

    public Integer getMainSkuId() {
        return mainSkuId;
    }

    public void setMainSkuId(Integer mainSkuId) {
        this.mainSkuId = mainSkuId;
    }

    public List<Integer> getPartSkuId() {
        return partSkuId;
    }

    public void setPartSkuId(List<Integer> partSkuId) {
        this.partSkuId = partSkuId;
    }

    @Override
    public String toString() {
        String msg = "EditFittingReq{" + "mainSkuId=" + mainSkuId + ", partSkuId= [";
        if (!partSkuId.isEmpty()) {
            for (Integer i : partSkuId) {
                msg = msg + i + ",";
            }
            msg = msg + "]}";
        }
        return msg;
    }
}
