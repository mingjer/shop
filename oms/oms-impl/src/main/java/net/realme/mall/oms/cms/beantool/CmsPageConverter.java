package net.realme.mall.oms.cms.beantool;

import net.realme.mall.oms.cms.model.CmsPage;
import net.realme.mall.oms.domain.cms.CmsPageDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.cms.beantool
 *
 * @author 91000044
 * @date 2018/7/31 16:30
 */
@Mapper(componentModel = "spring")
public interface CmsPageConverter {

    CmsPageDto toCmsPageDto(CmsPage cmsPage);

    CmsPage fromCmsPageDto(CmsPageDto cmsPageDto);

    List<CmsPageDto> toCmsPageDtoList(List<CmsPage> list);

    List<CmsPage> fromCmsPageDtoList(List<CmsPageDto> list);
}
