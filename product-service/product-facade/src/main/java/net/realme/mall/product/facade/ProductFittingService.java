package net.realme.mall.product.facade;

import net.realme.mall.product.domain.ProductFittingDto;

import java.util.List;

/**
 * Created by 91000156 on 2018/9/18 10:40
 */
public interface ProductFittingService {

    // 添加配件信息
    int addProductFitting(Integer skuId, List<Integer> fittingIdList);

    // 根据skuId获取配件的的数量
    int getFittingCountByMainSkuId(Integer skuId);

    // 更新和新增配件信息
    int updInsertFitting(Integer skuId, List<Integer> fittingIdList);

    // 获取配件信息列表
    List<Integer> getFittingIdsByMainSkuId(Integer skuId);

    // 获取一条配件信息
    ProductFittingDto getFittingDtoById(Integer mainSkuId, Integer partSkuId);

    // 删除主配件关系
    int deleteFittingInfo(Integer mainSkuId, Integer partSkuId);
}
