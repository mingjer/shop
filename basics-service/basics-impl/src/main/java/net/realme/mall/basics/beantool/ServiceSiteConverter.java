package net.realme.mall.basics.beantool;

import java.util.List;

import org.mapstruct.Mapper;

import net.realme.mall.basics.dto.ServiceSiteDto;
import net.realme.mall.basics.model.ServiceSite;

@Mapper(componentModel = "spring")
public interface ServiceSiteConverter {

	ServiceSite fromServiceSiteDto(ServiceSiteDto serviceSiteDto);

	ServiceSiteDto toServiceSiteDto(ServiceSite serviceSite);

	List<ServiceSiteDto> toServiceSiteDtoList(List<ServiceSite> serviceSiteList);

	List<ServiceSite> fromServiceSiteDtoList(List<ServiceSiteDto> serviceSiteDtoList);
}
