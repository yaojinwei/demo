package com.yaojinwei.study.multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Multicast_Send {
    public static void main(String[] args) {
        String[] message = { "hello", "hi" };
        int port = 1234; // 端口
        InetAddress group = null; // 地址
        MulticastSocket multicastSocket = null; // 组播套接字

        try {
            group = InetAddress.getByName("239.1.1.1"); // 设置多播地址
            multicastSocket = new MulticastSocket(port); 
            // 设置数据包的ttl，值从0到255，应该是和ip包的ttl字段对应上了
            // 之前设置为1，在无限局域网中，数据包无法到达其他主机，所以这里设为2
            // 如果设为0，则只能发送给本机
            multicastSocket.setTimeToLive(2); 
            multicastSocket.joinGroup(group);
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                DatagramPacket packet = null;
                for (String msg : message) // 循环发送每条广播信息
                {
                    msg = msg + "," + System.currentTimeMillis();
                    byte buff[] = msg.getBytes();
                    packet = new DatagramPacket(buff, buff.length, group, port);
                    multicastSocket.send(packet);
                    System.out.println("send:" + msg);
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
                System.out.println("Error:" + e);
            }
        }
        
    }
}