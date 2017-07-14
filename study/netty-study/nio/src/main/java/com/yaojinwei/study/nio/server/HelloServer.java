package com.yaojinwei.study.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author Yao.Jinwei
 * @date 2017/3/21 18:09
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class HelloServer {
    public static void main(String[] args) throws IOException {
        // 本地字符集
        final String localCharSetName = "UTF-8";
        // 超时时间,单位毫秒
        final int listenningPort = 8888;
        // 缓冲区大小
        final int bufferSize = 1024;
        // 超时时间,单位毫秒
//        int timeOut = 3000;

        // 1、创建一个在本地端口进行监听的服务Socket信道
        final ServerSocketChannel serverChannel = ServerSocketChannel.open();
        InetSocketAddress addr = new InetSocketAddress(listenningPort);
        serverChannel.bind(addr);
        //2、设置为非阻塞方式，如果配置为阻塞模式，就不能用register关联SelectionKey.OP_ACCEPT，
        // 需要使用serverChannel.accept()接收连接请求，参考tomcat
        serverChannel.configureBlocking(false);

        serverChannel.socket().setSoTimeout(100);
        //3、创建一个选择器
        final Selector selector = Selector.open();
        //4、将server 信道的accept事件放入监听器
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);//

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                        // 5、等待某个信道就绪
                    try {
                        selector.select();  //阻塞等待连接事件的到来
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                        //Set<SelectionKey> keys = select.selectedKeys();//获取发生的事件
                        //for(SelectionKey key:keys)//遍历处理事件
                        // 使用迭代器进行遍历就绪信道
                        while(keys.hasNext())
                        {
                            try {
                                SelectionKey key = keys.next();
                                // 这种情况是有客户端连接过来,准备一个clientChannel与之通信
                                if (key.isAcceptable()) {
                                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                                    clientChannel.configureBlocking(false);
                                    clientChannel.register(selector, SelectionKey.OP_READ,
                                            ByteBuffer.allocate(bufferSize));
                                }
                                // 客户端有写入时
                                if (key.isReadable())//发生了读事件
                                {
                                    // 获得与客户端通信的信道
                                    SocketChannel clientChannel = (SocketChannel) key.channel();
                                    // 得到并重置缓冲区的主要索引值
                                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                                    buffer.clear();
                                    // 读取信息获得读取的字节数
                                    long bytesRead = clientChannel.read(buffer);
                                    if (bytesRead == -1) {
                                        // 没有读取到内容的情况
                                        clientChannel.close();
                                    } else {
                                        // 将缓冲区准备为数据传出状态
                                        buffer.flip();
                                        // 将获得字节字符串(使用Charset进行解码)
                                        String receivedString = Charset
                                                .forName(localCharSetName).newDecoder().decode(buffer).toString();

                                        // 控制台打印出来
                                        System.out.println("接收到信息:" + receivedString);

                                        // 准备发送的文本
                                        String sendString = "你好,客户端. 已经收到你的信息" + receivedString;

                                        // 将要发送的字符串编码(使用Charset进行编码)后再进行包装
                                        buffer = ByteBuffer.wrap(sendString.getBytes(localCharSetName));

                                        // 发送回去
                                        clientChannel.write(buffer);

                                        // 设置为下一次读取或是写入做准备
                                        key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                                    }
                                }
                            }
                            catch (IOException e){
                                e.printStackTrace();
                            }
                            finally {
                                keys.remove();
                            }
                        }
                }

            }
        });
        thread.setDaemon(false);
        thread.start();
    }
}
