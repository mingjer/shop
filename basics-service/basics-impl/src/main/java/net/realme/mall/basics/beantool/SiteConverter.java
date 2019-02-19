package net.realme.mall.basics.beantool;

import net.realme.mall.basics.dto.SiteDto;
import net.realme.mall.basics.model.Site;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SiteConverter {

    Site fromSiteDto(SiteDto siteDto);

    SiteDto toSitDto(Site site);

    List<SiteDto> toSiteDtoList(List<Site> siteList);
}
