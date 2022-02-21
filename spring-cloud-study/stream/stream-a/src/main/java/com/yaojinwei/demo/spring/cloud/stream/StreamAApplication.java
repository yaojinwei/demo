package com.yaojinwei.demo.spring.cloud.stream;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
@SpringBootApplication
@EnableBinding({MySource.class, MySink.class})
public class StreamAApplication {
    public static void main(String[] args) {
        SpringApplication.run(StreamAApplication.class, args);
    }

    @Bean
    public CustomRunner customRunner() {
        return new CustomRunner();
    }

    public static class CustomRunner implements CommandLineRunner {

        @Autowired
        private MySource source;

        @Override
        public void run(String... args) throws Exception {
            int count = 3;
            for (int index = 1; index <= count; index++) {
                boolean result = source.output1().send(MessageBuilder.withPayload("msg-" + index)
                        .setHeader("test", "123123").build());
                System.out.println(result);
            }
        }
    }

}
