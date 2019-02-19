package net.realme.mall.product.beantool;

import net.realme.mall.product.domain.response.ProductDetailFittings;
import net.realme.mall.product.domain.response.ProductDetailResponse;
import net.realme.mall.product.model.ProductSku;
import net.realme.mall.product.model.SkuFittingView;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by 91000156 on 2018/9/5 13:00
 */
@Mapper(componentModel = "spring")
public interface ProductDetailConvert {

    // 商品信息PO转VO
    ProductDetailResponse toProductDetailResponse(ProductSku productSku1);

    // 配件信息PO转VO
    List<ProductDetailFittings> toDetailFittingList(List<SkuFittingView> skuFittingViewList);
}
