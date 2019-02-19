package net.realme.mall.user.facade;

import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.user.domain.UserAddressDto;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.user.facade
 *
 * @author 91000044
 * @date 2018/8/28 20:28
 */
public interface UserAddressService {

	ResultT<String> add(UserAddressDto userAddressDto);

	ResultT<UserAddressDto> getDefault(long ssoid);

	ResultT<UserAddressDto> getById(long ssoid, long userAddressId);

	ResultT<ResultList<UserAddressDto>> listAll(long ssoid);

	ResultT<Integer> update(UserAddressDto userAddressDto);

	ResultT<Integer> deleteById(long ssoid, long userAddressId);

	ResultT<Integer> setDefault(UserAddressDto userAddressDto);
}
