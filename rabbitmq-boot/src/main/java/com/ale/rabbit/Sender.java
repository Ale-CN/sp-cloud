package com.ale.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    @Autowired
    RabbitTemplate template;

    /**
     * 发送消息
     */
    public void send() {
        String msg = "Sender : 'i am Sender!!'";
        System.out.println(msg);
        template.convertAndSend("queue_hello", msg);
    }
}
