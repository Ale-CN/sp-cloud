package com.ale.rabbitmq;

import com.ale.rabbitmq.topic.TopicSenderAll;
import com.ale.rabbitmq.topic.TopicSenderInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TopicTest {
    @Autowired
    TopicSenderInfo senderInfo;
    @Autowired
    TopicSenderAll senderErr;

    @Test
    public void test() {
        senderInfo.topicSenderInfo();
        senderInfo.topicSenderInfo();
        senderInfo.topicSenderInfo();

        senderErr.topicSenderErr();
    }

}
