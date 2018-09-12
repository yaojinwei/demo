package com.yaojinwei.framework.sms.handler;

/**
 * @author jinwei.yjw
 * @date 2018/4/29 18:44
 */
public interface ChannelPipeline {
    void addFirst(String var1, ChannelHandler var2);

    void addLast(String var1, ChannelHandler var2);

    void addBefore(String var1, String var2, ChannelHandler var3);

    void addAfter(String var1, String var2, ChannelHandler var3);

    void remove(ChannelHandler var1);

    ChannelHandler remove(String var1);

    <T extends ChannelHandler> T remove(Class<T> var1);

    ChannelHandler removeFirst();

    ChannelHandler removeLast();

    ChannelHandler getFirst();

    ChannelHandler getLast();

    ChannelHandler get(String var1);

}
