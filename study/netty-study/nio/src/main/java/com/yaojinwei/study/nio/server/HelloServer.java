package com.yaojinwei.study.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

/**
 * @author Yao.Jinwei
 * @date 2017/3/21 18:09
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class HelloServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSock = ServerSocketChannel.open();
        InetSocketAddress addr = new InetSocketAddress(4000);
        serverSock.socket().bind(addr);

    }
}
