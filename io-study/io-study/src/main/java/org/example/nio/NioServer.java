package org.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 同步非阻塞IO
 *
 * 老师收作业
 * 一个老师，多个学生
 * 老师一直来回转，看哪些同学的作业写完了，如果写完了就一起收上来
 *
 * 相比AIO，有一个轮询的线程
 *
 * 适用于连接数少且连接比较短的架构
 */
public class NioServer {
    public static final String BYE = "bye";
    private Selector selector;
    public static final int PORT = 8082;

    private ServerSocketChannel serverSocketChannel;

    public NioServer(){
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务器启动，端口：" + PORT);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void start(){
        try {
            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    iterator.remove();
                    ;
                    handle(next);
                }
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void handle(SelectionKey key) throws IOException {
        if(key.isAcceptable()){
            ServerSocketChannel server = (ServerSocketChannel)key.channel(); //获取服务器通道
            SocketChannel client = server.accept(); //接受客户端连接
            client.configureBlocking(false); //设置为非阻塞模式
            client.register(selector,SelectionKey.OP_READ ); //注册读事件
            client.write(ByteBuffer.wrap("欢迎，请输入".getBytes())); //向客户端发送欢迎消息

            System.out.println("客户端" + client.getRemoteAddress() + "已连接");
        }
        else if(key.isReadable()){ // 读时间，有客户端发送消息
            SocketChannel client = (SocketChannel)key.channel(); //获取客户端通道
            ByteBuffer buffer = ByteBuffer.allocate(1024); //创建缓冲区

            int read = client.read(buffer); //读取客户端发送的数据到缓冲区

            if(read > 0){
                buffer.flip(); //切换缓冲区为读模式
                String message = new String(buffer.array(), 0, read);
                System.out.println("收到客户端消息:" + client.getRemoteAddress() + ", 内容：" + message);
                if(BYE.equals(message.trim())){ //如果是断开连接的指令，关闭通道并取消注册
                    client.close();
                }
            }

        }
        else if(key.isWritable()){ //写事件，向客户端发送数据完成后，注册读事件，等待下一次读数据
            SocketChannel client = (SocketChannel)key.channel(); //获取客户端通道
            ByteBuffer buffer = ByteBuffer.wrap("asdfasdfasfd".getBytes());
            client.write(buffer);
            key.interestOps(SelectionKey.OP_READ);
        }
    }

    public static void main(String[] args) {
        new NioServer().start();
    }
}
