package net.realme.scm.wms.facade;

import net.realme.framework.util.dto.ResultList;
import net.realme.scm.wms.domain.WmsShippingNotifyDto;

import java.util.List;

public interface WmsShippingNotifyService {
    Integer insert(WmsShippingNotifyDto wmsShippingNotifyDto) ;
    Integer batchInsert(List<WmsShippingNotifyDto> wmsShippingNotifyDtos) ;

    ResultList<WmsShippingNotifyDto> get(int page, int limit);

    WmsShippingNotifyDto queryWayBilByOrderId(String skuId);
}
