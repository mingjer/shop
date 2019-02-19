package net.realme.mall.product.dao;

import net.realme.mall.product.model.ProductAttributeValue;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProductAttributeValueMapper extends Mapper<ProductAttributeValue> {

    int batchInsert(List<ProductAttributeValue> list);
}