package com.ale.rabbitmq.topic;

import com.ale.rabbitmq.TopicConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSenderAll {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void topicSenderErr() {
        String msg = "[all]";
        System.out.println("TopicSenderAll :" + msg);
        rabbitTemplate.convertAndSend("topicExchange", TopicConfig.TOPIC_ALL, msg);
    }
}
