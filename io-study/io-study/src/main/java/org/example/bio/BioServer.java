package org.example.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 同步阻塞IO
 * 用老师收作业来形容，相当于来多少个学生，就派多少个老师来收作业，每个老师都等着对应同学的作业写完
 *
 * 线程模型上是1+n
 * 必须有一个线程用于接受客户端链接
 * n个客户端要开启n个线程
 * 适用于连接数少且连接固定，对服务器资源要求较高
 *
 * https://blog.csdn.net/m0_56799642/article/details/129049532
 */
public class BioServer {
    private static Socket socket = null;

    public static void main(String[] args) throws IOException {
        try {
            //绑定端口
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(8080));
            while (true) {
                //等待连接  阻塞
                System.out.println("等待连接");
                socket = serverSocket.accept();
                System.out.println("连接成功");
                //连接成功后新开一个线程去处理这个连接
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        byte[] bytes = new byte[1024];
                        try {
                            System.out.println("等待读取数据");
                            //等待读取数据    阻塞
                            int length = socket.getInputStream().read(bytes);
                            System.out.println(new String(bytes, 0, length));
                            System.out.println("数据读取成功");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}