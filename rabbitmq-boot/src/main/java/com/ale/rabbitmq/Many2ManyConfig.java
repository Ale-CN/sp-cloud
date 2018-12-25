package com.ale.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Many2ManyConfig {

    public static final String QUEUE_NAME1 = "queue_o2m1";

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE_NAME1);
    }

}
