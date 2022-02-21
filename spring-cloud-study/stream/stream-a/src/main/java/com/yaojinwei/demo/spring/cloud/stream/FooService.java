package com.yaojinwei.demo.spring.cloud.stream;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
@Service
public class FooService {

    public void checkInfo(Message message){
        System.out.println(message);
    }
}
