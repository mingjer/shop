package net.realme.mall.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.realme.framework.util.dto.ResultList;

import net.realme.framework.util.dto.ResultT;
import net.realme.mall.user.beantool.ReserveInfoConvert;
import net.realme.mall.user.dao.ReserveInfoMapper;
import net.realme.mall.user.domain.ReserveInfoDto;
import net.realme.mall.user.facade.ReserveService;
import net.realme.mall.user.model.ReserveInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/** 
 * @Author: 91000156
 * @Date: 2018/8/24 11:12
 * @Description:
 */  
@Service
@Transactional(value = "userTransactionManager", rollbackFor = Exception.class)
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    private ReserveInfoMapper reserveInfoMapper;

    @Autowired
    private ReserveInfoConvert reserveInfoConvert;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ResultT<Long> addReserveInfo(ReserveInfoDto reserveInfoDto) {
        ReserveInfo reserveInfo = reserveInfoConvert.toReserveInfo(reserveInfoDto);
        Example example = new Example(ReserveInfo.class);
        Example.Criteria criteria = example.createCriteria();
        // 一个手机号只能预约一个特定的sku商品
        criteria.andEqualTo("skuId",reserveInfo.getSkuId());
        criteria.andEqualTo("phone",reserveInfo.getPhone());
        criteria.andEqualTo("status",new Byte("0"));
        List<ReserveInfo> reserveInfoList = reserveInfoMapper.selectByExample(example);
        // 一个手机号只能对统一个sku_id预约一次
        if (reserveInfoList != null && !reserveInfoList.isEmpty()) {
            logger.info("phone: {} already reserved!", reserveInfo.getPhone());
            return ResultT.fail();
        }
        if(reserveInfoMapper.insertSelective(reserveInfo) >0){
            return ResultT.success(reserveInfo.getId());
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<Integer> cancelReserveInfo(int userId, int skuId) {
        return ResultT.fail();
    }

    @Override
    public ResultT<ReserveInfoDto> queryReserveInfo(int userId, int skuId) {
        return null;
    }

    @Override
    public ResultT<ResultList<ReserveInfoDto>> getReserveInfos(int skuId, int page, int limit) {
        ResultList<ReserveInfoDto> resultList = new ResultList<>();
        // 分页大小
        PageHelper.startPage(page, limit);
        Example example = new Example(ReserveInfo.class);
        // 设置查询条件
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("skuId", skuId);
        criteria.andEqualTo("status", new Byte("0"));
        // 按条件进行查询
        List<ReserveInfo> reserveInfoList = reserveInfoMapper.selectByExample(example);
        PageInfo<ReserveInfo> pageInfo = new PageInfo<>(reserveInfoList);
        if (pageInfo.getTotal() > 0) {
            List<ReserveInfoDto> reserveInfoDtoList = reserveInfoConvert.toReserveInfoDtoList(pageInfo.getList());
            resultList.setRecords(reserveInfoDtoList);
        }
        resultList.setPageNum(page);
        resultList.setPageSize(limit);
        resultList.setRecordTotal(pageInfo.getTotal());
        return ResultT.success(resultList);
    }
}
