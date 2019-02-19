package net.realme.mall.product.dao;

import net.realme.mall.product.model.AttrValJoinSku;
import net.realme.mall.product.model.ProductSkuAttribute;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProductSkuAttributeMapper extends Mapper<ProductSkuAttribute> {

    String attrJoinSkuInfoSql = "SELECT\n" +
            "\tpj.sku_id AS sku_id,\n" +
            "\tpa.id AS attr_id,\n" +
            "\tpa.`name` AS attr_name,\n" +
            "\tpj.attr_val_id AS attr_val_id,\n" +
            "\tpj.attr_val AS attr_val\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tpsa.product_id AS product_id,\n" +
            "\t\t\tpsa.sku_id AS sku_id,\n" +
            "\t\t\tpsa.attr_id AS attr_id,\n" +
            "\t\t\tpsa.attr_val_id AS attr_val_id,\n" +
            "\t\t\tpav.attr_val AS attr_val\n" +
            "\t\tFROM\n" +
            "\t\t\t(\n" +
            "\t\t\t\tSELECT\n" +
            "\t\t\t\t\tproduct_id,\n" +
            "\t\t\t\t\tsku_id,\n" +
            "\t\t\t\t\tattr_id,\n" +
            "\t\t\t\t\tattr_val_id\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tproduct_sku_attribute\n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\t`status` = 0\n" +
            "\t\t\t) psa\n" +
            "\t\tJOIN product_attribute_value pav ON (\n" +
            "\t\t\tpsa.product_id = pav.product_id\n" +
            "\t\t\tAND psa.attr_val_id = pav.id\n" +
            "\t\t\tAND pav.`status` = 0\n" +
            "\t\t)\n" +
            "\t) pj\n" +
            "JOIN product_attribute pa ON (\n" +
            "\tpj.attr_id = pa.id\n" +
            "\tAND STATUS = 0\n" +
            ")";

    int batchInsert(List<ProductSkuAttribute> list);

    @Select(attrJoinSkuInfoSql + " where sku_id = #{skuId}")
    @Results({
            @Result(property = "skuId", column = "sku_id"),
            @Result(property = "attrId", column = "attr_id"),
            @Result(property = "attrName", column = "attr_name"),
            @Result(property = "attrValId", column = "attr_val_id"),
            @Result(property = "attrVal", column = "attr_val")
    })
    List<AttrValJoinSku> getSkuAttributeInSkuResponse(Integer skuId);
}