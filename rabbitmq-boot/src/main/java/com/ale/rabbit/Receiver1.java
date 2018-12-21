package com.ale.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "a_queue")
public class Receiver1 {
    @RabbitHandler
    public void receiver(String msg){
        System.out.println("Receiver1 : [" + msg + "]");
    }

}
