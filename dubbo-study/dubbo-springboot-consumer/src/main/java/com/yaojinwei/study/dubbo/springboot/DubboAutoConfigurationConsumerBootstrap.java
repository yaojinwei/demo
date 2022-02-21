package com.yaojinwei.study.dubbo.springboot;

import com.yaojinwei.study.dubbo.dto.SpeakerDTO;
import com.yaojinwei.study.dubbo.interfaces.SpeakInterface;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.ReferenceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;

@EnableAutoConfiguration
@ComponentScan("com.yaojinwei.study.dubbo.service")
public class DubboAutoConfigurationConsumerBootstrap {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Bean
    public ReferenceBean speakInterface(){
        ReferenceBean bean = new ReferenceBean();
        bean.setId("speakInterface");
        bean.setUrl("dubbo://127.0.0.1:12345");
//        bean.setVersion("1.0.0");
        bean.setInterface("com.yaojinwei.study.dubbo.interfaces.SpeakInterface");
        return bean;
    }

    @Resource(name="speakInterface")
    private SpeakInterface demoService;

    public static void main(String[] args) {
        SpringApplication.run(DubboAutoConfigurationConsumerBootstrap.class).close();
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            SpeakerDTO speakerDTO = new SpeakerDTO();
            speakerDTO.setContry("china");
            speakerDTO.setCount(123);
            speakerDTO.setName("lala");
            logger.info("" + demoService.speak(speakerDTO));
        };
    }
}