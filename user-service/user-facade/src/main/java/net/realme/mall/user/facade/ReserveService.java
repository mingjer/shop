package net.realme.mall.user.facade;

import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.user.domain.ReserveInfoDto;


/** 
 * @Author: 91000156
 * @Date: 2018/8/24 11:11
 * @Description:
 */  
public interface ReserveService {

    /**
     * 添加预约订阅信息到数据库
     *
     * @param reserveInfoDto
     * @return
     */
    ResultT<Long> addReserveInfo(ReserveInfoDto reserveInfoDto);

    /**
     * 取消预约信息
     *
     * @param userId
     * @param skuId
     * @return
     */
    ResultT<Integer> cancelReserveInfo(int userId, int skuId);

    /**
     * 查询预约信息
     *
     * @param userId
     * @param skuId
     * @return
     */
    ResultT<ReserveInfoDto> queryReserveInfo(int userId, int skuId);

    /**
     * 分页获取预约信息
     *
     * @param skuId
     * @param page
     * @param limit
     * @return
     */
    ResultT<ResultList<ReserveInfoDto>> getReserveInfos(int skuId, int page, int limit);
}
