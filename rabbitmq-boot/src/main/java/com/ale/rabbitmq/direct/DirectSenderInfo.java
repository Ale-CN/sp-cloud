package com.ale.rabbitmq.direct;

import com.ale.rabbitmq.DirectConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectSenderInfo {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void directSend() {
        String msg = "[info]";
        System.out.println("directSendInfo :" + msg);
        rabbitTemplate.convertAndSend(DirectConfig.DIRECT_EXCHANGE, DirectConfig.ROUTE_KEY_INFO, msg);
    }

}
