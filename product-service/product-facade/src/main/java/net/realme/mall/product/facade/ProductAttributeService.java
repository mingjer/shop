package net.realme.mall.product.facade;

import net.realme.mall.product.domain.ProductAttributeDto;
import net.realme.mall.product.domain.response.ProductAttributeResponse;

import java.util.List;

/**
 * Created by 91000156 on 2018/8/30 13:50
 */
public interface ProductAttributeService {

    List<ProductAttributeResponse> getProductAttributeList();
}
