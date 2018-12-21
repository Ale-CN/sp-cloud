package com.ale.exchange;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 订阅/发布((类型：BuiltinExchangeType.FANOUT))
 * 1.通过exchange广播的方式，将消息发送给所有连接到该exchange的消费者。
 * BuiltinExchangeType.FANOUT规则：不需要routingKey，它采取广播模式，一个消息进来时，投递到与该交换机绑定的所有队列。
 */
public class Product {
    //exchange名字
    public static String EXCHANGE_NAME = "exchange1";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Channel channel = null;
        try {
            channel = factory.newConnection().createChannel();
            // 2.为通道声明exchange和exchange的类型
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

            String msg = " hello rabbitmq, this is publish/subscribe mode";
            // 3.发送消息到指定的exchange,队列指定为空,由exchange根据情况判断需要发送到哪些队列
            channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
            System.out.println("product send a msg: " + msg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭连接
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
