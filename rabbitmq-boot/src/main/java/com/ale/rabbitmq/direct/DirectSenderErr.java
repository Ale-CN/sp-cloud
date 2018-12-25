package com.ale.rabbitmq.direct;

import com.ale.rabbitmq.DirectConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectSenderErr {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void directSend() {
        String msg = "[err]";
        System.out.println("DirectSenderErr :" + msg);
        rabbitTemplate.convertAndSend(DirectConfig.DIRECT_EXCHANGE, DirectConfig.ROUTE_KEY_ERR, msg);
    }

}
