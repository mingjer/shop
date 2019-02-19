package net.realme.mall.basics.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.realme.framework.cache.redis.RedisCache;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.beantool.AdministrativeDivisionConverter;
import net.realme.mall.basics.common.consts.DivisionConst;
import net.realme.mall.basics.dao.AdministrativeDivisionMapper;
import net.realme.mall.basics.dto.DivisionDto;
import net.realme.mall.basics.facade.DivisionService;
import net.realme.mall.basics.model.AdministrativeDivision;
import tk.mybatis.mapper.entity.Example;

@Service
public class DivisionServiceImpl implements DivisionService {

	private static final String COUNTRY_LIST_CACHE_KEY = "COUNTRY_LIST_CACHE";
	private static final String PROVINCE_LIST_CACHE_KEY = "PROVINCE_LIST_CACHE_";
	private static final String CITY_LIST_CACHE_KEY = "CITY_LIST_CACHE_";

	@Autowired
	private AdministrativeDivisionMapper administrativeDivisionMapper;

	@Autowired
	private AdministrativeDivisionConverter administrativeDivisionConverter;

	@Autowired
	private RedisCache<List<DivisionDto>> redisCache;

	@Override
	public ResultT<Integer> addDivision(DivisionDto divisionDto) {
		AdministrativeDivision division = administrativeDivisionConverter.fromDivisionDto(divisionDto);
		if (administrativeDivisionMapper.insert(division) > 0) {
			return ResultT.success(1);
		}
		return ResultT.fail();
	}

	@Override
	public ResultT<Integer> batchAddDivision(List<DivisionDto> divisionDtos) {
		List<AdministrativeDivision> divisionList = administrativeDivisionConverter.fromDivisionDtoList(divisionDtos);
		if (administrativeDivisionMapper.batchInsert(divisionList) > 0) {
			return ResultT.success(1);
		}
		return ResultT.fail();
	}

	@Override
	public ResultT<ResultList<DivisionDto>> getCountryList() {
		ResultList<DivisionDto> result = new ResultList<>();
		result.setPageNum(1);
		result.setPageSize(0);
		result.setRecordTotal(0);
		List<DivisionDto> dtoRecords = this.getAllCountry();
		if (dtoRecords != null) {
			result.setRecords(dtoRecords);
			result.setPageSize(dtoRecords.size());
			result.setRecordTotal(dtoRecords.size());
		}
		return ResultT.success(result);
	}

	private List<DivisionDto> getAllCountry() {
		List<DivisionDto> dtoRecords = redisCache.get(COUNTRY_LIST_CACHE_KEY);
		if (dtoRecords != null && !dtoRecords.isEmpty()) {
			return dtoRecords;
		}
		Example example = new Example(AdministrativeDivision.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("divisionType", DivisionConst.DivisionType.COUNTRY.getValue());
		example.setOrderByClause("division_name ASC");
		List<AdministrativeDivision> divisionList = administrativeDivisionMapper.selectByExample(example);
		if (divisionList.size() > 0) {
			dtoRecords = administrativeDivisionConverter.toDivisionDtoList(divisionList);
			redisCache.set(COUNTRY_LIST_CACHE_KEY, dtoRecords);
		}
		return dtoRecords;
	}

	@Override
	public ResultT<ResultList<DivisionDto>> getProvinceList(String countryId) {
		ResultList<DivisionDto> result = new ResultList<>();
		result.setPageNum(1);
		result.setPageSize(0);
		result.setRecordTotal(0);
		List<DivisionDto> dtoRecords = this.getProvinceByCountryId(countryId);
		if (dtoRecords != null) {
			result.setRecords(dtoRecords);
			result.setPageSize(dtoRecords.size());
			result.setRecordTotal(dtoRecords.size());
		}
		return ResultT.success(result);
	}

	private List<DivisionDto> getProvinceByCountryId(String countryId) {
		List<DivisionDto> dtoRecords = redisCache.get(PROVINCE_LIST_CACHE_KEY + countryId);
		if (dtoRecords != null && !dtoRecords.isEmpty()) {
			return dtoRecords;
		}
		Example example = new Example(AdministrativeDivision.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("divisionType", DivisionConst.DivisionType.PROVINCE.getValue());
		criteria.andEqualTo("countryCode", countryId);
		example.setOrderByClause("division_name ASC");
		List<AdministrativeDivision> divisionList = administrativeDivisionMapper.selectByExample(example);
		if (divisionList.size() > 0) {
			dtoRecords = administrativeDivisionConverter.toDivisionDtoList(divisionList);
			redisCache.set(PROVINCE_LIST_CACHE_KEY + countryId, dtoRecords);
		}
		return dtoRecords;
	}

	@Override
	public ResultT<ResultList<DivisionDto>> getCityList(String countryId, String provinceId) {
		ResultList<DivisionDto> result = new ResultList<>();
		result.setPageNum(1);
		result.setPageSize(0);
		result.setRecordTotal(0);
		List<DivisionDto> dtoRecords = this.getCityByProvinceId(countryId, provinceId);
		if (dtoRecords != null) {
			result.setRecords(dtoRecords);
			result.setPageSize(dtoRecords.size());
			result.setRecordTotal(dtoRecords.size());
		}
		return ResultT.success(result);
	}

	private List<DivisionDto> getCityByProvinceId(String countryId, String provinceId) {
		List<DivisionDto> dtoRecords = redisCache.get(CITY_LIST_CACHE_KEY + countryId + "_" + provinceId);
		if (dtoRecords != null && !dtoRecords.isEmpty()) {
			return dtoRecords;
		}
		Example example = new Example(AdministrativeDivision.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("divisionType", DivisionConst.DivisionType.CITY.getValue());
		criteria.andEqualTo("countryCode", countryId);
		criteria.andEqualTo("parentId", provinceId);
		example.setOrderByClause("division_name ASC");
		List<AdministrativeDivision> divisionList = administrativeDivisionMapper.selectByExample(example);
		if (divisionList.size() > 0) {
			dtoRecords = administrativeDivisionConverter.toDivisionDtoList(divisionList);
			redisCache.set(CITY_LIST_CACHE_KEY + countryId + "_" + provinceId, dtoRecords);
		}
		return dtoRecords;
	}
}
