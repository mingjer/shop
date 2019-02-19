package net.realme.mall.product.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.realme.framework.cache.redis.RedisCache;
import net.realme.framework.util.dto.ResultT;
import net.realme.framework.util.dto.ResultList;
import net.realme.mall.product.beantool.ProductSpuConvert;
import net.realme.mall.product.common.ProductConst;
import net.realme.mall.product.dao.ProductBaseMapper;
import net.realme.mall.product.dao.ProductSkuMapper;
import net.realme.mall.product.domain.ProductDto;
import net.realme.mall.product.domain.ProductListQuery;
import net.realme.mall.product.domain.request.ProductGetUpdDto;
import net.realme.mall.product.domain.request.SpuAttrUpdate;
import net.realme.mall.product.facade.ProductAttributeValueService;
import net.realme.mall.product.facade.ProductSpuService;
import net.realme.mall.product.model.ProductBase;
import net.realme.mall.product.model.ProductSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.product.impl
 *
 * @author 91000044
 * @date 2018/8/23 11:30
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ProductSpuServiceImpl implements ProductSpuService {

    @Autowired
    private RedisCache<ProductGetUpdDto> cache;

    @Autowired
    private ProductSpuConvert productConverter;

    @Autowired
    private ProductBaseMapper productBaseMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductAttributeValueService productAttributeValueService;

    @Override
    public ResultT<Integer> add(ProductDto productDto) {
        ProductBase productBase = productConverter.fromProductDto(productDto);
        int flg = productBaseMapper.insertSelective(productBase);
        if (flg > 0) {
            Map<Integer, List<String>> options = productDto.getOptions();
            // 自增Id是返回到对象中，而不是flg中
            productAttributeValueService.add(productBase.getProductId(), options);
            return ResultT.success(productBase.getProductId());
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<ProductGetUpdDto> get(Integer productId) {
        String cacheKey = ProductConst.RedisKey.CACHE_SPU_INFO_KEY + productId;
        ProductGetUpdDto productGetUpdDto = cache.get(cacheKey);
        if (productGetUpdDto != null) {
            return ResultT.success(productGetUpdDto);
        }
        Example example = new Example(ProductBase.class);
        // 设置查询条件
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", productId);
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        // 按照ProductId和状态查询一条SPU信息
        ProductBase productBase = productBaseMapper.selectOneByExample(example);
        if (productBase != null) {
            productGetUpdDto = productConverter.toProductGetUpdDto(productBase);
            // 获取attrOptions
            Map<Integer, List<SpuAttrUpdate>> options = productAttributeValueService.getSpuAttrUpdateByProductId(productId);
            productGetUpdDto.setOptions(options);
            cache.set(cacheKey, productGetUpdDto, ProductConst.RedisKey.SPU_CACHE_TTL);
            return ResultT.success(productGetUpdDto);
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<Integer> status(Integer productId, byte shelfStatus, int userId) {
        // 设置更新条件
        Example example = new Example(ProductBase.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", productId);
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        // shelfStatus对应上架和下架
        ProductBase updateProduct = new ProductBase();
        updateProduct.setShelfStatus(shelfStatus);
        updateProduct.setUpdatedTime(System.currentTimeMillis());
        updateProduct.setUpdatedBy(userId);
        if (productBaseMapper.updateByExampleSelective(updateProduct, example) > 0) {
            clearCache(productId);
            // SPU下架后对应的所有的SKU的状态也要改成下架，同理上架后也让下面的SKU都改为上架
            Example skuExample = new Example(ProductSku.class);
            Example.Criteria skuCriteria = skuExample.createCriteria();
            skuCriteria.andEqualTo("productId", productId);
            skuCriteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
            // shelfStatus对应上架和下架
            ProductSku productSku = new ProductSku();
            productSku.setShelfStatus(shelfStatus);
            productSku.setUpdatedTime(System.currentTimeMillis());
            productSku.setUpdatedBy(userId);
            if (productSkuMapper.updateByExampleSelective(productSku, skuExample) > 0){
                // 更新sku状态后,sku_list直接查库，故可不清sku缓存
            }
            return ResultT.success(1);
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<Integer> update(ProductGetUpdDto productGetUpdDto) {
        ProductBase productBase = productConverter.fromProductGetUpdDto(productGetUpdDto);
        // 设置更新条件
        Example example = new Example(ProductBase.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", productGetUpdDto.getProductId());
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        if (productBaseMapper.updateByExampleSelective(productBase, example) > 0) {
            // 禁止删除已有属性，只能改文本的值
            Integer productId = productGetUpdDto.getProductId();
            productAttributeValueService.updInsert(productId,productGetUpdDto.getOptions());
            clearCache(productId);
            return ResultT.success(1);
        }
        return ResultT.fail();
    }

    private void clearCache(Integer productId) {
        cache.delete(ProductConst.RedisKey.CACHE_SPU_INFO_KEY + productId);
    }

    @Override
    public ResultT<ResultList<ProductDto>> list(ProductListQuery query) {
        ResultList<ProductDto> result = new ResultList<>();
        Example example = new Example(ProductBase.class);
        Example.Criteria criteria = example.createCriteria();

        if (query.getCategoryCode() != null) {
            criteria.andEqualTo("categoryCode", query.getCategoryCode());
        }
        if (query.getBrandCode() != null) {
            criteria.andEqualTo("brandCode", query.getBrandCode());
        }
        if (query.getProductId() != null) {
            criteria.andEqualTo("productId", query.getProductId());
        }
        if (query.getProductName() != null) {
            // 商品名称按照模糊查询
            criteria.andLike("name", "%" + query.getProductName() + "%");
        }
        // 查询未被删除的商品信息
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        // 分页
        PageHelper.startPage(query.getPage(), query.getLimit(), true).setOrderBy("created_time desc");
        List<ProductBase> productList = productBaseMapper.selectByExample(example);
        PageInfo<ProductBase> pageInfo = new PageInfo<>(productList);
        if (pageInfo.getTotal() > 0) {
            List<ProductDto> dtoRecords = productConverter.toProductDtoList(pageInfo.getList());
            result.setRecords(dtoRecords);
            result.setRecordTotal(pageInfo.getTotal());
            result.setPageNum(pageInfo.getPageNum());
            result.setPageSize(pageInfo.getPageSize());
        }
        return ResultT.success(result);
    }

    @Override
    public ResultT<Integer> deleteSpuInfo(Integer productId, int userId) {
        // 设置删除条件
        Example example = new Example(ProductBase.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", productId);
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        // 将status设置删除状态
        ProductBase productBase = new ProductBase();
        // 将商品信息置为删除
        productBase.setStatus((byte) -1);
        productBase.setUpdatedTime(System.currentTimeMillis());
        productBase.setUpdatedBy(userId);
        if (productBaseMapper.updateByExampleSelective(productBase, example) > 0) {
            // 删除缓存
            clearCache(productId);
            // 删除spu的属性组合
            if (productAttributeValueService.deleteByProductId(productId) > 0) {
                return ResultT.success(1);
            }
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<Integer> getSpuCountByName(String spuName) {
        // 添加时还未生成product_id
        Example example = new Example(ProductBase.class);
        // 设置查询条件
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", spuName);
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        // 按照ProductId和状态查询一条SPU信息
        int spuCount = productBaseMapper.selectCountByExample(example);
        // 返回spu的count
        return ResultT.success(spuCount);
    }
}
