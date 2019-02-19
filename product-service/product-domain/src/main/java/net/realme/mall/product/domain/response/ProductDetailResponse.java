package net.realme.mall.product.domain.response;

import net.realme.mall.product.domain.ProductAttributesViewDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by 91000156 on 2018/9/5 11:14
 */
public class ProductDetailResponse implements Serializable {


    private static final long serialVersionUID = -5319626984337417984L;
    /**
     * sku id
     */
    private Integer skuId;

    /**
     * 商品spu id
     */
    private Integer productId;

    /**
     * 商品名称
     */
    private String productName;
    /**
     * sku名称
     */
    private String skuName;

    /**
     * 促销名称
     */
    private String marketingName;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 短描述
     */
    private String shortDesc;

    /**
     * 描述
     */
    private String description;

    /**
     * 站点编码
     */
    private String siteCode;

    /**
     * 机型
     */
    private String model;

    /**
     * 包装-长
     */
    private Double packLength;

    /**
     * 包装-宽
     */
    private Double packWidth;

    /**
     * 包装-高
     */
    private Double packHeight;

    /**
     * 包装重量(g)
     */
    private Double packWeight;

    /**
     *  货币符号
     */
    private String priceSymbol;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 自定义Url
     */
    private String userDefinedUrl;

    /**
     * 销售状态 0 不开卖 1 开卖 2 预约
     */
    private Byte saleStatus;

    /**
     * 销售预约时间的时区
     */
    private String timeZone;

    /**
     * 开卖时间
     */
    private Long saleStart;

    /**
     * 停止销售时间
     */
    private Long saleEnd;

    /**
     * 预约开始时间
     */
    private Long reserveStart;

    /**
     * 预约结束时间
     */
    private Long reserveEnd;

    /**
     * 剩余分钟数开始倒计时
     */
    private String countdownWithin;

    /**
     * 每单最大数量
     */
    private Integer maxQuantity;

    /**
     * 橱窗图按json存储
     */
    private String overviewUri;

    /**
     * 参数图片
     */
    private String specImage;

    /**
     * PC-包装图片-多张
     */
    private String pcPackImage;

    /**
     * 移动端-包装图片-多张
     */
    private String mobilePackImage;

    /**
     * seo优化标题
     */
    private String seoTitle;

    /**
     * seo优化关键字
     */
    private String seoKeywords;

    /**
     * seo优化描述
     */
    private String seoDesc;

    /**
     * 产品站概览url
     */
    private String itemSitesUrl;

    /**
     * sku属性规格权重设置 json存储
     */
    private String specWeights;

    /**
     * 状态：0-上架，1-已下架
     */
    private Byte shelfStatus;

    /**
     * sku 属性组
     */
    private Map<String,String> skuAttrGroup;

    /**
     * product 颜色相关属性
     */
    private List<ProductAttributesViewDto> colorList;

    /**
     * product 配置相关属性
     */
    private List<ProductAttributesViewDto> specList;

    /**
     * 商品sku对应的配件信息
     */
    private List<ProductDetailFittings> skuFittingList;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPackLength() {
        return packLength;
    }

    public void setPackLength(Double packLength) {
        this.packLength = packLength;
    }

    public Double getPackWidth() {
        return packWidth;
    }

    public void setPackWidth(Double packWidth) {
        this.packWidth = packWidth;
    }

    public Double getPackHeight() {
        return packHeight;
    }

    public void setPackHeight(Double packHeight) {
        this.packHeight = packHeight;
    }

    public Double getPackWeight() {
        return packWeight;
    }

    public void setPackWeight(Double packWeight) {
        this.packWeight = packWeight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUserDefinedUrl() {
        return userDefinedUrl;
    }

    public void setUserDefinedUrl(String userDefinedUrl) {
        this.userDefinedUrl = userDefinedUrl;
    }

    public Byte getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Byte saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Long getSaleStart() {
        return saleStart;
    }

    public void setSaleStart(Long saleStart) {
        this.saleStart = saleStart;
    }

    public Long getSaleEnd() {
        return saleEnd;
    }

    public void setSaleEnd(Long saleEnd) {
        this.saleEnd = saleEnd;
    }

    public Long getReserveStart() {
        return reserveStart;
    }

    public void setReserveStart(Long reserveStart) {
        this.reserveStart = reserveStart;
    }

    public Long getReserveEnd() {
        return reserveEnd;
    }

    public void setReserveEnd(Long reserveEnd) {
        this.reserveEnd = reserveEnd;
    }

    public String getCountdownWithin() {
        return countdownWithin;
    }

    public void setCountdownWithin(String countdownWithin) {
        this.countdownWithin = countdownWithin;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public String getOverviewUri() {
        return overviewUri;
    }

    public void setOverviewUri(String overviewUri) {
        this.overviewUri = overviewUri;
    }

    public String getSpecImage() {
        return specImage;
    }

    public void setSpecImage(String specImage) {
        this.specImage = specImage;
    }

    public String getPcPackImage() {
        return pcPackImage;
    }

    public void setPcPackImage(String pcPackImage) {
        this.pcPackImage = pcPackImage;
    }

    public String getMobilePackImage() {
        return mobilePackImage;
    }

    public void setMobilePackImage(String mobilePackImage) {
        this.mobilePackImage = mobilePackImage;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    public String getSeoDesc() {
        return seoDesc;
    }

    public void setSeoDesc(String seoDesc) {
        this.seoDesc = seoDesc;
    }

    public Byte getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(Byte shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    public String getItemSitesUrl() {
        return itemSitesUrl;
    }

    public void setItemSitesUrl(String itemSitesUrl) {
        this.itemSitesUrl = itemSitesUrl;
    }

    public List<ProductAttributesViewDto> getColorList() {
        return colorList;
    }

    public void setColorList(List<ProductAttributesViewDto> colorList) {
        this.colorList = colorList;
    }

    public List<ProductAttributesViewDto> getSpecList() {
        return specList;
    }

    public void setSpecList(List<ProductAttributesViewDto> specList) {
        this.specList = specList;
    }

    public String getSpecWeights() {
        return specWeights;
    }

    public void setSpecWeights(String specWeights) {
        this.specWeights = specWeights;
    }

    public String getPriceSymbol() {
        return priceSymbol;
    }

    public void setPriceSymbol(String priceSymbol) {
        this.priceSymbol = priceSymbol;
    }

    public Map<String, String> getSkuAttrGroup() {
        return skuAttrGroup;
    }

    public void setSkuAttrGroup(Map<String, String> skuAttrGroup) {
        this.skuAttrGroup = skuAttrGroup;
    }

    public List<ProductDetailFittings> getSkuFittingList() {
        return skuFittingList;
    }

    public void setSkuFittingList(List<ProductDetailFittings> skuFittingList) {
        this.skuFittingList = skuFittingList;
    }
}
