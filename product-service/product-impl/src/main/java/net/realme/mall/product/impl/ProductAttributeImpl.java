package net.realme.mall.product.impl;

import net.realme.mall.product.beantool.ProductAttributeConvert;
import net.realme.mall.product.dao.ProductAttributeMapper;
import net.realme.mall.product.domain.response.ProductAttributeResponse;
import net.realme.mall.product.facade.ProductAttributeService;
import net.realme.mall.product.model.ProductAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 91000156 on 2018/8/30 14:00
 */
@Service
public class ProductAttributeImpl implements ProductAttributeService {

    @Autowired
    private ProductAttributeMapper productAttributeMapper;

    @Autowired
    private ProductAttributeConvert productAttributeConvert;

    @Override
    public List<ProductAttributeResponse> getProductAttributeList() {
        // 初始化返回结果
        List<ProductAttributeResponse> attrsDtoList = new ArrayList<>();
        // 根据产品的spuId查询对应的attributeValue组成options
        Example example = new Example(ProductAttribute.class);
        // 设置查询条件
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 0);
        // 按照属性序号排序输出
        String orderBy = "sequence asc";
        example.setOrderByClause(orderBy);
        // 查询spu对应的attrOptions
        List<ProductAttribute> attrList = productAttributeMapper.selectByExample(example);
        if (attrList != null && !attrList.isEmpty()) {
            attrsDtoList = productAttributeConvert.toProductAttributeResList(attrList);
        }
        return attrsDtoList;
    }
}
