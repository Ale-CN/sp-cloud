package com.ale.namequeue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    private static final String QUEUE_NAME = "queue_test";

    public static void main(String[] args) {
        receive();
    }

    public static void receive() {
        ConnectionFactory factory;
        Connection connection;
        Channel channel;

        factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
