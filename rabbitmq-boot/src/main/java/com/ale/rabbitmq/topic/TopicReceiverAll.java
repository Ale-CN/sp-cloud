package com.ale.rabbitmq.topic;

import com.ale.rabbitmq.TopicConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

//@Component
public class TopicReceiverAll {
    @RabbitListener(queues = TopicConfig.QUEUE_NAME1)
    public void receiverInfo(String msg) {
        System.out.println("-->TopicReceiverAll--receiverInfo() : " + msg);
    }


    @RabbitListener(queues = TopicConfig.QUEUE_NAME2)
    public void receiverAll(String msg) {
        System.out.println("-->TopicReceiverAll--receiverAll() : " + msg);
    }
}


