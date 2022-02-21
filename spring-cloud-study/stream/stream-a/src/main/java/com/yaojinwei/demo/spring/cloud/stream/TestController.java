package com.yaojinwei.demo.spring.cloud.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private MySource source;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String send(String msg) {
        Message message = MessageBuilder.withPayload(msg)
                .build();

        source.output1().send(message);
        return "字符串消息发送成功!";
    }


    @StreamListener("input1")
    public void receiveInput1(Message<String> receiveMsg) {
        System.out.println("input1 receive: " + receiveMsg);
    }
//    @RequestMapping(value = "/sendWithTags", method = RequestMethod.GET)
//    public String sendWithTags(String msg) {
//        senderService.sendWithTags(msg, "tagStr");
//        return "带tag字符串消息发送成功!";
//    }
//
//    @RequestMapping(value = "/sendObject", method = RequestMethod.GET)
//    public String sendObject(int index) {
//        senderService.sendObject(new Foo(index, "foo"), "tagObj");
//        return "Object对象消息发送成功!";
//    }
}