package net.realme.mall.user.beantool;

import net.realme.mall.user.domain.UserAddressDto;
import net.realme.mall.user.model.UserAddress;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.user.beantool
 *
 * @author 91000044
 * @date 2018/8/28 20:39
 */
@Mapper(componentModel = "spring")
public interface UserAddressConverter {

    UserAddressDto toUserAddressDto(UserAddress userAddress);

    UserAddress fromUserAddressDto(UserAddressDto userAddressDto);

    List<UserAddressDto> toUserAddressDtoList(List<UserAddress> userAddressList);
}
