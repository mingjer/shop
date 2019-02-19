package net.realme.mall.basics.beantool;

import net.realme.mall.basics.dto.TranslationDto;
import net.realme.mall.basics.model.Translation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TranslationConverter {

    Translation fromTranslationDto(TranslationDto translationDto);

    TranslationDto toTranslationDto(Translation translation);

    List<TranslationDto> toTranslationDtoList(List<Translation> siteList);

    List<Translation> fromTranslationDtoList(List<TranslationDto> siteList);

}

