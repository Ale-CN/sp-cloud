package com.ale.rabbitmq.fanout;

import com.ale.rabbitmq.FanoutConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender1 {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void fanoutSend(){
        String msg = "[fanout]";
        System.out.println("FanoutSender1 " + msg);
        rabbitTemplate.convertAndSend(FanoutConfig.FANOUT_EXCHANGE,"",msg);
    }

}
