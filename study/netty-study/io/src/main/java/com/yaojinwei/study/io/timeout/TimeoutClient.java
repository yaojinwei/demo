package com.yaojinwei.study.io.timeout;

import com.yaojinwei.study.io.simple.StreamWriter;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * @author Yao.Jinwei
 * @date 2017/3/27 11:37
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class TimeoutClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        //1、创建客户端Socket，指定服务器地址和端口
        Socket socket = new Socket();
        long start = System.currentTimeMillis();
        System.out.println("start to connect: " + start);
        //连接需在指定时间内完成，否则断开连接
        try {
            socket.connect(new InetSocketAddress("127.0.0.1", 4001), 1000);
        }
        catch (SocketTimeoutException ex){
        }
        finally {
            long end = System.currentTimeMillis();
            System.out.println("end connect: " + System.currentTimeMillis() + ", spend total: " + (end - start));
        }

        //这个参数测试没有效果
        socket.setSoTimeout(1000);

        for(int i=0;i<1000;i++){}
        //2、获取输出流，向服务器端发送信息
        OutputStream os = socket.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        bw.write("我是客户端" + "\n");
        bw.write("woshi kehuduan" + "\n");
        bw.flush();
        socket.shutdownOutput();  //关闭输出流

        Thread.sleep(10000);
        //3、获取输入流，并读取服务器端的响应信息
        InputStream is = socket.getInputStream();
        StreamWriter.write(is, System.out);

        //4、关闭资源
        bw.close();  //这个放到前面会关闭socket
        os.close();
        socket.close();
    }
}
