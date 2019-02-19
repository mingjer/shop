package net.realme.scm.wms.dao;

import net.realme.scm.wms.model.WmsShippingNotify;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface WmsShippingNotifyMapper extends Mapper<WmsShippingNotify> {
    Integer batchInsert(List<WmsShippingNotify> wmsShippingNotifies);

    WmsShippingNotify queryWayBilByOrderId(String skuId);
}