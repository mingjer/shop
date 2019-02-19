package net.realme.mall.product.beantool;

import net.realme.mall.product.domain.response.ProductAttributeResponse;
import net.realme.mall.product.model.ProductAttribute;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by 91000156 on 2018/8/30 13:53
 */
@Mapper(componentModel = "spring")
public interface ProductAttributeConvert {

    List<ProductAttributeResponse> toProductAttributeResList(List<ProductAttribute> attributeList);
}
