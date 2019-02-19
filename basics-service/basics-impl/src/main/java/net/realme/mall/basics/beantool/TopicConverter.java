package net.realme.mall.basics.beantool;

import net.realme.mall.basics.dto.TopicDto;
import net.realme.mall.basics.model.Topic;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by 91000156 on 2018/9/14 11:27
 */
@Mapper(componentModel = "spring")
public interface TopicConverter {

    Topic fromTopicDto(TopicDto topicDto);

    TopicDto toTopicDto(Topic topic);

    List<TopicDto> toTopicDtoList(List<Topic> topicList);
}
