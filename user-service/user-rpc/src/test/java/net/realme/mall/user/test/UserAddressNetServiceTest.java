package net.realme.mall.user.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.user.Application;
import net.realme.mall.user.domain.UserAddressDto;
import net.realme.mall.user.facade.UserAddressService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserAddressNetServiceTest {

	@Autowired
	private UserAddressService userAddressNetService;

	@Test
	public void addUserAddress() {
		UserAddressDto userAddressDto = new UserAddressDto();
		userAddressDto.setSsoid(15452L);
		userAddressDto.setPostCode("7221056");
		userAddressDto.setFullName("liutangqi");
		userAddressDto.setCountyId("IN");
		userAddressDto.setCountyName("India");
		userAddressDto.setProvinceId("IN-WB");
		userAddressDto.setProvinceName("West Bengal");
		userAddressDto.setCityId("IN-WB-03");
		userAddressDto.setCityName("Bankura");
		userAddressDto.setIsDefault(Byte.valueOf("0"));
		userAddressDto.setPhoneCallingCodes("+91");
		userAddressDto.setPhoneNumber("1545265");
		userAddressDto.setAddress1("afswerw");
		userAddressDto.setAddress2("werwsfsdf");
		userAddressDto.setEmail("15856462@qq.com");
		userAddressNetService.add(userAddressDto);
	}

	@Test
	public void getUserAddressList() {
		Long ssoid = 15452L;
		ResultT<ResultList<UserAddressDto>> result = userAddressNetService.listAll(ssoid);
		List<UserAddressDto> list = result.getData().getRecords();
		for (UserAddressDto userAddressDto : list) {
			System.out.println(userAddressDto);
		}
	}

	@Test
	public void updateUserAddress() {
		UserAddressDto userAddressDto = new UserAddressDto();
		userAddressDto.setId(108300969657384960L);
		userAddressDto.setPostCode("722105");
		userAddressDto.setFullName("liutangqi");
		userAddressDto.setCountyId("IN");
		userAddressDto.setCountyName("India");
		userAddressDto.setProvinceId("IN-WB");
		userAddressDto.setProvinceName("West Bengal");
		userAddressDto.setCityId("IN-WB-03");
		userAddressDto.setCityName("Bankura");
		userAddressDto.setIsDefault(Byte.valueOf("0"));
		userAddressDto.setPhoneCallingCodes("+91");
		userAddressDto.setPhoneNumber("1545265");
		userAddressDto.setAddress1("afswerw");
		userAddressDto.setAddress2("werwsfsdf");
		userAddressDto.setEmail("15856462@qq.com");
		userAddressNetService.update(userAddressDto);
	}

	@Test
	public void deleteUserAddress() {
		Long ssoid = 15452L;
		Long userAddressId = 108284172040290304L;
		userAddressNetService.deleteById(ssoid, userAddressId);
	}

	@Test
	public void defaultUserAddress() {
		Long ssoid = 15452L;
		ResultT<UserAddressDto> result = userAddressNetService.getDefault(ssoid);
		System.out.println(result.getData());
	}
}
