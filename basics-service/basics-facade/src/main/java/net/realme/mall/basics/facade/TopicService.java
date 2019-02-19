package net.realme.mall.basics.facade;

import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.dto.TopicDto;

/**
 * Created by 91000156 on 2018/9/13 20:34
 */
public interface TopicService {

    ResultT<Integer> insertTopic(TopicDto topicDto);

    ResultT<TopicDto> getTopicByName(String topicName);

    ResultT<ResultList<TopicDto>> getTopicList(int page , int limit);

    ResultT<Integer> updateTopic(TopicDto topicDto);
}
