package net.realme.mall.user.beantool;


import net.realme.mall.user.domain.SubscribeEmailDto;
import net.realme.mall.user.model.SubscribeEmail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscribeEmailConvert {

    SubscribeEmailDto toSubscribeEmailDto(SubscribeEmail subscribeEmail);

    List<SubscribeEmailDto> toSubscribeEmailDtoList(List<SubscribeEmail> subscribeEmailList);

    SubscribeEmail toSubscribeEmail(SubscribeEmailDto subscribeEmailDto);
}
