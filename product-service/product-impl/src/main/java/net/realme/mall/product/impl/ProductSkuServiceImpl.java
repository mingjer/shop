package net.realme.mall.product.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.realme.framework.cache.redis.RedisCache;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.product.beantool.ProductSkuAttributeConvert;
import net.realme.mall.product.beantool.ProductSkuConvert;
import net.realme.mall.product.common.ProductConst;
import net.realme.mall.product.dao.ProductSkuMapper;
import net.realme.mall.product.domain.ProductSkuAttributeDto;
import net.realme.mall.product.domain.request.ProductSkuDto;
import net.realme.mall.product.domain.SkuListQuery;
import net.realme.mall.product.domain.request.SpuInfoOnSkuRequest;
import net.realme.mall.product.domain.response.*;
import net.realme.mall.product.facade.*;
import net.realme.mall.product.model.ProductSku;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by 91000156 on 2018/8/29 14:38
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ProductSkuServiceImpl implements ProductSkuService {

    @Autowired
    private RedisCache<ProductSkuResponse> cache;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductSkuConvert productSkuConvert;

    @Autowired
    private ProductSkuAttributeConvert productSkuAttributeConvert;

    @Autowired
    private ProductAttributeValueService productAttributeValueService;

    @Autowired
    private ProductSkuAttributeService productSkuAttributeService;

    @Autowired
    private ProductAttributeService productAttributeService;

    @Autowired
    private ProductFittingService productFittingService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ResultT<Integer> addSkuInfo(ProductSkuDto productSkuDto) {
        // DTO转PO
        ProductSku productSku = productSkuConvert.toProductSku(productSkuDto);
        // 插入时间
        productSku.setCreatedTime(System.currentTimeMillis());
        // 插入sku信息表
        int rows = productSkuMapper.insertSelective(productSku);
        // 插入sku所选属性
        if(rows>0){
            List<ProductSkuAttributeDto> skuAttrDtoList = productSkuAttributeConvert.fromReqToSkuAttrDtoList(productSkuDto.getSelOption());
            // 批量插入sku属性组合,skuId在插入后会放到PO中
            int insertRet = productSkuAttributeService.addSkuAttribute(skuAttrDtoList,productSkuDto.getProductId(),productSku.getSkuId());
            return ResultT.success(insertRet);
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<ProductSkuResponse> getSkuInfoById(Integer skuId) {
        String cacheKey = ProductConst.RedisKey.CACHE_SKU_INFO_KEY + skuId;
        ProductSkuResponse skuResponse= cache.get(cacheKey);
        if (skuResponse != null) {
            return ResultT.success(skuResponse);
        }
        Example example = new Example(ProductSku.class);
        // 设置查询条件
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("skuId", skuId);
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        // 按照SkuId和状态查询一条SKU信息
        ProductSku productSku = productSkuMapper.selectOneByExample(example);
        if (productSku != null) {
            skuResponse = productSkuConvert.toSkuResponse(productSku);
            // 获取已选的sku属性组合
            List<SkuAttributeInSkuResponse> result = productSkuAttributeService.getSkuAttrInSkuResponse(skuId);
            skuResponse.setSelOption(result);
            // 获取sku的配件List
            List<Integer> fittingsList = productFittingService.getFittingIdsByMainSkuId(skuId);
            skuResponse.setFittingSkuIdList(fittingsList);
            cache.set(cacheKey, skuResponse, ProductConst.RedisKey.SKU_CACHE_TTL);
            return ResultT.success(skuResponse);
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<Integer> updateSkuStatus(Integer skuId, byte shelfStatus, int userId) {
        // 设置更新条件
        Example example = new Example(ProductSku.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("skuId", skuId);
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        // 将status设置为已下架
        ProductSku updateSku = new ProductSku();
        // 将下架和上架状态进行更新
        updateSku.setShelfStatus(shelfStatus);
        updateSku.setUpdatedTime(System.currentTimeMillis());
        updateSku.setUpdatedBy(userId);
        if (productSkuMapper.updateByExampleSelective(updateSku, example) > 0) {
            // 删除缓存
            clearCache(skuId);
            if (productSkuAttributeService.updateSkuAttributeShelfStatus(skuId, shelfStatus) > 0) {
                return ResultT.success(1);
            }
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<Integer> deleteSkuInfo(Integer skuId, int userId) {
        // 设置删除条件
        Example example = new Example(ProductSku.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("skuId", skuId);
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        // 将status设置删除状态
        ProductSku updateSku = new ProductSku();
        // 将下架和上架状态进行更新
        updateSku.setStatus((byte) -1);
        updateSku.setUpdatedTime(System.currentTimeMillis());
        updateSku.setUpdatedBy(userId);
        if (productSkuMapper.updateByExampleSelective(updateSku, example) > 0) {
            // 删除缓存
            clearCache(skuId);
            // 删除sku属性组合
            if (productSkuAttributeService.deleteSkuAttribute(skuId) > 0) {
                return ResultT.success(1);
            }
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<Integer> updateSkuInfo(ProductSkuDto productSkuDto) {
        ProductSku productSku = productSkuConvert.toProductSku(productSkuDto);
        productSku.setUpdatedTime(System.currentTimeMillis());
        // 设置更新条件
        Example example = new Example(ProductSku.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("skuId", productSkuDto.getSkuId());
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        if (productSkuMapper.updateByExampleSelective(productSku, example) > 0) {
            Integer skuId = productSkuDto.getSkuId();
            // 禁止删除已有属性，只能改文本的值
//            Map<Integer, List<String>> options = productDto.getOptions();
//            productAttributeValueService.add(productId, options);
            // 清除缓存
            clearCache(skuId);
            return ResultT.success(1);
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<ResultList<ProductSkuResponse>> getSkuList(SkuListQuery query) {
        ResultList<ProductSkuResponse> result = new ResultList<>();
        Example example = new Example(ProductSku.class);
        Example.Criteria criteria = example.createCriteria();
        // 添加已设置的查询条件
        if (query.getProductId() != null) {
            criteria.andEqualTo("productId", query.getProductId());
        }
        if (query.getProductName() != null) {
            criteria.andLike("productName", "%" + query.getProductName() + "%");
        }
        if (query.getSkuId() != null) {
            criteria.andEqualTo("skuId", query.getSkuId());
        }
        if (query.getSkuName() != null) {
            criteria.andLike("skuName", "%" + query.getSkuName() + "%");
        }
        if (query.getErpCode() != null) {
            criteria.andLike("erpCode", "%" + query.getErpCode() + "%");
        }

        // 查询未被删除的sku信息
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        PageHelper.startPage(query.getPage(), query.getLimit(), true);
        example.setOrderByClause("created_time desc");
        List<ProductSku> productList = productSkuMapper.selectByExample(example);
        PageInfo<ProductSku> pageInfo = new PageInfo<>(productList);
        if (pageInfo.getTotal() > 0) {
            List<ProductSkuResponse> dtoRecords = productSkuConvert.toProductSkuResList(pageInfo.getList());
            result.setRecords(dtoRecords);
            result.setRecordTotal(pageInfo.getTotal());
            result.setPageNum(pageInfo.getPageNum());
            result.setPageSize(pageInfo.getPageSize());
        }
        return ResultT.success(result);
    }

    @Override
    public ResultT<List<ProductSkuResponse>> getSkuListBySiteCode(String siteCode) {
        Example example = new Example(ProductSku.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("siteCode", siteCode);
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        List<ProductSku> productList = productSkuMapper.selectByExample(example);
        PageInfo<ProductSku> pageInfo = new PageInfo<>(productList);
        List<ProductSkuResponse> dtoRecords=null;
        if (pageInfo.getTotal() > 0) {
            dtoRecords = productSkuConvert.toProductSkuResList(pageInfo.getList());
        }
        return ResultT.success(dtoRecords);
    }

    @Override
    public ResultT<SelSkuAttrGroupResponse> getSelSkuAttrGroup(Integer productId, Integer skuId) {
        // 初始化返回结果
        SelSkuAttrGroupResponse selSkuAttr = new SelSkuAttrGroupResponse();
        // 获取可编辑SKU属性组
        List<ProductAttributeResponse> attrList = productAttributeService.getProductAttributeList();
        // 根据productId获取对应的属性组
        List<ProductAttributeValueResponse> attrValueGroup = productAttributeValueService.getAttrValueResponseList(productId);
        // 根据skuId获取指定的所选属性
        List<ProductSkuAttributeResponse> selOption = productSkuAttributeService.getSkuAttrResponse(skuId);
        selSkuAttr.setAttrList(attrList);
        selSkuAttr.setAttrValList(attrValueGroup);
        selSkuAttr.setSelOption(selOption);
        return ResultT.success(selSkuAttr);
    }

    @Override
    public ResultT<GetSkuAttrGroupResponse> getDefaultSkuAttrGroup(Integer productId) {
        // 初始化返回结果
        GetSkuAttrGroupResponse getSkuAttrGroupResponse = new GetSkuAttrGroupResponse();
        // 获取可编辑SKU属性组
        List<ProductAttributeResponse> attrList = productAttributeService.getProductAttributeList();
        // 根据productId获取对应的属性组
        List<ProductAttributeValueResponse> attrValueGroup = productAttributeValueService.getAttrValueResponseList(productId);
        // 根据productId获取此SPU下所有已选的SKU属性
        Map<String, List<DefaultOptionRetModel>> selOption = productSkuAttributeService.getProductSkuAttr(productId);
        // 返回赋值
        getSkuAttrGroupResponse.setAttrList(attrList);
        getSkuAttrGroupResponse.setAttrValList(attrValueGroup);
        getSkuAttrGroupResponse.setSelOptions(selOption);
        return ResultT.success(getSkuAttrGroupResponse);
    }

    @Override
    public ResultT<Integer> editSpuAttrOnSkuPage(SpuInfoOnSkuRequest spuInfoOnSkuRequest, Integer userId) {
        // 获取sku对应的spu_id及site_code
        ResultT<ProductSkuResponse> skuInfoResult = getSkuInfoById(spuInfoOnSkuRequest.getSkuId());
        // 未能获取到Sku信息则返回
        if (skuInfoResult == null || !skuInfoResult.isSuccess()) {
            return ResultT.fail();
        }
        // 转化为DB-Model
        ProductSku productSku = productSkuConvert.formSpuInfoToSkuInfo(spuInfoOnSkuRequest);
        productSku.setUpdatedBy(userId);
        // 设置更新条件
        Example example = new Example(ProductSku.class);
        Example.Criteria criteria = example.createCriteria();
        // 将更新覆盖到spu下sku的site_code对应的所有sku
        criteria.andEqualTo("productId", skuInfoResult.getData().getProductId());
        criteria.andEqualTo("siteCode", skuInfoResult.getData().getSiteCode());
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        // 修改时间
        productSku.setUpdatedTime(System.currentTimeMillis());
        // 数据库更新
        if (productSkuMapper.updateByExampleSelective(productSku, example) > 0) {
            Integer skuId = spuInfoOnSkuRequest.getSkuId();
            clearAllSkuCache();
            return ResultT.success(1);
        }
        return ResultT.fail();
    }

    @Override
    public List<ProductSkuDto> getSkuInfoBySaleStatus(Byte saleStatus) {
        Example example = new Example(ProductSku.class);
        Example.Criteria criteria = example.createCriteria();
        // 销售状态 0 不开卖 1 开卖 2 预约
        criteria.andEqualTo("saleStatus", saleStatus);
        // 查询未被删除的sku信息
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        List<ProductSku> productList = productSkuMapper.selectByExample(example);
        List<ProductSkuDto> dtoRecords = productSkuConvert.toProductSkuDtoList(productList);
        return dtoRecords;
    }

    @Override
    public ResultT<Integer> editSkuFitting(Integer skuId, List<Integer> fittingIdList) {
        // 每次进来先将上次编辑的配件信息，updStatus
        productFittingService.deleteFittingInfo(skuId, null);
        // 然后进行批量添加操作
        int addCode = productFittingService.addProductFitting(skuId, fittingIdList);
        if(addCode < 0){
            return ResultT.fail();
        }
        return ResultT.success(addCode);
    }

    @Override
    public ResultT<Boolean> isUserDefinedUriExist(String siteCode, String userDefinedUri) {
        // 初始化标记
        boolean existFlg = false;
        // 同一个站点下的Sku-Uri不可以重复
        Example example = new Example(ProductSku.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("siteCode", siteCode);
        criteria.andEqualTo("userDefinedUrl", userDefinedUri);
        // 查询未被删除的sku信息
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        // 是否已存在此Uri
        int count = productSkuMapper.selectCountByExample(example);
        if (count > 0) {
            // 已存在，不可重复
            existFlg = true;
        } else {
            // 不存在，可添加
            existFlg = false;
        }
        return ResultT.success(existFlg);
    }

    private void clearCache(Integer skuId) {
        cache.delete(ProductConst.RedisKey.CACHE_SKU_INFO_KEY + skuId);
    }

    /**
     * 删除sku缓存
     */
    private void clearAllSkuCache() {
        Set<String> keys = cache.getKeysByPrefix(ProductConst.RedisKey.CACHE_SKU_INFO_KEY + "*");
        if (!CollectionUtils.isEmpty(keys)) {
            cache.deleteKeyByPrefix(keys);
        }
    }

    @Override
    public ResultT<ProductSkuDto> getByEANCode(String eanCode) {
        ProductSku productSku = productSkuMapper.selectByEANCode(eanCode);
        if (productSku != null) {
            return ResultT.success(productSkuConvert.toProductSkuDto(productSku));
        }
        return ResultT.fail();
    }
}
