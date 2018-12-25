package com.ale.rabbitmq.topic;

import com.ale.rabbitmq.TopicConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = TopicConfig.QUEUE_NAME1)
public class TopicReceiverInfo {

    @RabbitHandler
    public void receiverInfo(String msg) {
        System.out.println("-->TopicReceiverInfo : " + msg);
    }
}
