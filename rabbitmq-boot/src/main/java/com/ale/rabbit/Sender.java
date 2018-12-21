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
        String msg = "hello !";
        System.out.println("Sender send:[" + msg + "]");
        template.convertAndSend("a_queue", msg);
    }

    /**
     * 发送消息exchange
     */
    public void send2() {
        String msg = "hello !";
        System.out.println("Sender send:[" + msg + "]");
        template.convertAndSend("topicExchange");
    }


}
