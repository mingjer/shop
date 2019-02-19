package net.realme.mall.oms.cms.facade;

import net.realme.framework.util.dto.ResultList;
import net.realme.mall.oms.domain.cms.CmsComponentDto;
import net.realme.mall.oms.domain.cms.CmsComponentsQuery;

import java.util.List;
import java.util.Map;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.cms.facade
 *
 * @author 91000044
 * @date 2018/7/28 15:43
 */
public interface CmsComponentService {

    int add(CmsComponentDto cmsComponentDto);

    int batchAdd(List<CmsComponentDto> cmsComponentDtos);

    /**
     * 更新默认值，如果name有更改，那相关的所有本地记录也要更新
     * @param defaultComponent
     * @param updatedComponent 更新的数据
     * @return
     */
    int updateDefault(CmsComponentDto defaultComponent, CmsComponentDto updatedComponent);

    int updateStatus(int componentId, byte status);

    /**
     * 按类型、名称删除（debug）
     *
     * @param type
     * @param name
     * @return
     */
    int deleteByTypeName(String type, String name);

    boolean isTypeNameExists(String type, String name);

    CmsComponentDto get(int id);

    /**
     * 返回实际站点对应的值，如不存在则返回默认值
     * @param type
     * @param name
     * @param siteCode
     * @return
     */
    CmsComponentDto get(String type, String name, String siteCode);

    int updateL10NByName(String type, String name, List<CmsComponentDto> data);

    /**
     * 根据条件返回默认值列表
     *
     * @param query
     * @return
     */
    ResultList<CmsComponentDto> defaultList(CmsComponentsQuery query);


    /**
     * 按类型返回
     *
     * @param query
     * @return
     */
    ResultList<CmsComponentDto> listByName(CmsComponentsQuery query);

}
