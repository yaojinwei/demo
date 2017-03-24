package com.yaojinwei.study.io.simple;

import java.io.*;
import java.net.Socket;

/**
 * @author Yao.Jinwei
 * @date 2017/3/21 18:57
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class HelloClient {
    public static void main(String[] args) throws Exception {
        for(int i=0;i<100000;i++){
            //1、创建客户端Socket，指定服务器地址和端口
            Socket socket = new Socket("127.0.0.1",4001);
            //2、获取输出流，向服务器端发送信息
            OutputStream os = socket.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write("我是客户端" + i + "\n");
            bw.write("woshi kehuduan" + i + "\n");
            bw.flush();
            socket.shutdownOutput();  //关闭输出流

            //3、获取输入流，并读取服务器端的响应信息
            InputStream is = socket.getInputStream();
            StreamWriter.write(is, System.out);

            //4、关闭资源
            bw.close();  //这个放到前面会关闭socket
            os.close();
            socket.close();
        }
    }
}
