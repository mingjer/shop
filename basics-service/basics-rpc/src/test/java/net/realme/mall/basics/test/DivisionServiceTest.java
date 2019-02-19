package net.realme.mall.basics.test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.realme.mall.basics.Application;
import net.realme.mall.basics.dto.DivisionDto;
import net.realme.mall.basics.facade.DivisionService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DivisionServiceTest {

	@Autowired
	private DivisionService divisionService;

	@Test
	public void testAddDivision() {
		DivisionDto divisionDto = new DivisionDto();
		divisionDto.setDivisionId("");
		divisionDto.setDivisionCode("");
		divisionDto.setDivisionName("");
		divisionDto.setDivisionType("");
		divisionDto.setParentId("");
		divisionDto.setParentName("");
		divisionDto.setIdPath("");
		divisionDto.setNamePath("");
		divisionDto.setHasChild(Byte.valueOf("1"));
		divisionDto.setCountryCode("");
		divisionDto.setStatus(Byte.valueOf("1"));
		divisionDto.setSequence(1);
		Long time = new Date().getTime();
		divisionDto.setCreatedAt(time);
		divisionDto.setUpdatedAt(time);
		divisionService.addDivision(divisionDto);
	}

	@Test
	public void batchAddDivisionFromExcel() throws IOException {
		List<DivisionDto> divisionDtos = DivisionExcelDataUtil.getDivisionDtoListFromExcel();
		for (DivisionDto divisionDto : divisionDtos) {
			if("IN-PB-06".equals(divisionDto.getDivisionId())) {
				System.out.println(divisionDto);
			}
		}
		//divisionService.batchAddDivision(divisionDtos);
	}

}
