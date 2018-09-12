package com.yaojinwei.framework.sms.handler;

/**
 * @author jinwei.yjw
 * @date 2018/4/29 18:47
 */
public interface Channel {
    /**
     * Return the global unique identifier of this {@link Channel}
     */
    Integer getId();
    /**
     * Return
     */
    ChannelPipeline pipeline();



}
