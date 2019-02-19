package net.realme.mall.user.beantool;


import net.realme.mall.user.domain.ReserveInfoDto;
import net.realme.mall.user.model.ReserveInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReserveInfoConvert {

    ReserveInfoDto toReserveInfoDto(ReserveInfo reserveInfo);

    List<ReserveInfoDto> toReserveInfoDtoList(List<ReserveInfo> reserveInfoList);

    ReserveInfo toReserveInfo(ReserveInfoDto reserveInfoDto);
}
