package com.ale.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue aQueue() {
        return new Queue("a_queue");
    }

    @Bean
    public Queue bQueue(){
        return new Queue("b_queue");
    }

    @Bean
    public TopicExchange topicExchange(){
        return  new TopicExchange("topicExchange");
    }

    @Bean
    public Binding bindingExchange_error(Queue aQueue,TopicExchange topicExchange){
        return BindingBuilder.bind(aQueue).to(topicExchange).with("log4j.error");
    }

    @Bean
    public Binding bindingExchange_info(Queue aQueue,TopicExchange topicExchange){
        return BindingBuilder.bind(aQueue).to(topicExchange).with("log4j.info");
    }

    @Bean
    public Binding bindingExchange_all(Queue bQueue,TopicExchange topicExchange){
        return BindingBuilder.bind(bQueue).to(topicExchange).with("#");
    }



}
