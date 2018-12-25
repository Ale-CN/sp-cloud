package com.ale.rabbitmq.many2many;

import com.ale.rabbitmq.Many2ManyConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender2 {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sende() {
        String msg = "[many2many]";
        System.out.println("Sender2 :" + msg);
        rabbitTemplate.convertAndSend(Many2ManyConfig.QUEUE_NAME1, msg);
    }
}
