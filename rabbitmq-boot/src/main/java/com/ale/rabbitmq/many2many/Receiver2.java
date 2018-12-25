package com.ale.rabbitmq.many2many;

import com.ale.rabbitmq.Many2ManyConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = Many2ManyConfig.QUEUE_NAME1)
public class Receiver2 {

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("Receiver222 :" + msg + "--");
    }

}
