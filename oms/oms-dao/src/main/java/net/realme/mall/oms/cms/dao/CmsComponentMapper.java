package net.realme.mall.oms.cms.dao;

import net.realme.mall.oms.cms.model.CmsComponent;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CmsComponentMapper extends Mapper<CmsComponent> {

    int updateStatus(@Param("type") String type, @Param("name") String name, @Param("status") byte status);

    List<CmsComponent> selectBySite(@Param("type") String type, @Param("name") String name,@Param("siteCode") String siteCode);

    List<CmsComponent> selectForRender(String type, String siteCode, List<String> names);
}