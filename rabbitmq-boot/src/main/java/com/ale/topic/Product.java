package com.ale.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 主题路由器Topic Exchange(类型：BuiltinExchangeType.TOPIC) 模糊匹配。
 * 1.对消息中的routing key和队列中的binding key模糊匹配，将消息投递到该队列中。
 * BuiltinExchangeType.TOPIC规则：对key进行模式匹配后进行投递，符号”#”匹配一个或多个词，符号”*”匹配正好一个词。例如”abc.#”匹配”abc.def.ghi”，”abc.*”只匹配”abc.def”。
 * 注：【direct路由器类似于sql语句中的精确查询；topic 路由器有点类似于sql语句中的模糊查询。】
 */
public class Product {
    public static final String EXCHANGE_NAME = "exchange-topic";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;

        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();
            // 设置exchange的类型
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            // 设置使用的exchange
//            String routingKey = "info";
//            String routingKey = "log4j.error";
            String routingKey = "error";
            String msg = "i am a msg [" + routingKey + "]";
            channel.basicPublish(EXCHANGE_NAME, routingKey, false, null, msg.getBytes());
            System.out.println("product send [" + msg + "]");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
