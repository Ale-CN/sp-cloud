package com.ale.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    public static final String QUEUE_FANOUT1 = "queue_fanout1";
    public static final String QUEUE_FANOUT2 = "queue_fanout2";
    public static final String FANOUT_EXCHANGE = "fanout_exchange";

    @Bean
    public Queue fanout1() {
        return new Queue(QUEUE_FANOUT1);
    }

    @Bean
    public Queue fanout2() {
        return new Queue(QUEUE_FANOUT2);
    }

    @Bean
    public FanoutExchange fanoutExchange1() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding bind1(Queue fanout1, FanoutExchange fanoutExchange1) {
        return BindingBuilder.bind(fanout1).to(fanoutExchange1);
    }

    @Bean
    public Binding bind2(Queue fanout2, FanoutExchange fanoutExchange1) {
        return BindingBuilder.bind(fanout2).to(fanoutExchange1);
    }

}
