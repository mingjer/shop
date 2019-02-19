package net.realme.scm.wms.beantool;

import net.realme.scm.wms.domain.WmsShippingNotifyDto;
import net.realme.scm.wms.model.WmsShippingNotify;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class WmsShippingNotifyConverter {
    public abstract WmsShippingNotify fromWmsShippingNotifyDto(WmsShippingNotifyDto wmsShippingNotifyDto);
    public abstract List<WmsShippingNotify> fromWmsShippingNotifyDtoList(List<WmsShippingNotifyDto> wmsShippingNotifyDtos);
    public abstract List<WmsShippingNotifyDto> toWmsShippingNotifyDtoList(List<WmsShippingNotify> wmsShippingNotifies);
    public abstract WmsShippingNotifyDto toWmsShippingNotifyDto(WmsShippingNotify wmsShippingNotify);
}
