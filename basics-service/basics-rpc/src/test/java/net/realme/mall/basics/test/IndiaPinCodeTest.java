package net.realme.mall.basics.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.Application;
import net.realme.mall.basics.dto.IndiaPinCodeDto;
import net.realme.mall.basics.facade.IndiaPinCodeService;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.basics.test
 *
 * @author 91000044
 * @date 2018/8/28 15:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class IndiaPinCodeTest {

	@Autowired
	private IndiaPinCodeService indiaPinCodeService;

	@Test
	public void getByPinCode() {
		ResultT<IndiaPinCodeDto> indiaPinCodeDto = indiaPinCodeService.getByPinCode("691559");
		System.out.println(indiaPinCodeDto.isSuccess());
		System.out.println(indiaPinCodeDto.getData());
	}

	@Test
	public void updateBatchPinCode() throws IOException {
		Map<String, String> codeMap = DivisionExcelDataUtil.getDivisionCodeFromExcel();
		ResultT<ResultList<IndiaPinCodeDto>> result = indiaPinCodeService.listAll();
		List<IndiaPinCodeDto> pinCodeDtos = result.getData().getRecords();
		for (IndiaPinCodeDto pinCodeDto : pinCodeDtos) {
			pinCodeDto.setCountryId("IN");
			pinCodeDto.setCountryName("India");
			pinCodeDto.setProvinceId(codeMap.get(pinCodeDto.getProvinceName()));
			if (StringUtils.isBlank(pinCodeDto.getCityId())) {
				pinCodeDto.setCityId(codeMap.get(pinCodeDto.getProvinceName() + "-" + pinCodeDto.getCityName()));
				indiaPinCodeService.updatePinCode(pinCodeDto);
			}
		}
	}
}
