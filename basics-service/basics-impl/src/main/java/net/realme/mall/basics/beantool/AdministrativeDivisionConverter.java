package net.realme.mall.basics.beantool;

import java.util.List;

import org.mapstruct.Mapper;

import net.realme.mall.basics.dto.DivisionDto;
import net.realme.mall.basics.model.AdministrativeDivision;

@Mapper(componentModel = "spring")
public interface AdministrativeDivisionConverter {

	AdministrativeDivision fromDivisionDto(DivisionDto divisionDto);

	DivisionDto toDivisionDto(DivisionDto divisionDto);

	List<DivisionDto> toDivisionDtoList(List<AdministrativeDivision> pincodeList);

	List<AdministrativeDivision> fromDivisionDtoList(List<DivisionDto> divisionDtoList);
}
