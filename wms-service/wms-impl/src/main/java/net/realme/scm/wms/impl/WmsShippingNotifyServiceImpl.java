package net.realme.scm.wms.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.realme.framework.util.dto.ResultList;
import net.realme.scm.wms.beantool.WmsShippingNotifyConverter;
import net.realme.scm.wms.dao.WmsShippingNotifyMapper;
import net.realme.scm.wms.domain.WmsShippingNotifyDto;
import net.realme.scm.wms.facade.WmsShippingNotifyService;
import net.realme.scm.wms.model.WmsShippingNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WmsShippingNotifyServiceImpl implements WmsShippingNotifyService {
    @Autowired
    WmsShippingNotifyMapper wmsShippingNotifyMapper;
    @Autowired
    WmsShippingNotifyConverter wmsShippingNotifyConverter;

    @Override
    public Integer insert(WmsShippingNotifyDto wmsShippingNotifyDto) {
        WmsShippingNotify wmsShippingNotify = wmsShippingNotifyConverter.fromWmsShippingNotifyDto(wmsShippingNotifyDto);
        Integer i = wmsShippingNotifyMapper.insert(wmsShippingNotify);
        return i;
    }

    @Override
    public Integer batchInsert(List<WmsShippingNotifyDto> wmsShippingNotifyDtos) {
        List<WmsShippingNotify> wmsShippingNotifies = wmsShippingNotifyConverter.fromWmsShippingNotifyDtoList(wmsShippingNotifyDtos);
        Integer i = wmsShippingNotifyMapper.batchInsert(wmsShippingNotifies);
        return i;
    }

    @Override
    public ResultList<WmsShippingNotifyDto> get(int page, int limit) {
        PageHelper.startPage(page, limit, true);
        List<WmsShippingNotify> wmsShippingNotifies = this.wmsShippingNotifyMapper.selectAll();
        PageInfo<WmsShippingNotify> pageInfo = new PageInfo(wmsShippingNotifies);
        List<WmsShippingNotifyDto> wmsShippingNotifyDtos = this.wmsShippingNotifyConverter.toWmsShippingNotifyDtoList(wmsShippingNotifies);
        ResultList result = new ResultList();
        result.setPageNum(page);
        result.setPageSize(limit);
        result.setRecordTotal(pageInfo.getTotal());
        result.setRecords(wmsShippingNotifyDtos);
        return result;
    }

    @Override
    public WmsShippingNotifyDto queryWayBilByOrderId(String skuId) {
        WmsShippingNotify wmsShippingNotify = wmsShippingNotifyMapper.queryWayBilByOrderId(skuId);
        return this.wmsShippingNotifyConverter.toWmsShippingNotifyDto(wmsShippingNotify);
    }
}
