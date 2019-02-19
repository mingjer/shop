package net.realme.mall.oms.cms.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.realme.framework.util.dto.ResultList;
import net.realme.mall.oms.cms.beantool.CmsPageConverter;
import net.realme.mall.oms.cms.dao.CmsPageMapper;
import net.realme.mall.oms.cms.dao.CmsPageReleaseMapper;
import net.realme.mall.oms.cms.dao.CmsPageSiteMapper;
import net.realme.mall.oms.cms.facade.CmsPageService;
import net.realme.mall.oms.cms.model.CmsComponentSite;
import net.realme.mall.oms.cms.model.CmsPage;
import net.realme.mall.oms.cms.model.CmsPageRelease;
import net.realme.mall.oms.cms.model.CmsPageSite;
import net.realme.mall.oms.domain.cms.CmsPageDto;
import net.realme.mall.oms.domain.cms.CmsPageQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.cms.impl
 *
 * @author 91000044
 * @date 2018/7/28 16:50
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class CmsPageServiceImpl implements CmsPageService {

    @Autowired
    private CmsPageMapper cmsPageMapper;

    @Autowired
    private CmsPageSiteMapper cmsPageSiteMapper;

    @Autowired
    private CmsPageConverter cmsPageConverter;

    @Autowired
    private CmsPageReleaseMapper cmsPageReleaseMapper;

    @Override
    public int add(CmsPageDto cmsPageDto) {
        CmsPage cmsPage = cmsPageConverter.fromCmsPageDto(cmsPageDto);
        if (cmsPageMapper.insert(cmsPage) > 0) {
            int pageId = cmsPage.getId();
            addSite(pageId, cmsPageDto.getUri(), cmsPageDto.getSiteCodesArr());
            return pageId;
        }
        return 0;
    }

    @Override
    public int update(CmsPageDto cmsPageDto) {
        int pageId = cmsPageDto.getId();
        CmsPage cmsPage = cmsPageConverter.fromCmsPageDto(cmsPageDto);
        if (cmsPageMapper.updateByPrimaryKeySelective(cmsPage) > 0) {
            Example example = new Example(CmsPageSite.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("pageId", pageId);
            cmsPageSiteMapper.deleteByExample(example);

            addSite(pageId, cmsPageDto.getUri(), cmsPageDto.getSiteCodesArr());
            return 1;
        }
        return 0;
    }

    private void addSite(int pageId, String uri, String[] siteCodes) {
        List<CmsPageSite> pageSites = new ArrayList<>();
        for (String siteCode : siteCodes) {
            CmsPageSite cmsPageSite = new CmsPageSite();
            cmsPageSite.setPageId(pageId);
            cmsPageSite.setSiteCode(siteCode);
            cmsPageSite.setUri(uri);
            pageSites.add(cmsPageSite);
        }
        cmsPageSiteMapper.batchInsert(pageSites);
    }

    @Override
    public int updateStatus(int pageId, byte status) {
        CmsPage cmsPage = new CmsPage();
        cmsPage.setId(pageId);
        cmsPage.setStatus(status);
        if (cmsPageMapper.updateByPrimaryKeySelective(cmsPage) > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public CmsPageDto get(int id) {
        CmsPage cmsPage = cmsPageMapper.selectByPrimaryKey(id);
        return cmsPageConverter.toCmsPageDto(cmsPage);
    }

    @Override
    public ResultList<CmsPageDto> list(CmsPageQuery query) {
        ResultList<CmsPageDto> result = new ResultList<>();
        if (query == null) {
            return result;
        }
        if (query.getPage() == null || query.getLimit() == null) {
            return result;
        }
        int page = query.getPage();
        int limit = query.getLimit();
        String orderBy = "updated_at DESC";
        if (StringUtils.isNotEmpty(query.getOrderBy())) {
            orderBy = query.getOrderBy();
        }
        PageHelper.startPage(page, limit, true).setOrderBy(orderBy);
//        Example example = new Example(CmsPage.class);
//        Example.Criteria criteria = example.createCriteria();
//        List<CmsPage> records = cmsPageMapper.selectByExample(example);
        List<CmsPage> records = cmsPageMapper.selectAll();
        PageInfo<CmsPage> pageInfo = new PageInfo<>(records);
        if (pageInfo.getTotal() > 0) {
            List<CmsPageDto> dtoRecords = cmsPageConverter.toCmsPageDtoList(pageInfo.getList());
            result.setRecords(dtoRecords);
        }
        result.setPageNum(page);
        result.setPageSize(limit);
        result.setRecordTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public int requestToRelease(int pageId, String siteCode, long version) {
        CmsPageRelease cmsPageRelease = new CmsPageRelease();
        cmsPageRelease.setPageId(pageId);
        cmsPageRelease.setStatus(STATUS_INPROGRESS);
        cmsPageRelease.setSiteCode(siteCode);
        cmsPageRelease.setVersion(version);
        return cmsPageReleaseMapper.insert(cmsPageRelease);
    }

    @Override
    public int releaseDone(int pageId, String siteCode, long version, String htmlContent) {
        Example example = new Example(CmsPageRelease.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pageId", pageId);
        criteria.andEqualTo("siteCode", siteCode);
        criteria.andEqualTo("version", version);
        CmsPageRelease cmsPageRelease = cmsPageReleaseMapper.selectOneByExample(example);
        if (cmsPageRelease != null) {
            cmsPageRelease.setStatus(STATUS_RELEASED);
            cmsPageRelease.setRenderedHtml(htmlContent);
            cmsPageRelease.setReleasedAt(System.currentTimeMillis());
            return cmsPageReleaseMapper.updateByPrimaryKeySelective(cmsPageRelease);
        }
        return 0;
    }
}
