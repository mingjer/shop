package net.realme.mall.product.domain.response;

import net.realme.mall.product.domain.request.ProductSkuAttributeRequest;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 91000156 on 2018/8/30 19:08
 */
public class ProductSkuResponse implements Serializable {

    private static final long serialVersionUID = 5524247527193136030L;
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
     * EAN条形码
     */
    private String eanCode;

    /**
     * 物料代码
     */
    private String erpCode;

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
     * 创建时间
     */
    private Long createdTime;

    /**
     * 更新时间
     */
    private Long updatedTime;

    /**
     * 创建者
     */
    private Integer createdBy;

    /**
     * 更新者
     */
    private Integer updatedBy;

    /**
     * 状态：0-上架，1-已下架
     */
    private Byte shelfStatus;

    /**
     * sku属性组合及已选组合
     */
    private List<SkuAttributeInSkuResponse> selOption;

    /**
     * 配件的skuIdList
     */
    private List<Integer> fittingSkuIdList;

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

    /**
     * 获取促销名称
     *
     * @return marketing_name - 促销名称
     */
    public String getMarketingName() {
        return marketingName;
    }

    /**
     * 设置促销名称
     *
     * @param marketingName 促销名称
     */
    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName == null ? null : marketingName.trim();
    }

    /**
     * 获取副标题
     *
     * @return sub_title - 副标题
     */
    public String getSubTitle() {
        return subTitle;
    }

    /**
     * 设置副标题
     *
     * @param subTitle 副标题
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }

    /**
     * 获取短描述
     *
     * @return short_desc - 短描述
     */
    public String getShortDesc() {
        return shortDesc;
    }

    /**
     * 设置短描述
     *
     * @param shortDesc 短描述
     */
    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc == null ? null : shortDesc.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取站点编码
     *
     * @return site_code - 站点编码
     */
    public String getSiteCode() {
        return siteCode;
    }

    /**
     * 设置站点编码
     *
     * @param siteCode 站点编码
     */
    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode == null ? null : siteCode.trim();
    }

    public String getEanCode() {
        return eanCode;
    }

    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
    }

    /**
     * 获取机型
     *
     * @return model - 机型
     */
    public String getModel() {
        return model;
    }

    public String getErpCode() {
        return erpCode;
    }

    public void setErpCode(String erpCode) {
        this.erpCode = erpCode;
    }

    /**
     * 设置机型
     *
     * @param model 机型
     */
    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    /**
     * 获取包装-长
     *
     * @return pack_length - 包装-长
     */
    public Double getPackLength() {
        return packLength;
    }

    /**
     * 设置包装-长
     *
     * @param packLength 包装-长
     */
    public void setPackLength(Double packLength) {
        this.packLength = packLength;
    }

    /**
     * 获取包装-宽
     *
     * @return pack_width - 包装-宽
     */
    public Double getPackWidth() {
        return packWidth;
    }

    /**
     * 设置包装-宽
     *
     * @param packWidth 包装-宽
     */
    public void setPackWidth(Double packWidth) {
        this.packWidth = packWidth;
    }

    /**
     * 获取包装-高
     *
     * @return pack_height - 包装-高
     */
    public Double getPackHeight() {
        return packHeight;
    }

    /**
     * 设置包装-高
     *
     * @param packHeight 包装-高
     */
    public void setPackHeight(Double packHeight) {
        this.packHeight = packHeight;
    }

    /**
     * 获取包装重量(g)
     *
     * @return pack_weight - 包装重量(g)
     */
    public Double getPackWeight() {
        return packWeight;
    }

    /**
     * 设置包装重量(g)
     *
     * @param packWeight 包装重量(g)
     */
    public void setPackWeight(Double packWeight) {
        this.packWeight = packWeight;
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取自定义Url
     *
     * @return user_defined_url - 自定义Url
     */
    public String getUserDefinedUrl() {
        return userDefinedUrl;
    }

    /**
     * 设置自定义Url
     *
     * @param userDefinedUrl 自定义Url
     */
    public void setUserDefinedUrl(String userDefinedUrl) {
        this.userDefinedUrl = userDefinedUrl == null ? null : userDefinedUrl.trim();
    }

    /**
     * 获取销售状态 0 不开卖 1 开卖 2 预约
     *
     * @return sale_status - 销售状态 0 不开卖 1 开卖 2 预约
     */
    public Byte getSaleStatus() {
        return saleStatus;
    }

    /**
     * 设置销售状态 0 不开卖 1 开卖 2 预约
     *
     * @param saleStatus 销售状态 0 不开卖 1 开卖 2 预约
     */
    public void setSaleStatus(Byte saleStatus) {
        this.saleStatus = saleStatus;
    }

    /**
     * 获取销售预约时间的时区
     *
     * @return time_zone - 销售预约时间的时区
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * 设置销售预约时间的时区
     *
     * @param timeZone 销售预约时间的时区
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone == null ? null : timeZone.trim();
    }

    /**
     * 获取开卖时间
     *
     * @return sale_start - 开卖时间
     */
    public Long getSaleStart() {
        return saleStart;
    }

    /**
     * 设置开卖时间
     *
     * @param saleStart 开卖时间
     */
    public void setSaleStart(Long saleStart) {
        this.saleStart = saleStart;
    }

    /**
     * 获取停止销售时间
     *
     * @return sale_end - 停止销售时间
     */
    public Long getSaleEnd() {
        return saleEnd;
    }

    /**
     * 设置停止销售时间
     *
     * @param saleEnd 停止销售时间
     */
    public void setSaleEnd(Long saleEnd) {
        this.saleEnd = saleEnd;
    }

    /**
     * 获取预约开始时间
     *
     * @return reserve_start - 预约开始时间
     */
    public Long getReserveStart() {
        return reserveStart;
    }

    /**
     * 设置预约开始时间
     *
     * @param reserveStart 预约开始时间
     */
    public void setReserveStart(Long reserveStart) {
        this.reserveStart = reserveStart;
    }

    /**
     * 获取预约结束时间
     *
     * @return reserve_end - 预约结束时间
     */
    public Long getReserveEnd() {
        return reserveEnd;
    }

    /**
     * 设置预约结束时间
     *
     * @param reserveEnd 预约结束时间
     */
    public void setReserveEnd(Long reserveEnd) {
        this.reserveEnd = reserveEnd;
    }

    /**
     * 获取剩余分钟数开始倒计时
     *
     * @return countdown_within - 剩余分钟数开始倒计时
     */
    public String getCountdownWithin() {
        return countdownWithin;
    }

    /**
     * 设置剩余分钟数开始倒计时
     *
     * @param countdownWithin 剩余分钟数开始倒计时
     */
    public void setCountdownWithin(String countdownWithin) {
        this.countdownWithin = countdownWithin == null ? null : countdownWithin.trim();
    }

    /**
     * 获取每单最大数量
     *
     * @return max_quantity - 每单最大数量
     */
    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    /**
     * 设置每单最大数量
     *
     * @param maxQuantity 每单最大数量
     */
    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    /**
     * 获取橱窗图按json存储
     *
     * @return overview_uri - 橱窗图按json存储
     */
    public String getOverviewUri() {
        return overviewUri;
    }

    /**
     * 设置橱窗图按json存储
     *
     * @param overviewUri 橱窗图按json存储
     */
    public void setOverviewUri(String overviewUri) {
        this.overviewUri = overviewUri == null ? null : overviewUri.trim();
    }

    /**
     * 获取参数图片
     *
     * @return spec_image - 参数图片
     */
    public String getSpecImage() {
        return specImage;
    }

    /**
     * 设置参数图片
     *
     * @param specImage 参数图片
     */
    public void setSpecImage(String specImage) {
        this.specImage = specImage == null ? null : specImage.trim();
    }

    /**
     * 获取PC-包装图片-多张
     *
     * @return pc_pack_image - PC-包装图片-多张
     */
    public String getPcPackImage() {
        return pcPackImage;
    }

    /**
     * 设置PC-包装图片-多张
     *
     * @param pcPackImage PC-包装图片-多张
     */
    public void setPcPackImage(String pcPackImage) {
        this.pcPackImage = pcPackImage == null ? null : pcPackImage.trim();
    }

    /**
     * 获取移动端-包装图片-多张
     *
     * @return mobile_pack_image - 移动端-包装图片-多张
     */
    public String getMobilePackImage() {
        return mobilePackImage;
    }

    /**
     * 设置移动端-包装图片-多张
     *
     * @param mobilePackImage 移动端-包装图片-多张
     */
    public void setMobilePackImage(String mobilePackImage) {
        this.mobilePackImage = mobilePackImage == null ? null : mobilePackImage.trim();
    }

    /**
     * 获取seo优化标题
     *
     * @return seo_title - seo优化标题
     */
    public String getSeoTitle() {
        return seoTitle;
    }

    /**
     * 设置seo优化标题
     *
     * @param seoTitle seo优化标题
     */
    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle == null ? null : seoTitle.trim();
    }

    /**
     * 获取seo优化关键字
     *
     * @return seo_keywords - seo优化关键字
     */
    public String getSeoKeywords() {
        return seoKeywords;
    }

    /**
     * 设置seo优化关键字
     *
     * @param seoKeywords seo优化关键字
     */
    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords == null ? null : seoKeywords.trim();
    }

    /**
     * 获取seo优化描述
     *
     * @return seo_desc - seo优化描述
     */
    public String getSeoDesc() {
        return seoDesc;
    }

    /**
     * 设置seo优化描述
     *
     * @param seoDesc seo优化描述
     */
    public void setSeoDesc(String seoDesc) {
        this.seoDesc = seoDesc == null ? null : seoDesc.trim();
    }

    /**
     * 获取产品站概览url
     *
     * @return item_sites_url - 产品站概览url
     */
    public String getItemSitesUrl() {
        return itemSitesUrl;
    }

    /**
     * 设置产品站概览url
     *
     * @param itemSitesUrl 产品站概览url
     */
    public void setItemSitesUrl(String itemSitesUrl) {
        this.itemSitesUrl = itemSitesUrl == null ? null : itemSitesUrl.trim();
    }

    /**
     * 获取sku属性规格权重设置 json存储
     *
     * @return spec_weights - sku属性规格权重设置 json存储
     */
    public String getSpecWeights() {
        return specWeights;
    }

    /**
     * 设置sku属性规格权重设置 json存储
     *
     * @param specWeights sku属性规格权重设置 json存储
     */
    public void setSpecWeights(String specWeights) {
        this.specWeights = specWeights == null ? null : specWeights.trim();
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Long getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取更新时间
     *
     * @return updated_time - 更新时间
     */
    public Long getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 设置更新时间
     *
     * @param updatedTime 更新时间
     */
    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * 获取创建者
     *
     * @return created_by - 创建者
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建者
     *
     * @param createdBy 创建者
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取更新者
     *
     * @return updated_by - 更新者
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置更新者
     *
     * @param updatedBy 更新者
     */
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 获取状态：0-上架，1-已下架
     *
     * @return shelf_status - 状态：0-上架，1-已下架
     */
    public Byte getShelfStatus() {
        return shelfStatus;
    }

    /**
     * 设置状态：0-上架，1-已下架
     *
     * @param shelfStatus 状态：0-上架，1-已下架
     */
    public void setShelfStatus(Byte shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    public List<SkuAttributeInSkuResponse> getSelOption() {
        return selOption;
    }

    public void setSelOption(List<SkuAttributeInSkuResponse> selOption) {
        this.selOption = selOption;
    }

    public List<Integer> getFittingSkuIdList() {
        return fittingSkuIdList;
    }

    public void setFittingSkuIdList(List<Integer> fittingSkuIdList) {
        this.fittingSkuIdList = fittingSkuIdList;
    }
}
