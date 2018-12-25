package com.ale.rabbitmq.fanout;

import com.ale.rabbitmq.FanoutConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = FanoutConfig.QUEUE_FANOUT2)
public class FanoutReceiver2 {

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("Receiver2 " + msg);
    }
}
