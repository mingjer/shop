package net.realme.mall.oms.cms.dao;

import net.realme.mall.oms.cms.model.CmsPageSite;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CmsPageSiteMapper extends Mapper<CmsPageSite> {

    int batchInsert(List<CmsPageSite> list);
}