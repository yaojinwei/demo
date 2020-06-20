package com.yaojinwei.demo.nacos;

import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @NacosConfigListener(dataId = "test")
    public void onMessage(String config) {
        System.out.println(config);
    }
}
