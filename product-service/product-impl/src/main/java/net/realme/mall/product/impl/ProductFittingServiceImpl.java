package net.realme.mall.product.impl;

import net.realme.mall.product.beantool.ProductFittingConvert;
import net.realme.mall.product.common.ProductConst;
import net.realme.mall.product.dao.ProductFittingMapper;
import net.realme.mall.product.domain.ProductFittingDto;
import net.realme.mall.product.facade.ProductFittingService;
import net.realme.mall.product.model.ProductFitting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 91000156 on 2018/9/18 10:59
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ProductFittingServiceImpl implements ProductFittingService {

    @Autowired
    private ProductFittingMapper productFittingMapper;

    @Autowired
    private ProductFittingConvert productFittingConvert;

    @Override
    public int addProductFitting(Integer skuId, List<Integer> fittingIdList) {
        List<ProductFitting> fittingList = new ArrayList<>();
        Long createTime = System.currentTimeMillis();
        for (int i = 0; i < fittingIdList.size(); i++) {
            ProductFitting fitting = new ProductFitting();
            fitting.setMainSkuId(skuId);
            fitting.setPartSkuId(fittingIdList.get(i));
            fitting.setCreatedTime(createTime);
            fitting.setSequence(i);
            fittingList.add(fitting);
        }
        int insCode = productFittingMapper.batchInsert(fittingList);
        return insCode;
    }

    @Override
    public int getFittingCountByMainSkuId(Integer skuId) {
        // 根据主件skuId获取对应的配件数量
        Example example = new Example(ProductFitting.class);
        // 设置查询条件
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("mainSkuId", skuId);
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        // 按照ProductId和状态查询一条SPU信息
        int fittingsCount = productFittingMapper.selectCountByExample(example);
        return fittingsCount;
    }

    @Override
    public int updInsertFitting(Integer skuId, List<Integer> fittingIdList) {
        // 是否需要更新
        for (int i = 0; i < fittingIdList.size(); i++) {
            // 尝试更新
            Example example = new Example(ProductFitting.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("mainSkuId", skuId);
            criteria.andEqualTo("partSkuId", fittingIdList.get(i));
            criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
            ProductFitting productFitting = new ProductFitting();
            productFitting.setPartSkuId(fittingIdList.get(i));
            productFitting.setSequence(i);
            Long nowTime = System.currentTimeMillis();
            productFitting.setUpdatedTime(nowTime);
            // 先进行更新
            int updCode = productFittingMapper.updateByExampleSelective(productFitting,example);
            // 更新返回值为0，则插入
            if(updCode >0 ){
                continue;
            }
            productFitting.setMainSkuId(skuId);
            productFitting.setCreatedTime(nowTime);
            // 没有，则新插入一条数据
            productFittingMapper.insertSelective(productFitting);
            }
        return 0;
    }

    @Override
    public List<Integer> getFittingIdsByMainSkuId(Integer skuId) {
        // 根据主件skuId获取对应的配件数量
        Example example = new Example(ProductFitting.class);
        // 设置查询条件
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("mainSkuId", skuId);
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        List<ProductFitting> productFittingList = productFittingMapper.selectByExample(example);
        List<ProductFittingDto> productFittingDtoList = productFittingConvert.toFittingDtoList(productFittingList);
        List<Integer> fittingIdList = new ArrayList<>();
        for(ProductFitting pf : productFittingList){
            Integer fittingId = pf.getPartSkuId();
            fittingIdList.add(fittingId);
        }
        return fittingIdList;
    }

    @Override
    public ProductFittingDto getFittingDtoById(Integer mainSkuId, Integer partSkuId) {
        // 根据主件skuId获取对应的配件数量
        Example example = new Example(ProductFitting.class);
        // 设置查询条件
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("mainSkuId", mainSkuId);
        criteria.andEqualTo("partSkuId", partSkuId);
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        ProductFitting productFitting = productFittingMapper.selectOneByExample(example);
        ProductFittingDto productFittingDto = productFittingConvert.toFittingDto(productFitting);
        return productFittingDto;
    }

    @Override
    public int deleteFittingInfo(Integer mainSkuId, Integer partSkuId) {
        Example example = new Example(ProductFitting.class);
        Example.Criteria criteria = example.createCriteria();
        if (mainSkuId != null) {
            criteria.andEqualTo("mainSkuId", mainSkuId);
        }
        if (partSkuId != null) {
            criteria.andEqualTo("partSkuId", partSkuId);
        }
        criteria.andEqualTo("status", ProductConst.DataStatus.NORMAL);
        ProductFitting productFitting = new ProductFitting();
        productFitting.setUpdatedTime(System.currentTimeMillis());
        productFitting.setStatus(ProductConst.DataStatus.DELETED);
        int updCode = productFittingMapper.updateByExampleSelective(productFitting, example);
        return updCode;
    }
}
