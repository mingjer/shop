package net.realme.mall.product.dao;

import net.realme.mall.product.model.ProductSku;
import tk.mybatis.mapper.common.Mapper;

public interface ProductSkuMapper extends Mapper<ProductSku> {

    ProductSku selectByEANCode(String eanCode);
}