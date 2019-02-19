package net.realme.mall.oms.cms.impl;

import net.realme.mall.oms.cms.dao.SkuPageReleaseMapper;
import net.realme.mall.oms.cms.facade.CmsPageService;
import net.realme.mall.oms.cms.facade.ProductDetailPageService;
import net.realme.mall.oms.cms.ftl.ProductDetailPageEngine;
import net.realme.mall.oms.cms.model.SkuPageRelease;
import net.realme.mall.product.facade.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import tk.mybatis.mapper.entity.Example;

/**
 * Created by 91000156 on 2018/9/6 11:42
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ProductDetailPageServiceImpl implements ProductDetailPageService {

    @Autowired
    private CmsPageService cmsPageService;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private ProductDetailPageEngine productDetailPageEngine;

    @Autowired
    private SkuPageReleaseMapper skuPageReleaseMapper;

    @Override
    public int releaseStart(int skuId, int pageId, String siteCode, long version) {
        SkuPageRelease skuPageRelease = new SkuPageRelease();
        skuPageRelease.setSkuId(skuId);
        skuPageRelease.setPageId(pageId);
        skuPageRelease.setStatus(STATUS_INPROGRESS);
        skuPageRelease.setSiteCode(siteCode);
        skuPageRelease.setVersion(version);
        return skuPageReleaseMapper.insert(skuPageRelease);
    }

    @Override
    public int releaseEnd(int skuId, long version, String htmlContent) {
        Example example = new Example(SkuPageRelease.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("skuId", skuId);
        criteria.andEqualTo("version", version);
        SkuPageRelease skuPageRelease = skuPageReleaseMapper.selectOneByExample(example);
        if (skuPageRelease != null) {
            skuPageRelease.setStatus(STATUS_RELEASED);
            skuPageRelease.setRenderedHtml(htmlContent);
            skuPageRelease.setReleasedAt(System.currentTimeMillis());
            return skuPageReleaseMapper.updateByPrimaryKeySelective(skuPageRelease);
        }
        return 0;
    }
}
