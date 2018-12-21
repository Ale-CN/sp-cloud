package com.ale.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue_hello")
public class Receiver {
    @RabbitHandler
    public void receiver(String msg){
        System.out.println("Receiver : [" + msg + "]");
    }
}
