package com.yaojinwei.study.netty3.client;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Yao.Jinwei
 * @date 2016/12/21 14:35
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class HelloClient {
    private final String host;
    private final int port;

    public HelloClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start(){
        // 1.创建boss和work两个线程池
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();
        // 2.创建ChannelFactory
        ChannelFactory channelFactory = new NioClientSocketChannelFactory(boss, worker);
        // 3.创建ClientBootstrap
        ClientBootstrap bootstrap = new ClientBootstrap(channelFactory);
        // 4.设置管道工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new ClientHandler());
            }
        });
        // 5.连接服务端
        ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port));
        future.awaitUninterruptibly();
        // 6.
        future.getChannel().getCloseFuture().awaitUninterruptibly();
        // 关闭时释放资源
        bootstrap.releaseExternalResources();
    }


    public static void main(String[] args) {
        HelloClient client = new HelloClient("127.0.0.1", 1234);
        client.start();

        new Scanner(System.in).nextLine();
    }
}
