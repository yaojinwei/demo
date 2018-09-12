package com.yaojinwei.study.io.timeout;

import com.yaojinwei.study.io.simple.StreamWriter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Yao.Jinwei
 * @date 2017/3/27 11:36
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class TimeoutServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        //1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
        ServerSocket serverSocket = new ServerSocket(4001);
        //accept获取连接时，阻塞指定时间
//        serverSocket.setSoTimeout(1000);
        int num = 0;
        while(true){
            //2、调用accept()方法开始监听，等待客户端的连接
            Socket socket = serverSocket.accept();
            socket.setSoTimeout(1000);
            socket.setKeepAlive(true);
            //3、获取输入流，并读取客户端信息
            InputStream inputStream = socket.getInputStream();
            StreamWriter.write(inputStream, System.out);

            socket.shutdownInput();  //关闭输入流
            //4、获取输出流，响应客户端的请求
            OutputStream os = socket.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write("我是服务器" + num++ + "\n");

            //5、关闭资源
            bw.flush();

            bw.close();   //关闭时，连带关闭socket
            socket.close();
        }
    }
}
