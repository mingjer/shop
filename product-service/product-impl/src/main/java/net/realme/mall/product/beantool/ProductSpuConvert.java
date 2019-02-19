package net.realme.mall.product.beantool;

import net.realme.mall.product.domain.ProductDto;
import net.realme.mall.product.domain.request.ProductGetUpdDto;
import net.realme.mall.product.model.ProductBase;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.product.beantool
 *
 * @author 91000044
 * @date 2018/8/23 11:40
 */
@Mapper(componentModel = "spring")
public interface ProductSpuConvert {

    ProductDto toProductDto(ProductBase productBase);

    ProductGetUpdDto toProductGetUpdDto(ProductBase productBase);

    ProductBase fromProductDto(ProductDto productDto);

    ProductBase fromProductGetUpdDto(ProductGetUpdDto productGetUpdDto);

    List<ProductDto> toProductDtoList(List<ProductBase> productBaseList);
}
