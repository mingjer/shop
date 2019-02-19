package net.realme.mall.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.realme.framework.cache.redis.RedisCache;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.third.facade.EmailService;
import net.realme.mall.user.beantool.SubscribeEmailConvert;
import net.realme.mall.user.common.NotificationConst;
import net.realme.mall.user.dao.SubscribeEmailMapper;
import net.realme.mall.user.domain.SubscribeEmailDto;
import net.realme.mall.user.facade.SubscribeService;
import net.realme.mall.user.model.SubscribeEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/** 
 * @Author: 91000156
 * @Date: 2018/8/24 11:13
 * @Description:
 */  
@Service
@Transactional(value = "userTransactionManager", rollbackFor = Exception.class)
public class SubscribeServiceImpl implements SubscribeService {

    @Autowired
    private SubscribeEmailMapper subscribeEmailMapper;

    @Autowired
    private SubscribeEmailConvert subscribeEmailConvert;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RedisCache redisCache;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ResultT<Long> addSubscribeInfo(SubscribeEmailDto subscribeEmailDto) {
        SubscribeEmail subscribeEmail = subscribeEmailConvert.toSubscribeEmail(subscribeEmailDto);
        Example example = new Example(SubscribeEmail.class);
        Example.Criteria criteria = example.createCriteria();
        // 查询此邮箱是否已订阅
        criteria.andEqualTo("email",subscribeEmail.getEmail());
        criteria.andEqualTo("status",new Byte("0"));
        List<SubscribeEmail> subscribeEmailList = subscribeEmailMapper.selectByExample(example);
        // 一个邮箱只能订阅一次
        if (subscribeEmailList != null && !subscribeEmailList.isEmpty()) {
            logger.info("email: {} already subscribed!", subscribeEmail.getEmail());
            return ResultT.fail();
        }
        if (subscribeEmailMapper.insertSelective(subscribeEmail) > 0) {
            logger.info("email: {} subscribed!", subscribeEmail.getEmail());
            return ResultT.success(subscribeEmail.getId());
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<Integer> cancelSubscribeInfo(SubscribeEmailDto subscribeEmailDto) {
        SubscribeEmail subscribeEmail = subscribeEmailConvert.toSubscribeEmail(subscribeEmailDto);
        Example example = new Example(SubscribeEmail.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email", subscribeEmail.getEmail());
        criteria.andEqualTo("status", 0);
        SubscribeEmail updatedSub = new SubscribeEmail();
        updatedSub.setStatus((byte) 1);
        updatedSub.setUpdateTime(System.currentTimeMillis() /1000);
        int ret = subscribeEmailMapper.updateByExampleSelective(updatedSub, example);
        if (ret > 0) {
            return ResultT.success(ret);
        }

        return ResultT.fail();
    }

    @Override
    public ResultT<ResultList<SubscribeEmailDto>> getSubscribeInfoList(int page, int limit) {
        ResultList<SubscribeEmailDto> resultList = new ResultList<>();
        // 按照create_time进行排序
        PageHelper.startPage(page, limit).setOrderBy("create_time asc");
        // 查询状态正常的记录
        Example example = new Example(SubscribeEmail.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status","0");
        List<SubscribeEmail> subscribeEmailsList = subscribeEmailMapper.selectByExample(example);
        PageInfo<SubscribeEmail> pageInfo = new PageInfo<>(subscribeEmailsList);
        if (pageInfo.getTotal() > 0) {
            List<SubscribeEmailDto> subscribeEmailDtoList = subscribeEmailConvert.toSubscribeEmailDtoList(pageInfo.getList());
            resultList.setRecords(subscribeEmailDtoList);
        }
        resultList.setPageNum(page);
        resultList.setPageSize(limit);
        resultList.setRecordTotal(pageInfo.getTotal());
        return ResultT.success(resultList);
    }

    @Override
    public ResultT<Integer> getSubscribeInfoCount() {
        int count = subscribeEmailMapper.selectCountByExample(new Example(SubscribeEmail.class));
        return  ResultT.success(count);
    }

    @Override
    public void sendMailByBatch(String title,String content,List<SubscribeEmailDto> subscribeEmailDtoList) {
        if (subscribeEmailDtoList != null && subscribeEmailDtoList.size() > 0) {
            for (int i = 0; i < subscribeEmailDtoList.size(); i++) {
                try{
                    // 执行发送邮件的任务
                    emailService.send(title, content, subscribeEmailDtoList.get(i).getEmail());
                    // 邮件发送完毕更新订阅表的状态
                    subscribeEmailDtoList.get(i).setSendTime(System.currentTimeMillis() /1000);
                    // 更新发送时间
                    updateSubscribeInfo(subscribeEmailDtoList.get(i));
                }catch (Exception e){
                    continue;
                }
            }
        }
    }

    @Override
    public ResultT<Long> updateSubscribeInfo(SubscribeEmailDto subscribeEmailDto) {
        SubscribeEmail subscribeEmail = subscribeEmailConvert.toSubscribeEmail(subscribeEmailDto);
        if (subscribeEmailMapper.updateByPrimaryKey(subscribeEmail) > 0) {
            return ResultT.success(subscribeEmail.getId());
        }
        return ResultT.fail();
    }
}
