package net.realme.mall.basics.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.realme.framework.cache.redis.RedisCache;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.beantool.IndiaCodeConverter;
import net.realme.mall.basics.dao.IndiaPinCodeMapper;
import net.realme.mall.basics.dto.IndiaPinCodeDto;
import net.realme.mall.basics.facade.IndiaPinCodeService;
import net.realme.mall.basics.model.IndiaPinCode;
import tk.mybatis.mapper.entity.Example;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.basics.impl
 *
 * @author 91000044
 * @date 2018/8/28 13:58
 */
@Service
public class IndiaPinCodeServiceImpl implements IndiaPinCodeService {

	private static final String PINCODE_CACHE_KEY = "PINCODE_CACHE_";

	@Autowired
	private IndiaPinCodeMapper indiaPinCodeMapper;

	@Autowired
	private IndiaCodeConverter indiaCodeConverter;

	@Autowired
	private RedisCache<IndiaPinCodeDto> redisCache;

	@Override
	public ResultT<ResultList<IndiaPinCodeDto>> list(int page, int limit) {
		PageHelper.startPage(page, limit, true);
		List<IndiaPinCode> records = indiaPinCodeMapper.selectAll();
		PageInfo<IndiaPinCode> pageInfo = new PageInfo<>(records);
		ResultList<IndiaPinCodeDto> result = new ResultList<>();
		if (pageInfo.getTotal() > 0) {
			List<IndiaPinCodeDto> dtoRecords = indiaCodeConverter.toIndiaCodeDtoList(pageInfo.getList());
			result.setRecords(dtoRecords);
		}
		result.setPageNum(page);
		result.setPageSize(limit);
		result.setRecordTotal(pageInfo.getTotal());
		return ResultT.success(result);
	}

	@Override
	public ResultT<ResultList<IndiaPinCodeDto>> listAll() {
		List<IndiaPinCode> records = indiaPinCodeMapper.selectAll();
		ResultList<IndiaPinCodeDto> result = new ResultList<>();
		if (!records.isEmpty()) {
			List<IndiaPinCodeDto> dtoRecords = indiaCodeConverter.toIndiaCodeDtoList(records);
			result.setRecords(dtoRecords);
		}
		result.setPageNum(1);
		result.setPageSize(records.size());
		result.setRecordTotal(records.size());
		return ResultT.success(result);
	}

	@Override
	public ResultT<IndiaPinCodeDto> getByPinCode(String pinCode) {
		if (StringUtils.isBlank(pinCode)) {
			return ResultT.fail();
		}
		IndiaPinCodeDto cachePinCode = redisCache.get(PINCODE_CACHE_KEY + pinCode);
		if (cachePinCode != null) {
			ResultT.success(cachePinCode);
		}
		Example example = new Example(IndiaPinCode.class);
		example.createCriteria().andEqualTo("pinCode", pinCode);
		IndiaPinCode indiaPinCode = indiaPinCodeMapper.selectOneByExample(example);
		if (indiaPinCode != null) {
			IndiaPinCodeDto pinCodeDto = indiaCodeConverter.toIndiaCodeDto(indiaPinCode);
			redisCache.set(PINCODE_CACHE_KEY + pinCode, pinCodeDto);
			return ResultT.success(pinCodeDto);
		}
		return ResultT.fail();
	}

	@Override
	public ResultT<Integer> addPinCode(IndiaPinCodeDto indiaPinCodeDto) {
		IndiaPinCode indiaPinCode = indiaCodeConverter.fromIndiaCodeDto(indiaPinCodeDto);
		int ret = indiaPinCodeMapper.insert(indiaPinCode);
		if (ret > 0) {
			return ResultT.success(1);
		}
		return ResultT.fail();
	}

	@Override
	public ResultT<Integer> updatePinCode(IndiaPinCodeDto indiaPinCodeDto) {
		IndiaPinCode indiaPinCode = indiaCodeConverter.fromIndiaCodeDto(indiaPinCodeDto);
		int ret = indiaPinCodeMapper.updateByPrimaryKey(indiaPinCode);
		if (ret > 0) {
			redisCache.delete(PINCODE_CACHE_KEY + indiaPinCodeDto.getId());
			return ResultT.success(1);
		}
		return ResultT.fail();
	}

}
