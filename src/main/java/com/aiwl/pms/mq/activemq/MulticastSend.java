package com.aiwl.pms.mq.activemq;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;

/***
 * �鲥ģ�ⷢ�Ͷ�
 * @author 1
 *
 */
public class MulticastSend {
	public static void main(String[] args) throws Throwable {
        // �鲥��ַ
        InetAddress group = InetAddress.getByName("239.0.0.5");
        // �鲥�˿ڣ�ͬʱҲ��UDP ���ݱ��ķ��Ͷ˿�
        int port = 19999;
        MulticastSocket mss = null;  

        // ����һ�����ڷ���/���յ�MulticastSocket�鲥�׽��ֶ���
        mss = new MulticastSocket(port);
        // ����Ҫ���͵��鲥��Ϣ��UDP���ݱ�
        // Я�����������ݣ��������activeMQ����ڵ������ṩNetwork Connectors��TCP/IP��ַ�Ͷ˿ڵ���Ϣ
        String message = "����һ�����activeMQ����ڵ㣨�ڵ���:yyyyyyy�����ҵĿ���tcp��ϢΪ��XXXXXXXXXX : ";  
        byte[] buffer2 = message.getBytes(); 
        DatagramPacket dp = new DatagramPacket(buffer2, buffer2.length, group, port);
        // ʹ���鲥�׽���joinGroup(),������뵽һ���鲥
        mss.joinGroup(group);

        // ��ʼ����һ������������뵽224.0.0.5�鲥��ַ������ActiveMQ����ڵ���й㲥
        Thread thread = Thread.currentThread();
        while (!thread.isInterrupted()) {
            // ʹ���鲥�׽��ֵ�send()���������鲥���ݰ�����������У������鲥���ݰ�
            mss.send(dp);
            System.out.println(new Date() + "�����鲥��" + message);
            synchronized (MulticastSend.class) {
                MulticastSend.class.wait(5000);
            }
        }

        mss.close();
    }
}
