package com.ale.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher {

    //队列名称
    public static final String QUEUE_NAME = "queue_work";
    //队列是否需要持久化
    public static final boolean DURABLE = true;

    //需要发送的消息列表
    public static final String[] msgs = {"task 1", "sleep", "task 3", "task 4", "task 5", "task 6"};

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;
        Channel channel = null;

        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, DURABLE, false, false, null);
            for (int i = 0; i < msgs.length; i++) {
                channel.basicPublish ("", QUEUE_NAME, null, msgs[i].getBytes());
                System.out.println("** new task ****:" + msgs[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
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
