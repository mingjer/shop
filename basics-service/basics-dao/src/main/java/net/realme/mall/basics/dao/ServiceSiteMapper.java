package net.realme.mall.basics.dao;

import java.util.List;

import net.realme.mall.basics.model.ServiceSite;
import tk.mybatis.mapper.common.Mapper;

public interface ServiceSiteMapper extends Mapper<ServiceSite> {
	int batchInsert(List<ServiceSite> list);
}