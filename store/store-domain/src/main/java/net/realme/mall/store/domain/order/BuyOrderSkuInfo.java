package net.realme.mall.store.domain.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 缓存商品信息
 *
 * @author 91000182
 * @date 2018/9/3
 */
public class BuyOrderSkuInfo implements Serializable {

    private Integer skuId;

    private BigDecimal price;

    /**
     * 数量
     */
    private int count;

    /**
     * 商品类型
     */
    private Integer skuType;// 1、普通商品 2、配件

    /**
     * 商品URL
     */
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
}
