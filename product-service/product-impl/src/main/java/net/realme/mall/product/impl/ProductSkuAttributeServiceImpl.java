package net.realme.mall.product.impl;

import net.realme.mall.product.beantool.ProductSkuAttributeConvert;
import net.realme.mall.product.common.ProductConst;
import net.realme.mall.product.dao.ProductSkuAttributeMapper;
import net.realme.mall.product.domain.ProductSkuAttributeDto;
import net.realme.mall.product.domain.request.ProductSkuAttributeRequest;
import net.realme.mall.product.domain.response.DefaultOptionRetModel;
import net.realme.mall.product.domain.response.ProductSkuAttributeResponse;
import net.realme.mall.product.domain.response.SkuAttributeInSkuResponse;
import net.realme.mall.product.facade.ProductSkuAttributeService;
import net.realme.mall.product.model.AttrValJoinSku;
import net.realme.mall.product.model.ProductSkuAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Created by 91000156 on 2018/8/29 15:41
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ProductSkuAttributeServiceImpl implements ProductSkuAttributeService {

    @Autowired
    private ProductSkuAttributeMapper productSkuAttributeMapper;

    @Autowired
    private ProductSkuAttributeConvert productSkuAttributeConvert;

    @Override
    public int addSkuAttribute(List<ProductSkuAttributeDto> attributeDtoList, Integer productId, Integer skuId) {
        // 初始化批量插入
        List<ProductSkuAttribute> productSkuAttributes = productSkuAttributeConvert.toProductSkuAttributeList(attributeDtoList);
        for (ProductSkuAttribute productSkuAttribute : productSkuAttributes) {
            productSkuAttribute.setProductId(productId);
            productSkuAttribute.setSkuId(skuId);
            productSkuAttribute.setCreatedTime(System.currentTimeMillis());
        }
        // 批量插入
        int ret = productSkuAttributeMapper.batchInsert(productSkuAttributes);

        return ret;
    }

    @Override
    public Map<String,String> getSkuAttribute(Integer skuId) {
        // 初始化返回值
        Map<String,String> retMap = new HashMap<>();
        Example example = new Example(ProductSkuAttribute.class);
        // 设置查询条件
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("skuId", skuId);
        criteria.andEqualTo("status", (byte) 0);
        // 按照ProductId和状态查询一条SPU信息
        List<ProductSkuAttribute> productSkuAttributes = productSkuAttributeMapper.selectByExample(example);
        if (productSkuAttributes != null && productSkuAttributes.size() > 0) {
            for(ProductSkuAttribute p : productSkuAttributes){
                // 构建此SKU已选属性的返回Map
                retMap.put(p.getAttrId(),p.getAttrValId());
            }
        }
        return retMap;
    }

    @Override
    public List<ProductSkuAttributeResponse> getSkuAttrResponse(Integer skuId) {
        // 初始化返回值
        List<ProductSkuAttributeResponse> skuAttrResList = new ArrayList<>();
        Example example = new Example(ProductSkuAttribute.class);
        // 设置查询条件
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("skuId", skuId);
        criteria.andEqualTo("status", (byte) 0);
        // 按照ProductId和状态查询一条SPU信息
        List<ProductSkuAttribute> productSkuAttributes = productSkuAttributeMapper.selectByExample(example);
        if (productSkuAttributes != null && productSkuAttributes.size() > 0) {
            skuAttrResList = productSkuAttributeConvert.toSkuAttrResponseList(productSkuAttributes);
        }
        return skuAttrResList;
    }

    @Override
    public List<ProductSkuAttributeRequest> getSkuAttrRequest(Integer skuId) {
        // 初始化返回值
        List<ProductSkuAttributeRequest> skuAttrReqList = new ArrayList<>();
        Example example = new Example(ProductSkuAttribute.class);
        // 设置查询条件
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("skuId", skuId);
        criteria.andEqualTo("status", (byte) 0);
        // 按照ProductId和状态查询一条SPU信息
        List<ProductSkuAttribute> productSkuAttributes = productSkuAttributeMapper.selectByExample(example);
        if (productSkuAttributes != null && productSkuAttributes.size() > 0) {
            skuAttrReqList = productSkuAttributeConvert.toSkuAttrRequestList(productSkuAttributes);
        }
        return skuAttrReqList;
    }

    @Override
    public Map<String, List<DefaultOptionRetModel>> getProductSkuAttr(Integer productId) {
        // 初始化返回结果
        Map<String, List<DefaultOptionRetModel>> options = new HashMap<>();
        Example example = new Example(ProductSkuAttribute.class);
        // 设置查询条件
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", productId);
        criteria.andEqualTo("status", (byte) 0);
        example.setOrderByClause("created_time desc");
        // 按照ProductId和状态查询一条SPU信息
        List<ProductSkuAttribute> productSkuAttributes = productSkuAttributeMapper.selectByExample(example);
        if (productSkuAttributes != null && productSkuAttributes.size() > 0) {
            for (ProductSkuAttribute p : productSkuAttributes) {
                List attrValList = null;
                if (!options.containsKey(p.getSkuId().toString())) {
                    attrValList = new ArrayList();
                    DefaultOptionRetModel model = new DefaultOptionRetModel();
                    model.setAttrId(p.getAttrId());
                    model.setAttrValId(p.getAttrValId());
                    attrValList.add(model);
                } else {
                    attrValList = options.get(p.getSkuId().toString());
                    DefaultOptionRetModel model = new DefaultOptionRetModel();
                    model.setAttrId(p.getAttrId());
                    model.setAttrValId(p.getAttrValId());
                    attrValList.add(model);
                }
                options.put(p.getSkuId().toString(), attrValList);
            }
        }
        return options;
    }

    @Override
    public int deleteSkuAttribute(Integer skuId) {
        Example example = new Example(ProductSkuAttribute.class);
        // 设置删除条件
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("skuId", skuId);
        criteria.andEqualTo("status", (byte) 0);
        // 将status设置删除状态
        ProductSkuAttribute skuAttribute = new ProductSkuAttribute();
        // 将商品信息置为删除
        skuAttribute.setStatus((byte) -1);
        skuAttribute.setUpdatedTime(System.currentTimeMillis());
        // 删除
        return productSkuAttributeMapper.updateByExampleSelective(skuAttribute, example);
    }

    @Override
    public List<SkuAttributeInSkuResponse> getSkuAttrInSkuResponse(Integer skuId) {
        // 初始化返回值
        List<SkuAttributeInSkuResponse> skuAttributeInSkuResponseList = new ArrayList<>();
        // 按照ProductId和状态查询一条SPU信息
        List<AttrValJoinSku> attrValJoinSkuList = productSkuAttributeMapper.getSkuAttributeInSkuResponse(skuId);
        if (attrValJoinSkuList != null && attrValJoinSkuList.size() > 0) {
            skuAttributeInSkuResponseList = productSkuAttributeConvert.toAttrValJoinSkuDtoInfo(attrValJoinSkuList);
        }
        return skuAttributeInSkuResponseList;
    }

    @Override
    public int updateSkuAttributeShelfStatus(Integer skuId, byte shelfStatus) {
        // 设置更新条件
        Example example = new Example(ProductSkuAttribute.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("skuId", skuId);
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        // 将status设置为已下架
        ProductSkuAttribute updateSkuAttribute = new ProductSkuAttribute();
        // 将下架和上架状态进行更新
        updateSkuAttribute.setShelfStatus(shelfStatus);
        updateSkuAttribute.setUpdatedTime(System.currentTimeMillis());

        return productSkuAttributeMapper.updateByExampleSelective(updateSkuAttribute, example);
    }
}