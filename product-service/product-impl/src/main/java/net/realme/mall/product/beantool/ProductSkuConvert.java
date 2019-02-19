package net.realme.mall.product.beantool;

import net.realme.mall.product.domain.request.ProductSkuDto;
import net.realme.mall.product.domain.request.SpuInfoOnSkuRequest;
import net.realme.mall.product.domain.response.ProductSkuResponse;
import net.realme.mall.product.model.ProductSku;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by 91000156 on 2018/8/29 14:44
 */
@Mapper(componentModel = "spring")
public interface ProductSkuConvert {

    ProductSkuDto toProductSkuDto(ProductSku productSku);

    ProductSku toProductSku(ProductSkuDto productSkuDto);

    List<ProductSkuDto> toProductSkuDtoList(List<ProductSku> productSkuList);

    List<ProductSkuResponse> toProductSkuResList(List<ProductSku> productSkuList);

    ProductSkuResponse toSkuResponse(ProductSku productSku);

    ProductSku formSpuInfoToSkuInfo(SpuInfoOnSkuRequest spuInfoOnSkuRequest);
}
