package com.yaojinwei.demo.tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yaojinwei on 2016/10/17.
 */
public class Server {
    //统计服务端接收了多少次请求
    private static int count = 0;

    public static void main(String[] args) {
        ServerSocket ss = null;
        Socket socket = null;
        try {
            ss = new ServerSocket(9999);
            System.out.println("服务初始化完毕，等待客户连接......");
            while(true){
                socket = ss.accept();
                //解析客户端里面的请求参数
                //得到客户端输入流
                InputStream is = socket.getInputStream();
                Request request = new Request(is);
                count++;
                //拿到客户端输出字节流
                OutputStream os = socket.getOutputStream();
                Response response = new Response(os);
                String html = "<html><head><title></title> <meta http-equiv=Content-Type content='text/html;charset=utf-8'></head><body>当前时间："
                        + "</br>服务器回复：<font size=12 color='red'>国庆节快乐</font></body></html>";
                //response.write(html);
                String uri = request.getUri();
                if(uri.endsWith(".html")){
                    //写出这个静态资源
                    response.writeHtmlFile(uri.substring(1));
                }

                //整个客户端请求全部完成
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
