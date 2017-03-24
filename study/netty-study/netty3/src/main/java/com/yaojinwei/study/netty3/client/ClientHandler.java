package com.yaojinwei.study.netty3.client;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

import java.nio.charset.Charset;

/**
 * @author Yao.Jinwei
 * @date 2016/12/21 14:40
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class ClientHandler extends SimpleChannelUpstreamHandler {

    /**
     * Creates a client-side handler.
     */
    public ClientHandler() {

    }

    @Override
    public void channelConnected(
            ChannelHandlerContext ctx, ChannelStateEvent e) {
        ChannelBuffer sendBuff = ChannelBuffers.dynamicBuffer();
        sendBuff.writeBytes("客户端发送消息".getBytes());

        e.getChannel().write(sendBuff);
        System.out.println("客户端发送信息完成！");
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        // Send back the received message to the remote peer.
        ChannelBuffer acceptBuff = (ChannelBuffer) e.getMessage();
        String info = acceptBuff.toString(Charset.defaultCharset());
        System.out.println(info);
        e.getChannel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        // Close the connection when an exception is raised.
        e.getCause();
        e.getChannel().close();
    }
}
