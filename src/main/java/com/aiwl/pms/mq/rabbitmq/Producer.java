package com.aiwl.pms.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * ��Ϣ������
 */
public class Producer {
    public final static String QUEUE_NAME="rabbitMQ.test";

    public static void main(String[] args) throws Exception {
        //�������ӹ���
        ConnectionFactory factory = new ConnectionFactory();
        //����RabbitMQ�����Ϣ
        factory.setHost("192.168.2.78");
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setPort(5672);
        factory.setConnectionTimeout(10000); 
        //����һ���µ�����
        Connection connection = factory.newConnection();
        //����һ��ͨ��
        Channel channel = connection.createChannel();
        //  ����һ������        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello RabbitMQ";
        //������Ϣ��������
//        for(int i=0;i<100;i++){
//        	Thread.sleep(1000);
        	channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
//        }
        System.out.println("Producer Send +'" + message + "'");
        //�ر�ͨ��������
        channel.close();
        connection.close();
    }
}