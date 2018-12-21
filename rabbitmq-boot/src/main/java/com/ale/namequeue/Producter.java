package com.ale.namequeue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producter {
    private static final String QUEUE_NAME = "queue_test";

    public static void main(String[] args) {
        send("a test msg !");
    }

    public static void send(String msg) {
        ConnectionFactory factory ;
        Connection connection = null;
        Channel channel = null;

        factory = new ConnectionFactory();// 初始化工厂
        factory.setHost("127.0.0.1");
        factory.setConnectionTimeout(6000);

        try {
            connection = factory.newConnection();// 创建连接
            channel = connection.createChannel();// 开启管道
            // 创建消息队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            System.out.println("Producter send MSG [" + msg + "]");

        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace();
        } catch (TimeoutException e) {
        }finally {
            //4.关闭连接
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }

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
