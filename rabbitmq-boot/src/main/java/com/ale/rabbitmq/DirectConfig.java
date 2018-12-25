package com.ale.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {
    public static final String QUEUE_NAME1 = "queue_direct1";
    public static final String QUEUE_NAME2 = "queue_direct2";
    public static final String DIRECT_EXCHANGE = "direct_exchange";
    public static final String ROUTE_KEY_INFO = "log4j.info";
    public static final String ROUTE_KEY_ERR = "log4j.err";


    @Bean
    public Queue directQueue1(){
        return new Queue(QUEUE_NAME1);
    }
    @Bean
    public Queue directQueue2(){
        return new Queue(QUEUE_NAME2);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Binding bindDirectExchange1(Queue directQueue1,DirectExchange directExchange){
        return BindingBuilder.bind(directQueue1).to(directExchange).with(ROUTE_KEY_INFO);
    }

    @Bean
    public Binding bindDirectExchange2(Queue directQueue2,DirectExchange directExchange){
        return BindingBuilder.bind(directQueue2).to(directExchange).with(ROUTE_KEY_ERR);
    }


}
