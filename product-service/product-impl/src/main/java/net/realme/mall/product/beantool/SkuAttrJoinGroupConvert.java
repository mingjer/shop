package net.realme.mall.product.beantool;

import net.realme.mall.product.domain.ProductAttributesViewDto;
import net.realme.mall.product.model.ProductAttributesView;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by 91000156 on 2018/9/7 19:17
 */
@Mapper(componentModel = "spring")
public interface SkuAttrJoinGroupConvert {

    List<ProductAttributesViewDto> toSkuAttrJoinGroupDtoList(List<ProductAttributesView> skuAttrJoinGroupList);
}
