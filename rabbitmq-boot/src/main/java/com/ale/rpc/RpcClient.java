package com.ale.rpc;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

/**
 * 远程过程调用（Remote Procedure Call）
 * 1.客户端发送启动后，会创建独特的回调队列。对于一个请求发送配置了两个属性的消息：一个是回调队列（图中的replay_to），一个是correlationId。 这个请求会发送到rpc_queue队列，然后到达服务端处理。
 * 2.服务端等待rpc_queue队列的请求。当有请求到来时，它就会开始干活并将结果通过发送消息来返回，该返回消息发送到replyTo指定的队列。
 * 3.客户端将等待回调队列返回数据。当返回的消息到达时，它将检查correlationId属性。如果该属性值和请求匹配，就将响应返回给程序。
 */
public class RpcClient {
    Connection connection = null;
    Channel channel = null;
    String queueName = "";

    public RpcClient() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();
        queueName = channel.queueDeclare().getQueue();
    }

    public String call(String msg) throws IOException, InterruptedException {
        Long startTime = System.currentTimeMillis();
        final String uuid = UUID.randomUUID().toString();
        //后续，服务端根据"replyTo"来指定将返回信息写入到哪个队列
        //后续，服务端根据关联标识"correlationId"来指定返回的响应是哪个请求的
        AMQP.BasicProperties prop = new AMQP.BasicProperties().builder().replyTo(queueName).correlationId(uuid).build();

        channel.basicPublish("", RpcServer.QUEUE_NAME, prop, msg.getBytes());
        System.out.println("RpcClient send [" + msg + "]");

        // 接收返回信息
        final BlockingQueue<String> blockQueue = new ArrayBlockingQueue<>(1);
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 返回信息的correlationId是否正确
                if (uuid.equals(properties.getCorrelationId())) {
                    String result = new String(body, "utf-8");
                    System.out.println("RpcClient receive response[" + result + "] ");
                    blockQueue.offer(result);
                    System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + "ms");
                }
            }
        });
        return blockQueue.take();
    }

    public void close() throws IOException {
        connection.close();
    }

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        RpcClient client = new RpcClient();
        client.call("client");
        client.close();
    }
}
