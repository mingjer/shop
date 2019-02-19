package net.realme.mall.store.domain.order.rsp;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单商品信息--确认页面用
 *
 * @author 91000182
 * @date 2018/9/5
 */
public class OrderConfirmItems implements Serializable {

    private Integer skuId;

    private String skuName;

    private String imageUrl;

    private String color;

    private String skuSpec;

    private BigDecimal price;

    /**
     * 数量
     */
    private int count;

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

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSkuSpec() {
        return skuSpec;
    }

    public void setSkuSpec(String skuSpec) {
        this.skuSpec = skuSpec;
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
}
