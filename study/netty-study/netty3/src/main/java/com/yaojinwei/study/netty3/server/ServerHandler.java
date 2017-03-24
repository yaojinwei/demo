package com.yaojinwei.study.netty3.server;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import java.nio.charset.Charset;

/**
 * @author Yao.Jinwei
 * @date 2016/12/21 14:34
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class ServerHandler extends SimpleChannelUpstreamHandler {
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        // Send back the received message to the remote peer.
        ChannelBuffer acceptBuff = (ChannelBuffer) e.getMessage();
        String info = acceptBuff.toString(Charset.defaultCharset());
        if (info != null && !"".equals(info)) {
            System.out.println("服务端接收到>>>>>" + info);
            ChannelBuffer sendBuff = ChannelBuffers.dynamicBuffer();
            sendBuff.writeBytes("服务端已接收到信息！".getBytes());
            e.getChannel().write(sendBuff);
        } else {
            e.getChannel().write("服务端没有接收到信息！");
        }
        e.getChannel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        // Close the connection when an exception is raised.
        e.getCause();
        e.getChannel().close();
    }
}
