package net.realme.mall.basics.facade;

import java.util.List;

import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.dto.ServiceSiteDto;

public interface ServiceSiteService {

	ResultT<ResultList<ServiceSiteDto>> list(String countryId, String provinceName, String cityName);

	ResultT<ResultList<ServiceSiteDto>> list(ServiceSiteDto serviceSiteDto, int page, int limit);

	ResultT<ResultList<ServiceSiteDto>> listAll();

	ResultT<ServiceSiteDto> getServiceSiteById(Integer serviceSiteId);

	ResultT<Integer> addServiceSite(ServiceSiteDto serviceSiteDto);

	ResultT<Integer> updateServiceSite(ServiceSiteDto serviceSiteDto);

	ResultT<Integer> batchAddServiceSite(List<ServiceSiteDto> serviceSiteDtos);
}
