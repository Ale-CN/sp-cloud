package com.ale.rabbitmq.fanout;

import com.ale.rabbitmq.FanoutConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = FanoutConfig.QUEUE_FANOUT1)
public class FanoutReceiver1 {

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("Receiver1 " + msg);
    }
}
