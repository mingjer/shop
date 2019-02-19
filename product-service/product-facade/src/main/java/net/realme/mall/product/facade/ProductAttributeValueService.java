package net.realme.mall.product.facade;

import net.realme.framework.util.dto.ResultT;
import net.realme.mall.product.domain.request.SpuAttrUpdate;
import net.realme.mall.product.domain.response.ProductAttributeValueResponse;

import java.util.List;
import java.util.Map;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.product.facade
 *
 * @author 91000044
 * @date 2018/8/23 12:38
 */
public interface ProductAttributeValueService {

    ResultT<Integer> add(Integer productId, Map<Integer, List<String>> options);

    ResultT<Integer> updInsert(Integer productId, Map<Integer, List<SpuAttrUpdate>> options);

    int deleteByProductId(Integer productId);

    Map<Integer, List<String>> getOptionsByProductId(Integer productId);

    Map<Integer, List<SpuAttrUpdate>> getSpuAttrUpdateByProductId(Integer productId);

    List<ProductAttributeValueResponse> getAttrValueResponseList(Integer productId);
}
