package org.example.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * AIO
 * 异步非阻塞IO
 *
 * 老师收作业
 *
 * 一个老师，多个学生
 * 学生写完作业，举手，老师过去收作业，如果这个学生作业没写完，老师一直等着，收完这个学生的作业继续进入等待状态
 * 老师没有学生举手的时候可以去干别的
 */
public class AioServer {
    public static void main(String[] args) throws IOException {
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
        server.bind(new InetSocketAddress(8081));

        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel client, Object attachment) {
                server.accept(null, this);

                handleClient(client);
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.printf("接受🔗失败：" + exc);
            }
        });

        System.in.read();

    }

    public static void handleClient(AsynchronousSocketChannel client)  {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            System.out.printf(client.getRemoteAddress() + "🔗");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        client.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if(result == -1){
                    try {
                        client.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }

                attachment.flip();
                byte[] data = new byte[attachment.remaining()];
                attachment.get(data);
                String message = new String(data);
                System.out.println("消息：" + message);
                attachment.clear();

                client.read(attachment, attachment, this);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("读取数据失败：" + exc);
            }
        });
    }
}
