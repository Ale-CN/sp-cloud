package com.ale.rabbitmq.topic;

import com.ale.rabbitmq.TopicConfig;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AnnotaionTopicReceiver {

    @RabbitListener(bindings = @QueueBinding(exchange = @Exchange(value = "topicExchange"), value = @Queue(value = TopicConfig.QUEUE_NAME1), key = TopicConfig.TOPIC_INFO))
    public void receive(String msg) {
        System.out.println(" AnnotaionTopicReceiver " + msg);
    }
}
