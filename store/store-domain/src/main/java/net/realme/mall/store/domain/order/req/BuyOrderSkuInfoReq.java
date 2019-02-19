package net.realme.mall.store.domain.order.req;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购买商品信息
 *
 * @author 91000182
 * @date 2018/9/8
 */
public class BuyOrderSkuInfoReq implements Serializable {

    @ApiModelProperty(value = "SKU_ID")
    private Integer skuId;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    /**
     * 数量
     */
    @ApiModelProperty(value = "购买数量")
    private int count;

    /**
     * 商品类型
     */
    @ApiModelProperty(value = "商品类型")
    private Integer skuType;// 1、普通商品 2、配件

    /**
     * 商品URL
     */
    @ApiModelProperty(value = "商品URL")
    private int url;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public Integer getSkuType() {
        return skuType;
    }

    public void setSkuType(Integer skuType) {
        this.skuType = skuType;
    }

    @Override
    public String toString() {
        return "BuyOrderSkuInfoReq [skuId=" + skuId + ", price=" + price + ", count=" + count + ", skuType=" + skuType
                + ", url=" + url + "]";
    }

}
