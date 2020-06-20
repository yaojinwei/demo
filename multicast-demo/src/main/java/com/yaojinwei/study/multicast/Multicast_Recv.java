package com.yaojinwei.study.multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
//https://www.cnblogs.com/allenwas3/p/8882204.html
//-Djava.net.preferIPv4Stack=true
public class Multicast_Recv {
    public static void main(String[] args) throws Exception {
        InetAddress group = InetAddress.getByName("239.1.1.1");
        int port = 1234;
        MulticastSocket socket = new MulticastSocket(port);
        socket.joinGroup(group);

        while (true) {
            byte buff[] = new byte[8192];
            DatagramPacket packet = null;
            packet = new DatagramPacket(buff, buff.length, group, port);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println("receive:" + message);
        }
    }
}