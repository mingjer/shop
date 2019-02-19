package net.realme.mall.oms.cms.dao;

import net.realme.mall.oms.cms.model.CmsComponentSite;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CmsComponentSiteMapper extends Mapper<CmsComponentSite> {

    int batchInsert(List<CmsComponentSite> list);
}