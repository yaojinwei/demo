package com.yaojinwei.study.broadcast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Broadcast_Send {
    public static void main(String[] args) {
        // 本机ip：10.3.80.248， 子网掩码：255.255.255.0，推出广播地址：10.3.80.255
        String host = "10.3.80.255"; // 广播地址
        int port = 9999; // 广播的目的端口
        String message = null; // 用于发送的字符串
        try {
            while (true) {
                message = "hello world " + System.currentTimeMillis();
                InetAddress addr = InetAddress.getByName(host);
                DatagramSocket ds = new DatagramSocket();
                DatagramPacket dp = new DatagramPacket(message.getBytes(),
                        message.length(), addr, port);
                ds.send(dp);
                Thread.sleep(2000);
//                ds.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}