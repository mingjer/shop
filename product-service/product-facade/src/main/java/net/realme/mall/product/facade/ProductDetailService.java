package net.realme.mall.product.facade;

import net.realme.mall.product.domain.response.ProductDetailFittings;
import net.realme.mall.product.domain.response.ProductDetailResponse;
import net.realme.mall.product.domain.response.SkuLiveStatusResponse;

import java.util.List;

/**
 * Created by 91000156 on 2018/9/5 11:12
 */
public interface ProductDetailService {

    // 根据skuId获取详情页所需信息
    ProductDetailResponse getDetailPageInfo(Integer skuId);

    // 根据skuId获取sku的初始状态
    SkuLiveStatusResponse getSkuLiveStatusInfo(Integer skuId);

    // 获取sku详情页面中的配件信息List
    List<ProductDetailFittings> getSkuFittings(Integer skuId);
}
