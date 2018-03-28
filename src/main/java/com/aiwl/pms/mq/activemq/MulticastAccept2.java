package com.aiwl.pms.mq.activemq;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastAccept2 {
	public static void main(String[] args) throws Throwable {
        // �����鲥�׽��֣����������
        MulticastSocket multicastSocket = new MulticastSocket(19999);
        // ע�⣬�鲥��ַ�Ͷ˿ڱ���ͷ����ߵ�һֱ�����ܼ�����ȷ����
        InetAddress ad = InetAddress.getByName("239.0.0.5");
        multicastSocket.joinGroup(ad);

        // ׼�����տ��ܵ��鲥�ź�
        byte[] datas = new byte[2048];
        DatagramPacket data = new DatagramPacket(datas, 2048 ,ad , 19999);
        Thread thread = Thread.currentThread();

        // ��ʼ�����鲥��Ϣ������ӡ����
        System.out.println(".....��ʼ�����鲥��Ϣ.....");
        while(!thread.isInterrupted()) {
            multicastSocket.receive(data);
            int leng = data.getLength();
            System.out.println(new String(data.getData() , 0 , leng , "UTF-8"));
        }

        multicastSocket.close();
    }
}
