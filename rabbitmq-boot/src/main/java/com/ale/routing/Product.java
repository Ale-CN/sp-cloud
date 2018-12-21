package com.ale.routing;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 路由Exchange(类型：BuiltinExchangeType.DIRECT)精确匹配。
 * 1.当消息中的routing key和队列中的binding key完全匹配时，才进行会将消息投递到该队列中。
 * BuiltinExchangeType.DIRECT规则：完全根据key进行投递的，例如，绑定时设置了routing key为”abc”，那么客户端提交的消息，只有设置了key为”abc”的才会投递到队列。

 */
public class Product {
    public static final String EXCHANGE_NAME = "exchange-direct";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;

        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();
            // 设置exchange的类型
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            // 设置使用的exchange
            String routingKey = "err";
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
