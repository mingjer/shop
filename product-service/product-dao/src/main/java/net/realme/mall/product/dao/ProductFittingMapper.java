package net.realme.mall.product.dao;

import net.realme.mall.product.model.ProductFitting;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProductFittingMapper extends Mapper<ProductFitting> {

    int batchInsert(List<ProductFitting> fittingList);
}