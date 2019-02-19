package net.realme.mall.product.beantool;

import net.realme.mall.product.domain.ProductFittingDto;
import net.realme.mall.product.model.ProductFitting;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by 91000156 on 2018/9/18 10:58
 */
@Mapper(componentModel = "spring")
public interface ProductFittingConvert {

    ProductFittingDto toFittingDto(ProductFitting productFitting);

    List<ProductFittingDto> toFittingDtoList(List<ProductFitting> productFittingList);
}
