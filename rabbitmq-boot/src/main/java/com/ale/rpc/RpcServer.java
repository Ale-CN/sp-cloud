package com.ale.rpc;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RpcServer {
    Connection connection = null;
    Channel channel = null;
    //RPC队列名
    public static final String QUEUE_NAME = "rpc_queue";

    public RpcServer() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public String handle() throws IOException {
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        System.out.println("RpcServer waiting for client request...");
        // 3.每次只接收一个消息（任务）
        channel.basicQos(1);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                AMQP.BasicProperties prop = new AMQP.BasicProperties().builder().correlationId(properties.getCorrelationId()).build();
                String resp = new String(body, "utf-8");
                System.out.println("RpcServer receive msg[" + resp + "]");
                // 处理RpcClient消息
                resp = dealMsg(resp);
                // 返回结果
                channel.basicPublish("", properties.getReplyTo(), prop, resp.getBytes());
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        // 5.消费消息（处理任务）
        channel.basicConsume(QUEUE_NAME, false, consumer);
        return null;
    }

    public String dealMsg(String msg) {
        return "【" + msg + "】";
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        RpcServer server = new RpcServer();
        server.handle();
    }
}
