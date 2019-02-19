package net.realme.mall.basics.facade;

import java.util.List;

import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.dto.DivisionDto;

public interface DivisionService {

	ResultT<Integer> addDivision(DivisionDto divisionDto);

	ResultT<Integer> batchAddDivision(List<DivisionDto> divisionDtos);

	ResultT<ResultList<DivisionDto>> getCountryList();

	ResultT<ResultList<DivisionDto>> getProvinceList(String countryId);

	ResultT<ResultList<DivisionDto>> getCityList(String countryId, String provinceId);
}
