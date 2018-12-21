package com.ale.routing;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer2 {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;

        try {
            // 1.创建连接和通道
            connection = factory.newConnection();
            Channel channel = connection.createChannel();
            // 2.设置exchange的类型
            channel.exchangeDeclare(Product.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            // 3.创建随机队列
            String qname = channel.queueDeclare().getQueue();
            // 4.建立exchange和队列的绑定关系
            String[] routingKeys = {"info", "debug", "err"};
            for (String key : routingKeys) {
                channel.queueBind(qname, Product.EXCHANGE_NAME, key);
                System.out.println(" **** Consumer2 keep alive ,waiting for " + key);
            }
            // 5.通过回调生成消费者并进行监听
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    // 获取消息内容然后处理
                    String msg = new String(body, "utf-8");
                    System.out.println("Consumer2 receive [" + msg + "]");
                }
            };
            // 6.消费消息
            channel.basicConsume(qname, true, consumer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
