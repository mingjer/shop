package net.realme.mall.product.dao;

import net.realme.mall.product.model.SkuDetailUrisView;
import net.realme.mall.product.model.ProductAttributesView;
import net.realme.mall.product.model.SkuFittingView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 91000156 on 2018/9/7 14:56
 */
public interface ProductDetailPageMapper {

    // 颜色属性id
    Integer colorAttrId = 1;
    // 配置属性id
    Integer specAttrId = 2;

    @Select("select symbol from rm_basics.currency INNER JOIN rm_basics.site on currency.abbr=site.currency_abbr where site_code= #{siteCode}")
    String getPriceSymbolMapper(String siteCode);

    @Select("select * from product_attributes_view where product_id = #{productId} and attr_id = #{attrId} order by sequence asc")
    @Results({
            @Result(property = "productId", column = "product_id"),
            @Result(property = "attrId", column = "attr_id"),
            @Result(property = "attrValId", column = "attr_val_id"),
            @Result(property = "attrVal", column = "attr_val")
    })
    List<ProductAttributesView> getSkuAttrListByType(@Param("productId") Integer productId, @Param("attrId") Integer attrId);

    @Select("select * from sku_detail_uris_view where product_id = #{productId}")
    @Results({
            @Result(property = "productId", column = "product_id"),
            @Result(property = "skuId", column = "sku_id"),
            @Result(property = "userDefinedUrl", column = "user_defined_url"),
            @Result(property = "colorId", column = "color_id"),
            @Result(property = "specId", column = "spec_id"),
    })
    List<SkuDetailUrisView> getJumpUriInfo(Integer productId);

    @Select("select * from sku_fitting_view where main_sku_id = #{skuId}")
    @Results({
            @Result(property = "partSkuId", column = "part_sku_id"),
            @Result(property = "mainSkuId", column = "main_sku_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "productName", column = "product_name"),
            @Result(property = "skuName", column = "sku_name"),
            @Result(property = "price", column = "price"),
            @Result(property = "siteCode", column = "site_code"),
            @Result(property = "itemSitesUrl", column = "item_sites_url"),
            @Result(property = "overviewUri", column = "overview_uri"),
    })
    List<SkuFittingView> getDetailFittingByMainSkuId(@Param("skuId") Integer skuId);
}
