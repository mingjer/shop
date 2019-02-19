package net.realme.mall.product.beantool;

import net.realme.mall.product.domain.request.SpuAttrUpdate;
import net.realme.mall.product.domain.response.ProductAttributeValueResponse;
import net.realme.mall.product.model.ProductAttributeValue;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by 91000156 on 2018/8/30 14:34
 */
@Mapper(componentModel = "spring")
public interface ProductAttributeValueConvert {

    List<ProductAttributeValueResponse> toProductAttributeValResList(List<ProductAttributeValue> attributeValueList);

    List<SpuAttrUpdate> toProductAttributeUpdate(List<ProductAttributeValue> attributeValueList);
}
