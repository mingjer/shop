package net.realme.mall.product.facade;

import net.realme.framework.util.dto.ResultT;
import net.realme.framework.util.dto.ResultList;
import net.realme.mall.product.domain.ProductDto;
import net.realme.mall.product.domain.ProductListQuery;
import net.realme.mall.product.domain.request.ProductGetUpdDto;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.product.facade
 *
 * @author 91000044
 * @date 2018/8/23 11:01
 */
public interface ProductSpuService {

    ResultT<Integer> add(ProductDto productDto);

    ResultT<ProductGetUpdDto> get(Integer productId);

    ResultT<Integer> status(Integer productId, byte shelfStatus, int userId);

    ResultT<Integer> update(ProductGetUpdDto productGetUpdDto);

    ResultT<ResultList<ProductDto>> list(ProductListQuery query);

    ResultT<Integer> deleteSpuInfo(Integer productId, int userId);

    // 添加和修改时spu名称不能和已有的重复
    ResultT<Integer> getSpuCountByName(String spuName);
}
