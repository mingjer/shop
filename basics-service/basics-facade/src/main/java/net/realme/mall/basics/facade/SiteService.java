package net.realme.mall.basics.facade;

import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.dto.SiteDto;

public interface SiteService {
    ResultT<Integer> addSite(SiteDto siteDto);

    ResultT<Integer>  updateSite(SiteDto siteDao);

    ResultT<SiteDto> getById(int id);

    ResultT<ResultList<SiteDto>> list(int page , int limit);

    ResultT<Integer>  updateSiteStatus(SiteDto siteDao);

    ResultT<ResultList<SiteDto>> listAll(int page, int limit);

    ResultT<SiteDto> getBySiteCode(String siteCode);
}