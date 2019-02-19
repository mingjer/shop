package net.realme.mall.user.facade;

import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.user.domain.SubscribeEmailDto;


import java.util.List;

/** 
 * @Author: 91000156
 * @Date: 2018/8/24 11:11
 * @Description:
 */  
public interface SubscribeService {

    /**
     * 添加邮件订阅信息
     *
     * @param subscribeEmailDto
     * @return
     */
    ResultT<Long> addSubscribeInfo(SubscribeEmailDto subscribeEmailDto);

    /**
     * 取消邮件订阅
     *
     * @param subscribeEmailDto
     * @return
     */
    ResultT<Integer> cancelSubscribeInfo(SubscribeEmailDto subscribeEmailDto);

    /**
     * 分页获取已订阅的邮箱信息
     *
     * @param page
     * @param limit
     * @return
     */
    ResultT<ResultList<SubscribeEmailDto>> getSubscribeInfoList(int page, int limit);

    /**
     * 获取当前页订阅的数量
     *
     * @return
     */
    ResultT<Integer> getSubscribeInfoCount();

    /**
     * 更新订阅信息
     *
     * @param subscribeEmailDto
     * @return
     */
    ResultT<Long> updateSubscribeInfo(SubscribeEmailDto subscribeEmailDto);

    /**
     * 批量发送邮件
     *
     * @param title
     * @param content
     * @param subscribeEmailDtoList
     */
    void sendMailByBatch(String title, String content, List<SubscribeEmailDto> subscribeEmailDtoList);
}
