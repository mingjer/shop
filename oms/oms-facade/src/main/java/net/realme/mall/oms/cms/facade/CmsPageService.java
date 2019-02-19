package net.realme.mall.oms.cms.facade;

import net.realme.framework.util.dto.ResultList;
import net.realme.mall.oms.domain.cms.CmsPageDto;
import net.realme.mall.oms.domain.cms.CmsPageQuery;

import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.cms.facade
 *
 * @author 91000044
 * @date 2018/7/28 16:26
 */
public interface CmsPageService {

    final byte STATUS_INPROGRESS = 0;
    final byte STATUS_RELEASED = 1;
    final byte STATUS_FAILED = 2;

    int add(CmsPageDto cmsPageDto);

    int update(CmsPageDto cmsPageDto);

    int updateStatus(int pageId, byte status);


    /**
     * 返回页面
     *
     * @param id
     * @return
     */
    CmsPageDto get(int id);


    /**
     * 按站点、创建人等分页返回页面
     *
     * @param query
     * @return
     */
    ResultList<CmsPageDto> list(CmsPageQuery query);

    /**
     * 请求发布
     *
     * @param pageId
     * @param siteCode
     * @param version
     * @return
     */
    int requestToRelease(int pageId, String siteCode, long version);

    /**
     * 发布成功
     *
     * @param pageId
     * @param siteCode
     * @param version
     * @param htmlContent
     * @return
     */
    int releaseDone(int pageId, String siteCode, long version, String htmlContent);
}
