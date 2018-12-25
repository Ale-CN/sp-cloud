package com.ale.rabbitmq.direct;

import com.ale.rabbitmq.DirectConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = DirectConfig.QUEUE_NAME2)
public class DirectReceiver2 {
    @RabbitHandler
    public void receive(String msg) {
        System.out.println("DirectReceiver2 :" + msg);
    }

}
