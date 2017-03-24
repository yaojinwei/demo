package com.yaojinwei.study.netty3.server;

import com.yaojinwei.study.netty3.client.HelloClient;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Yao.Jinwei
 * @date 2016/12/21 14:19
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class HelloServer {
    private int port;

    public HelloServer(int port) {
        this.port = port;
    }

    public void start() {
        // 1.创建boss和work两个线程池
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();
        // 2.创建ChannelFactory
        ChannelFactory channelFactory = new NioServerSocketChannelFactory(boss, worker);
        // 3.创建ServerBootstrap
        ServerBootstrap bootstrap = new ServerBootstrap(channelFactory);
        // 4.设置管道工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new ServerHandler());
            }
        });
        // 5.绑定端口，启动服务
        bootstrap.bind(new InetSocketAddress(port));
        System.out.println("hello server started!");
    }

    public static void main(String[] args) {
        HelloServer server = new HelloServer(1234);
        server.start();
    }
}
