package com.yaojinwei.study.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author Yao.Jinwei
 * @date 2017/3/30 16:40
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class HelloNioClient {
    SocketChannel socketChannel = null;
    public static void main(String[] args) throws IOException {
        HelloNioClient client = new HelloNioClient();
        client.connectAndSendNoException();
    }

    public void connectAndSendNoException(){
        try {
            connectAndSend();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(socketChannel != null){
                    socketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void connectAndSend() throws IOException, InterruptedException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8888));
        if(socketChannel.finishConnect()){
            int i=0;
            while(true){
                TimeUnit.SECONDS.sleep(1);
                String message = "服务端你好！" + i++;
                buffer.clear();
                buffer.put(message.getBytes());
                buffer.flip();
                while(buffer.hasRemaining()){
                    System.out.println(buffer);
                    socketChannel.write(buffer);
                    System.out.println(buffer);
                }
                buffer.clear();
                socketChannel.read(buffer);
                System.out.println("after read: " + buffer);
                buffer.flip();
                System.out.println("after flip: " + buffer);
                if(buffer.hasRemaining()){
                    String receivedString = Charset
                            .forName("UTF-8").newDecoder().decode(buffer).toString();

                    // 控制台打印出来
                    System.out.println("接收到信息:" + receivedString);
                }

            }
        }
    }
}
