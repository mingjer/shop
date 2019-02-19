package net.realme.mall.user;

import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.user.domain.UserAddressDto;
import net.realme.mall.user.facade.UserAddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.user
 *
 * @author 91000044
 * @date 2018/8/28 21:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserAddressTest {

    @Autowired
    private UserAddressService userAddressService;

    @Test
    public void addAddress() {
        UserAddressDto userAddressDto = new UserAddressDto();
        userAddressDto.setSsoid(20507788L);
        userAddressDto.setSiteCode("in");
        userAddressDto.setPinCode("722102");
        userAddressDto.setProvinceName("West Bengal");
        userAddressDto.setCityName("Bankura");
        //userAddressDto.setAddress("Stark Tower 007");
        userAddressDto.setFirstName("lh");
        userAddressDto.setLastName("t");
        userAddressDto.setPhoneCallingCodes("+91");
        userAddressDto.setPhoneNumber("9116299421");
        userAddressDto.setStatus((byte) 1);
        userAddressDto.setIsDefault((byte) 1);
        userAddressDto.setCreatedAt(System.currentTimeMillis());
        ResultT<Integer> ret = userAddressService.add(userAddressDto);
        System.out.println(ret.isSuccess());
        System.out.println(ret.getData());
    }

    @Test
    public void getAddress() {
        ResultT<UserAddressDto> ret = userAddressService.getById(1);
        System.out.println(ret.isSuccess());
        System.out.println(ret.getData());

        ret = userAddressService.getDefault(20507788L);
        System.out.println(ret.isSuccess());
        System.out.println(ret.getData());
    }

    @Test
    public void getAddressList() {
        ResultT<ResultList<UserAddressDto>> ret = userAddressService.listAll(20507788L);
        System.out.println(ret.isSuccess());
        System.out.println(ret.getData());
    }
}
