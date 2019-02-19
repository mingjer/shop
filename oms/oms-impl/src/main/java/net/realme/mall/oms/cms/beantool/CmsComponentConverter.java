package net.realme.mall.oms.cms.beantool;

import net.realme.mall.oms.cms.model.CmsComponent;
import net.realme.mall.oms.domain.cms.CmsComponentDto;

import org.mapstruct.Mapper;

import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.cms.beantool
 *
 * @author 91000044
 * @date 2018/7/28 16:52
 */
@Mapper(componentModel = "spring")
public interface CmsComponentConverter {

    CmsComponentDto toCmsComponentDto(CmsComponent cmsComponent);

    CmsComponent fromCmsComponentDto(CmsComponentDto cmsComponentDto);

    List<CmsComponentDto> toCmsComponentDtoList(List<CmsComponent> list);

    List<CmsComponent> fromCmsComponentDtoList(List<CmsComponentDto> list);
}
