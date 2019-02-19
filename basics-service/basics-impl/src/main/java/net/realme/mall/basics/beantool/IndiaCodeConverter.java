package net.realme.mall.basics.beantool;

import net.realme.mall.basics.dto.IndiaPinCodeDto;
import net.realme.mall.basics.model.IndiaPinCode;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.basics.beantool
 *
 * @author 91000044
 * @date 2018/8/28 14:01
 */
@Mapper(componentModel = "spring")
public interface IndiaCodeConverter {

	IndiaPinCode fromIndiaCodeDto(IndiaPinCodeDto pinCodeDto);

	List<IndiaPinCode> fromIndiaCodeDto(List<IndiaPinCodeDto> pinCodeDtos);

	IndiaPinCodeDto toIndiaCodeDto(IndiaPinCode pinCode);

	List<IndiaPinCodeDto> toIndiaCodeDtoList(List<IndiaPinCode> pinCodeList);
}
