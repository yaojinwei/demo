package com.yaojinwei.study.broadcast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

//https://www.cnblogs.com/allenwas3/p/8882204.html
//https://blog.csdn.net/garylongqq/article/details/52014617
public class Broadcast_Recv {
    public static void main(String[] args) {
        int port = 9999; // 开启监听的端口
        DatagramSocket ds = null;
        DatagramPacket dp = null;
        byte[] buf = new byte[1024];// 存储发来的消息
        try {
            // 绑定端口
            ds = new DatagramSocket(port);
            dp = new DatagramPacket(buf, buf.length);
            System.out.println("监听广播端口打开：");
            
            while (true) {
                ds.receive(dp);
                System.out.println("收到广播消息：" + new String(buf));
            }
            
//            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}