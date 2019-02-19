package net.realme.mall.oms.product.req;

import java.io.Serializable;

/**
 * Created by 91000156 on 2018/8/29 11:44
 */
public class SkuUpdateReq extends SkuCreateReq implements Serializable {

    private static final long serialVersionUID = 4126139791309987173L;

    private Integer skuId;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }
}
