package com.yaojinwei.demo.spring.cloud.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public interface MySink {
    @Input("input1")
    SubscribableChannel input1();
}
