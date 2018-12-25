package com.ale.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {
    public static final String QUEUE_NAME1 = "queue_topic1";
    public static final String QUEUE_NAME2 = "queue_topic2";
    public static final String TOPIC_INFO = "log4j.info";
    public static final String TOPIC_ALL = "log4j.#";

    @Bean
    public Queue topicQueue1() {
        return new Queue(QUEUE_NAME1);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(QUEUE_NAME2);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding bindTopiceExchange1(Queue topicQueue1, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with(TOPIC_INFO);
    }

    @Bean
    public Binding bindTopiceExchange2(Queue topicQueue2, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with(TOPIC_ALL);
    }

}
