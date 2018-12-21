package com.ale.workqueue;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工作队列
 * 1.默认使用轮询方式分发任务给接收者处理，分发同等任务给每个消费者。
 * 2.消息确认autoAck=true,服务器在分发后将直接删除消息(如果worker在处理过程中挂掉，分配给该worker的所有消息将丢失)
 * 3.队列持久化durable=false,服务器在分发任务后，worker尚在处理时，服务器挂掉了，所有消息将会丢失。
 * 4.公平分发，basicQos=1，每个任务消耗的时间是不一样的，公平分发可以让服务器只给空闲的worker分发任务。
 */
public class Worker {
    //队列是否需要持久化
//    public static final boolean DURABLE = false;
    public static final boolean DURABLE = true;// 3.队列持久化

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;


        try {
            connection = factory.newConnection();
            final Channel channel = connection.createChannel();
            channel.queueDeclare(Publisher.QUEUE_NAME, DURABLE, false, false, null);
            //3. consumer instance
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msg = new String(body, "UTF-8");
                    //deal task
                    try {
                        doWork(msg);
                    }finally {
                        channel.basicAck(envelope.getDeliveryTag(), false);// 2.消息确认
                    }
                }
            };

            //4.do consumer
//            boolean autoAck = true;// 自动应答
            boolean autoAck = false; // 2.消息确认
            // 3.每次只接收一个消息（任务）
            channel.basicQos(1);// 4.公平分发
            channel.basicConsume(Publisher.QUEUE_NAME, autoAck, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private static void doWork(String msg) {
        try {
            System.out.println("**** deal task begin :" + msg);

            //假装task比较耗时，通过sleep（）来模拟需要消耗的时间
            if ("sleep".equals(msg)) {
                Thread.sleep(1000 * 20);
            } else {
                Thread.sleep(1000);
            }

            System.out.println("**** deal task finish :" + msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
