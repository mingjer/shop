package net.realme.mall.oms.product.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import net.realme.mall.product.domain.request.ProductSkuAttributeRequest;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 91000156 on 2018/8/29 10:25
 */
@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkuCreateReq implements Serializable {

    private static final long serialVersionUID = -3870925821353394201L;

    // 商品ID
    private Integer productId;

    // 站点编码
    private String siteCode;

    // 商品名称
    private String productName;

    // SKU名称
    private String skuName;

    // 副标题
    private String subTitle;

    // 短描述
    private String shortDesc;

    // 物料编码
    private String erpCode;

    // EAN条形码
    private String eanCode;

    // 重量
    private Double packWeight;

    // 包装尺寸-长
    private Double packLength;

    // 包装尺寸-宽
    private Double packWidth;

    // 包装尺寸-高
    private Double packHeight;

    // 价格-卢比
    private BigDecimal price;

    // 最大购买数量
    private Integer maxQuantity;

    // 自定义url
    private String userDefinedUrl;

    // 开卖状态：0 不开卖 1 开卖 2 预约
    private Byte saleStatus;

    // 所选时区
    private String timeZone;

    // 开卖开始时间
    private Long saleStart;

    // 开卖结束时间
    private Long saleEnd;

    // 预约开始时间
    private Long reserveStart;

    // 预约结束时间
    private Long reserveEnd;

    // 倒计时时间
    private String countdownWithin;

    // 橱窗图json组合
    private String overviewUri;

    // 包装图组合-PC端
    private String pcPackImage;

    // 包装图组合-移动端
    private String mobilePackImage;

    // SEO优化-标题
    private String seoTitle;

    // SEO优化-描述
    private String seoDesc;

    // SEO优化-关键字
    private String seoKeywords;

    // 上下架状态
    private Byte shelfStatus;

    private List<ProductSkuAttributeRequest> selOption;

    public List<ProductSkuAttributeRequest> getSelOption() {
        return selOption;
    }

    public void setSelOption(List<ProductSkuAttributeRequest> selOption) {
        this.selOption = selOption;
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

    public String getErpCode() {
        return erpCode;
    }

    public void setErpCode(String erpCode) {
        this.erpCode = erpCode;
    }

    public Double getPackWeight() {
        return packWeight;
    }

    public void setPackWeight(Double packWeight) {
        this.packWeight = packWeight;
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

    public String getEanCode() {
        return eanCode;
    }

    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
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

    public String getOverviewUri() {
        return overviewUri;
    }

    public void setOverviewUri(String overviewUri) {
        this.overviewUri = overviewUri;
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

    public String getSeoDesc() {
        return seoDesc;
    }

    public void setSeoDesc(String seoDesc) {
        this.seoDesc = seoDesc;
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Byte getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(Byte shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
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

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    @Override
    public String toString() {
        return "SkuCreateReq{" +
                "productId=" + productId +
                ", siteCode='" + siteCode + '\'' +
                ", productName='" + productName + '\'' +
                ", skuName='" + skuName + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", erpCode='" + erpCode + '\'' +
                ", eanCode='" + eanCode + '\'' +
                ", packWeight=" + packWeight +
                ", packLength=" + packLength +
                ", packWidth=" + packWidth +
                ", packHeight=" + packHeight +
                ", price=" + price +
                ", maxQuantity=" + maxQuantity +
                ", userDefinedUrl='" + userDefinedUrl + '\'' +
                ", saleStatus=" + saleStatus +
                ", timeZone='" + timeZone + '\'' +
                ", saleStart=" + saleStart +
                ", saleEnd=" + saleEnd +
                ", reserveStart=" + reserveStart +
                ", reserveEnd=" + reserveEnd +
                ", countdownWithin='" + countdownWithin + '\'' +
                ", overviewUri='" + overviewUri + '\'' +
                ", pcPackImage='" + pcPackImage + '\'' +
                ", mobilePackImage='" + mobilePackImage + '\'' +
                ", seoTitle='" + seoTitle + '\'' +
                ", seoDesc='" + seoDesc + '\'' +
                ", seoKeywords='" + seoKeywords + '\'' +
                ", shelfStatus=" + shelfStatus +
                ", selOption=" + selOption +
                '}';
    }
}
