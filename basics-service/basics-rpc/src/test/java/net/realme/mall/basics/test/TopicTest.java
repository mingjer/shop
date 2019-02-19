package net.realme.mall.basics.test;

import net.realme.framework.util.dto.ResultList;
import net.realme.mall.basics.Application;
import net.realme.mall.basics.dto.TopicDto;
import net.realme.mall.basics.facade.TopicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by 91000156 on 2018/9/14 11:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TopicTest {

    @Autowired
    private TopicService topicService;

    @Test
    public void testCreate() {
        TopicDto topicDto = new TopicDto();
        topicDto.setTopic("test");
        topicDto.setDescription("desc");
        topicDto.setSubscribeType(new Byte("1"));
        topicDto.setTopicArn("1323444");
        topicDto.setCreateTime(System.currentTimeMillis());
        topicService.insertTopic(topicDto);
    }

    @Test
    public void testGet() {
        TopicDto topicDto = topicService.getTopicByName("test").getData();
        System.out.println(topicDto);
    }

    @Test
    public void testGetList() {
        ResultList<TopicDto> topicDtoList = topicService.getTopicList(1,20).getData();
        System.out.println(topicDtoList);
    }
}
