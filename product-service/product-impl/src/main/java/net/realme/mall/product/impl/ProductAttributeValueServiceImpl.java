package net.realme.mall.product.impl;

import net.realme.framework.util.dto.ResultT;
import net.realme.mall.product.beantool.ProductAttributeValueConvert;
import net.realme.mall.product.common.ProductConst;
import net.realme.mall.product.dao.ProductAttributeValueMapper;
import net.realme.mall.product.domain.request.SpuAttrUpdate;
import net.realme.mall.product.domain.response.ProductAttributeValueResponse;
import net.realme.mall.product.facade.ProductAttributeValueService;
import net.realme.mall.product.model.ProductAttributeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.product.impl
 *
 * @author 91000044
 * @date 2018/8/23 12:39
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ProductAttributeValueServiceImpl implements ProductAttributeValueService {

    @Autowired
    private ProductAttributeValueMapper productAttributeValueMapper;

    @Autowired
    private ProductAttributeValueConvert productAttributeValueConvert;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ResultT<Integer> add(Integer productId, Map<Integer, List<String>> options) {
        if (options == null || options.isEmpty()) {
            return ResultT.fail();
        }
        List<ProductAttributeValue> productAttributeValueList = new ArrayList<>();
        Long currentTime = System.currentTimeMillis();
        for (Map.Entry<Integer, List<String>> entry: options.entrySet()) {
            // 属性序号初始化为0
            int sequence = 1;
            if (entry.getValue() == null || entry.getValue().isEmpty()) {
                logger.error("spu [{}] attribute group is null or empty!!!", productId);
            }
            for (String val: entry.getValue()) {
                ProductAttributeValue pav = new ProductAttributeValue();
                pav.setProductId(productId);
                pav.setAttrId(entry.getKey());
                pav.setAttrVal(val);
                pav.setSequence(sequence++);
                pav.setCreatedTime(currentTime);
                productAttributeValueList.add(pav);
            }
        }
        int ret = productAttributeValueMapper.batchInsert(productAttributeValueList);
        if (ret > 0) {
            return ResultT.success(ret);
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<Integer> updInsert(Integer productId, Map<Integer, List<SpuAttrUpdate>> options) {
        if (options == null || options.isEmpty()) {
            return ResultT.fail();
        }
        // 遍历属性集合
        for(Map.Entry<Integer, List<SpuAttrUpdate>> entry : options.entrySet()){
            // 拿到每个属性组时进行遍历
            for (SpuAttrUpdate spuAttrUpdate : entry.getValue()) {
                // 尝试更新
                Example example = new Example(ProductAttributeValue.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("id",spuAttrUpdate.getId());
                ProductAttributeValue attributeValue = new ProductAttributeValue();
                attributeValue.setAttrVal(spuAttrUpdate.getAttrVal());
                attributeValue.setUpdatedTime(System.currentTimeMillis());
                if (spuAttrUpdate.getId() != null && spuAttrUpdate.getId() > 0) {
                    int updCode = productAttributeValueMapper.updateByExampleSelective(attributeValue,example);
                    // 更新返回值为0，则插入
                    if(updCode >0 ){
                        continue;
                    }
                }
                attributeValue.setProductId(productId);
                // 为颜色属性组时
                if (entry.getKey() == ProductConst.AttrInfo.COLOR_ATTR_ID) {
                    attributeValue.setAttrId(ProductConst.AttrInfo.COLOR_ATTR_ID);
                } else {
                    attributeValue.setAttrId(ProductConst.AttrInfo.SPEC_ATTR_ID);
                }
                attributeValue.setCreatedTime(System.currentTimeMillis());
                // 没有找到更新项，则插入
                productAttributeValueMapper.insertSelective(attributeValue);
            }
        }
        return ResultT.success();
    }

    @Override
    public int deleteByProductId(Integer productId) {
        Example example = new Example(ProductAttributeValue.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", productId);
        criteria.andEqualTo("status", (byte) 0);
        // 将status设置删除状态
        ProductAttributeValue attributeValue = new ProductAttributeValue();
        // 将商品信息置为删除
        attributeValue.setStatus((byte) -1);
        attributeValue.setUpdatedTime(System.currentTimeMillis());
        // 将数据更新为删除状态
        return productAttributeValueMapper.updateByExampleSelective(attributeValue, example);
    }

    @Override
    public Map<Integer, List<String>> getOptionsByProductId(Integer productId) {
        // 初始化返回结果
        Map<Integer, List<String>> options = new HashMap<>();
        // 根据产品的spuId查询对应的attributeValue组成options
        Example example = new Example(ProductAttributeValue.class);
        // 设置查询条件
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", productId);
        criteria.andEqualTo("status", 0);
        // 按照属性序号排序输出
        String orderBy = "sequence asc";
        example.setOrderByClause(orderBy);
        // 查询spu对应的attrOptions
        List<ProductAttributeValue> attrList = productAttributeValueMapper.selectByExample(example);
        if (attrList != null && !attrList.isEmpty()) {
            for (ProductAttributeValue p : attrList) {
                List attrValList = null;
                if (!options.containsKey(p.getAttrId())) {
                    attrValList = new ArrayList();
                    attrValList.add(p.getAttrVal());
                } else {
                    attrValList = options.get(p.getAttrId());
                    attrValList.add(p.getAttrVal());
                }
                options.put(p.getAttrId(), attrValList);
            }
        }
        return options;
    }

    @Override
    public Map<Integer, List<SpuAttrUpdate>> getSpuAttrUpdateByProductId(Integer productId) {
        // 初始化返回结果
        Map<Integer, List<SpuAttrUpdate>> options = new HashMap<>();
        // 根据产品的spuId查询对应的attributeValue组成options
        Example colorExample = new Example(ProductAttributeValue.class);
        // 设置颜色查询条件
        Example.Criteria criteriaColor = colorExample.createCriteria();
        criteriaColor.andEqualTo("productId", productId);
        criteriaColor.andEqualTo("attrId", ProductConst.AttrInfo.COLOR_ATTR_ID);
        criteriaColor.andEqualTo("status", 0);
        // 按照属性序号排序输出
        String orderBy = "sequence asc";
        colorExample.setOrderByClause(orderBy);
        // 查询spu对应的attrOptions
        List<ProductAttributeValue> colorAttrList = productAttributeValueMapper.selectByExample(colorExample);
        if (colorAttrList != null && !colorAttrList.isEmpty()) {
            List<SpuAttrUpdate> colorAttrUpdates = productAttributeValueConvert.toProductAttributeUpdate(colorAttrList);
            options.put(ProductConst.AttrInfo.COLOR_ATTR_ID,colorAttrUpdates);
        }
        Example specExample = new Example(ProductAttributeValue.class);
        // 设置颜色查询条件
        Example.Criteria criteriaSpec = specExample.createCriteria();
        criteriaSpec.andEqualTo("productId", productId);
        criteriaSpec.andEqualTo("attrId", ProductConst.AttrInfo.SPEC_ATTR_ID);
        criteriaSpec.andEqualTo("status", 0);
        specExample.setOrderByClause(orderBy);
        // 查询spu对应的attrOptions
        List<ProductAttributeValue> specAttrList = productAttributeValueMapper.selectByExample(specExample);
        if (specAttrList != null && !specAttrList.isEmpty()) {
            List<SpuAttrUpdate> specAttrUpdates = productAttributeValueConvert.toProductAttributeUpdate(specAttrList);
            options.put(ProductConst.AttrInfo.SPEC_ATTR_ID,specAttrUpdates);
        }
        return options;
    }

    @Override
    public List<ProductAttributeValueResponse> getAttrValueResponseList(Integer productId) {
        // 初始化返回结果
        List<ProductAttributeValueResponse> attrValueResList = new ArrayList<>();
        // 根据产品的spuId查询对应的attributeValue组成options
        Example example = new Example(ProductAttributeValue.class);
        // 设置查询条件
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", productId);
        criteria.andEqualTo("status", 0);
        // 按照属性序号排序输出
        String orderBy = "sequence asc";
        example.setOrderByClause(orderBy);
        // 查询spu对应的attrOptions
        List<ProductAttributeValue> attrList = productAttributeValueMapper.selectByExample(example);
        if (attrList != null && !attrList.isEmpty()) {
            attrValueResList = productAttributeValueConvert.toProductAttributeValResList(attrList);
        }
        return attrValueResList;
    }
}
