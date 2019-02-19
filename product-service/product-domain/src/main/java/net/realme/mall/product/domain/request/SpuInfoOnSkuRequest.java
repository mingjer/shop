package net.realme.mall.product.domain.request;

import java.io.Serializable;

/**
 * Created by 91000156 on 2018/8/31 19:14
 */
public class SpuInfoOnSkuRequest implements Serializable {

    private static final long serialVersionUID = 7599185198454394877L;

    private Integer skuId;

    // 产品站概览url
    private String itemSitesUrl;

    // sku属性规格权重设置 json存储 id-list
    private String specWeights;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getItemSitesUrl() {
        return itemSitesUrl;
    }

    public void setItemSitesUrl(String itemSitesUrl) {
        this.itemSitesUrl = itemSitesUrl;
    }

    public String getSpecWeights() {
        return specWeights;
    }

    public void setSpecWeights(String specWeights) {
        this.specWeights = specWeights;
    }

    @Override
    public String toString() {
        return "SpuInfoOnSkuRequest{" +
                "skuId='" + skuId + '\'' +
                ", itemSitesUrl='" + itemSitesUrl + '\'' +
                ", specWeights='" + specWeights + '\'' +
                '}';
    }
}
