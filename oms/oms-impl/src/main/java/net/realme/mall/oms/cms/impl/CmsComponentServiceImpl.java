package net.realme.mall.oms.cms.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.realme.framework.util.dto.ResultList;
import net.realme.mall.basics.dto.SiteEnum;
import net.realme.mall.oms.cms.beantool.CmsComponentConverter;
import net.realme.mall.oms.cms.dao.CmsComponentMapper;
import net.realme.mall.oms.cms.dao.CmsComponentSiteMapper;
import net.realme.mall.oms.cms.model.CmsComponentSite;
import net.realme.mall.oms.domain.cms.CmsComponentDto;
import net.realme.mall.oms.domain.cms.CmsComponentsQuery;
import net.realme.mall.oms.cms.facade.CmsComponentService;
import net.realme.mall.oms.cms.model.CmsComponent;
import org.apache.commons.lang3.StringUtils;
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
 * package: net.realme.mall.oms.cms.impl
 *
 * @author 91000044
 * @date 2018/7/28 16:49
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class CmsComponentServiceImpl implements CmsComponentService {

    @Autowired
    private CmsComponentConverter cmsComponentConverter;

    @Autowired
    private CmsComponentMapper cmsComponentMapper;

    @Autowired
    private CmsComponentSiteMapper cmsComponentSiteMapper;


    @Override
    public int add(CmsComponentDto cmsComponentDto) {
        CmsComponent cmsComponent = cmsComponentConverter.fromCmsComponentDto(cmsComponentDto);
        if (cmsComponentMapper.insert(cmsComponent) > 0) {
            int componentId = cmsComponent.getId();
            List<CmsComponentSite> componentSites = new ArrayList<>();
            String[] siteCodes = cmsComponent.getSiteCodes().split(",");
            for (String siteCode: siteCodes) {
                CmsComponentSite cmsComponentSite = new CmsComponentSite();
                cmsComponentSite.setComponentId(componentId);
                cmsComponentSite.setComponentType(cmsComponentDto.getType());
                cmsComponentSite.setComponentName(cmsComponentDto.getName());
                cmsComponentSite.setSiteCode(siteCode);
                componentSites.add(cmsComponentSite);
            }
            cmsComponentSiteMapper.batchInsert(componentSites);
            return componentId;
        }
        return 0;
    }

    @Override
    public int batchAdd(List<CmsComponentDto> cmsComponentDtos) {
        if (cmsComponentDtos == null || cmsComponentDtos.isEmpty()) {
            return 0;
        }
        int total = 0;
        for (CmsComponentDto cmsComponentDto : cmsComponentDtos) {
            total += add(cmsComponentDto);
        }
        return total;
    }

    @Override
    public int updateDefault(CmsComponentDto defaultComponent, CmsComponentDto updatedComponent) {
        String oldName = defaultComponent.getName();
        defaultComponent.setName(updatedComponent.getName());
        defaultComponent.setContent(updatedComponent.getContent());
        defaultComponent.setUpdatedAt(System.currentTimeMillis());
        if (cmsComponentMapper.updateByPrimaryKey(cmsComponentConverter.fromCmsComponentDto(defaultComponent)) <= 0) {
            return 0;
        }
        //如果名称改了相关联的name都要改
        if (!oldName.equals(updatedComponent.getName())) {
            Example example = new Example(CmsComponent.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("type", defaultComponent.getType());
            criteria.andEqualTo("name", oldName);
            CmsComponent l10n = new CmsComponent();
            l10n.setName(updatedComponent.getName());
            cmsComponentMapper.updateByExampleSelective(l10n, example);
        }
        return 1;
    }

    @Override
    public int updateStatus(int componentId, byte status) {
        CmsComponent cmsComponent = cmsComponentMapper.selectByPrimaryKey(componentId);
        if (cmsComponent != null) {
            return cmsComponentMapper.updateStatus(cmsComponent.getType(), cmsComponent.getName(), status);
        }
        return 0;
    }

    @Override
    public int deleteByTypeName(String type, String name) {
        Example example = new Example(CmsComponent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", type);
        criteria.andEqualTo("name", name);
        int total = cmsComponentMapper.deleteByExample(example);

        example = new Example(CmsComponentSite.class);
        criteria = example.createCriteria();
        criteria.andEqualTo("componentType", type);
        criteria.andEqualTo("componentName", name);
        cmsComponentSiteMapper.deleteByExample(example);
        return total;
    }

    @Override
    public boolean isTypeNameExists(String type, String name) {
        Example example = new Example(CmsComponent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", type);
        criteria.andEqualTo("name", name);
        criteria.andEqualTo("isDefault", 1);
        return cmsComponentMapper.selectOneByExample(example) != null;
    }

    @Override
    public CmsComponentDto get(int id) {
        CmsComponent cmsComponent = cmsComponentMapper.selectByPrimaryKey(id);
        if (cmsComponent != null) {
            return cmsComponentConverter.toCmsComponentDto(cmsComponent);
        }
        return null;
    }

    @Override
    public CmsComponentDto get(String type, String name, String siteCode) {
        List<CmsComponent> cmsComponents = cmsComponentMapper.selectBySite(type, name, siteCode);
        if (cmsComponents != null && !cmsComponents.isEmpty()) {
            CmsComponent expected = null;
            for (CmsComponent cmsComponent : cmsComponents) {
                if (expected == null || ( cmsComponent.getIsDefault() == 0) ) {
                    expected = cmsComponent;
                }
            }
            return cmsComponentConverter.toCmsComponentDto(expected);
        }
        return null;
    }

    @Override
    public int updateL10NByName(String type, String name, List<CmsComponentDto> data) {
        //删掉所有本地化的
        Example example = new Example(CmsComponent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", type);
        criteria.andEqualTo("name", name);
        criteria.andEqualTo("isDefault", 0);
        cmsComponentMapper.deleteByExample(example);
        //删掉关联表中的
        example = new Example(CmsComponentSite.class);
        criteria = example.createCriteria();
        criteria.andEqualTo("componentType", type);
        criteria.andEqualTo("componentName", name);
        criteria.andNotEqualTo("siteCode", SiteEnum.DEFAULT_SITE.getValue());
        cmsComponentSiteMapper.deleteByExample(example);

        int total = 0;
        for (CmsComponentDto componentDto : data) {
            total += add(componentDto);
        }
        return total;
    }

    @Override
    public ResultList<CmsComponentDto> defaultList(CmsComponentsQuery query) {
        ResultList<CmsComponentDto> result = new ResultList<>();
        if (query == null) {
            return result;
        }
        if( query.getPage() == null || query.getLimit() == null) {
            return result;
        }
        int page = query.getPage();
        int limit = query.getLimit();
        String orderBy = "updated_at DESC";
        if (StringUtils.isNotEmpty(query.getOrderBy())) {
            orderBy = query.getOrderBy();
        }
        PageHelper.startPage(page, limit, true).setOrderBy(orderBy);
        Example example = new Example(CmsComponent.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(query.getType())) {
            criteria.andEqualTo("type", query.getType());
        }
        criteria.andEqualTo("isDefault", 1);
        List<CmsComponent> records = cmsComponentMapper.selectByExample(example);
        PageInfo<CmsComponent> pageInfo = new PageInfo<>(records);
        if (pageInfo.getTotal() > 0) {
            List<CmsComponentDto> dtoRecords = cmsComponentConverter.toCmsComponentDtoList(pageInfo.getList());
            result.setRecords(dtoRecords);
        }
        result.setPageNum(page);
        result.setPageSize(limit);
        result.setRecordTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public ResultList<CmsComponentDto> listByName(CmsComponentsQuery query) {
        ResultList<CmsComponentDto> result = new ResultList<>();
        if (query == null) {
            return result;
        }
        String orderBy = "is_default DESC, updated_at DESC";
        if (StringUtils.isNotEmpty(query.getOrderBy())) {
            orderBy = query.getOrderBy();
        }
        Example example = new Example(CmsComponent.class);
        example.setOrderByClause(orderBy);
        if (StringUtils.isEmpty(query.getType()) || StringUtils.isEmpty(query.getName())) {
            return result;
        }
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", query.getType());
        criteria.andEqualTo("name", query.getName());
        criteria.andNotEqualTo("siteCodes", SiteEnum.DEFAULT_SITE.getValue());
        List<CmsComponent> records = cmsComponentMapper.selectByExample(example);
        PageInfo<CmsComponent> pageInfo = new PageInfo<>(records);
        if (pageInfo.getTotal() > 0) {
            List<CmsComponentDto> dtoRecords = cmsComponentConverter.toCmsComponentDtoList(pageInfo.getList());
            result.setRecords(dtoRecords);
        }
        result.setPageNum(1);
        result.setPageSize(Math.toIntExact(pageInfo.getTotal()));
        result.setRecordTotal(pageInfo.getTotal());
        return result;
    }
}
