package net.realme.mall.basics.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.beantool.TopicConverter;
import net.realme.mall.basics.dao.TopicMapper;
import net.realme.mall.basics.dto.TopicDto;
import net.realme.mall.basics.facade.TopicService;
import net.realme.mall.basics.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by 91000156 on 2018/9/14 11:21
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private TopicConverter topicConverter;

    @Override
    public ResultT<Integer> insertTopic(TopicDto topicDto) {
        Topic topic = topicConverter.fromTopicDto(topicDto);
        if (topicMapper.insertSelective(topic) > 0) {
            return ResultT.success(1);
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<TopicDto> getTopicByName(String topicName) {
        TopicDto topicDto = new TopicDto();
        Example example = new Example(Topic.class);
        // 设置查询条件
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("topic", topicName);
        criteria.andEqualTo("status", 0);
        // 按照TopicName和状态查询一条Topic信息
        Topic topic = topicMapper.selectOneByExample(example);
        if (topic != null) {
            return ResultT.success(topicConverter.toTopicDto(topic));
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<ResultList<TopicDto>> getTopicList(int page, int limit) {
        PageHelper.startPage(page, limit, true);
        // 查询状态为未删除的信息
        Example example = new Example(Topic.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 0);
        List<Topic> records = topicMapper.selectByExample(example);
        PageInfo<Topic> pageInfo = new PageInfo<>(records);
        ResultList<TopicDto> result = new ResultList<>();
        if (pageInfo.getTotal() > 0) {
            List<TopicDto> dtoRecords = topicConverter.toTopicDtoList(pageInfo.getList());
            result.setRecords(dtoRecords);
        }
        result.setPageNum(page);
        result.setPageSize(limit);
        result.setRecordTotal(pageInfo.getTotal());
        return ResultT.success(result);
    }

    @Override
    public ResultT<Integer> updateTopic(TopicDto topicDto) {
        Topic topic = topicConverter.fromTopicDto(topicDto);
        if (topicMapper.updateByPrimaryKeySelective(topic) > 0) {
            return ResultT.success(1);
        }
        return ResultT.fail();
    }
}
