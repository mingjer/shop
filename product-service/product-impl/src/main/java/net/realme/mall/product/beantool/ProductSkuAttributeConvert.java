package net.realme.mall.product.beantool;

import net.realme.mall.product.domain.ProductSkuAttributeDto;
import net.realme.mall.product.domain.request.ProductSkuAttributeRequest;
import net.realme.mall.product.domain.response.ProductSkuAttributeResponse;
import net.realme.mall.product.domain.response.SkuAttributeInSkuResponse;
import net.realme.mall.product.model.AttrValJoinSku;
import net.realme.mall.product.model.ProductSkuAttribute;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by 91000156 on 2018/8/29 15:54
 */
@Mapper(componentModel = "spring")
public interface ProductSkuAttributeConvert {

    ProductSkuAttributeDto toProductSkuAttributeDto(ProductSkuAttribute productSkuAttribute);

    ProductSkuAttribute toProductSkuAttribute(ProductSkuAttributeDto productSkuAttributeDto);

    List<ProductSkuAttributeDto> toProductSkuAttributeDtoList(List<ProductSkuAttribute> productSkuList);

    List<ProductSkuAttribute> toProductSkuAttributeList(List<ProductSkuAttributeDto> skuAttributeDtoList);

    List<ProductSkuAttributeResponse> toSkuAttrResponseList(List<ProductSkuAttribute> productSkuList);

    List<ProductSkuAttributeRequest> toSkuAttrRequestList(List<ProductSkuAttribute> productSkuList);

    List<ProductSkuAttributeDto> fromReqToSkuAttrDtoList(List<ProductSkuAttributeRequest> productSkuAttributeRequests);

    List<SkuAttributeInSkuResponse> toAttrValJoinSkuDtoInfo(List<AttrValJoinSku> attrValJoinSkuList);
}
