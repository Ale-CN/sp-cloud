package com.ale.rabbitmq.topic;

import com.ale.rabbitmq.TopicConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSenderInfo {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void topicSenderInfo() {
        String msg = "[info]";
        System.out.println("topicSenderInfo :" + msg);
        rabbitTemplate.convertAndSend("topicExchange", TopicConfig.TOPIC_INFO, msg);
    }
}
