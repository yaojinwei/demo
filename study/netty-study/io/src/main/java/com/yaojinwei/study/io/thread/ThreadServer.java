package com.yaojinwei.study.io.thread;

import com.yaojinwei.study.io.simple.StreamWriter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  使用传统的方式进行搭建的服务端，在每接收一个客户端的请求，服务端就创建一个新的线程，用来处理这个请求，
 *  但是在处理过程中，这些新建的线程往往需要等待客户端的输入和其他操作，需要阻塞式等待，因此，如果一个服务器负载很重，
 *  他就需要创建大量的这种等待的线程，cpu需要花费大量的时间去查询这些阻塞线程的状态是否可用，
 *  而且每次进行查询的时候需要切换线程的状态，造成了很大的性能开销。
 *  @see <a href="http://blog.sina.com.cn/s/blog_616e189f0100s7mm.html" >使用javaNIO提高服务端性能</a>
 *
 * @author Yao.Jinwei
 * @date 2017/3/22 17:28
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class ThreadServer {
    public static void main(String[] args) throws IOException {
        //1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
        ServerSocket serverSocket = new ServerSocket(4001);
//        int num = 0;
        while(true){
            //2、调用accept()方法开始监听，等待客户端的连接
            Socket socket = serverSocket.accept();
            new ProcessThread(socket).start(); //创建一个单独的线程来处理请求
        }
    }

    private static class ProcessThread extends Thread{
        private Socket socket;

        public ProcessThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                //3、获取输入流，并读取客户端信息
                InputStream inputStream = socket.getInputStream();
                StreamWriter.write(inputStream, System.out);

                socket.shutdownInput();  //关闭输入流
                //4、获取输出流，响应客户端的请求
                OutputStream os = socket.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                bw.write("我是服务器"+ "\n");

                //5、关闭资源
                bw.flush();
            }
            catch (IOException ex){

            }
            finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
