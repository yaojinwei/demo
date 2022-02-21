package com.yaojinwei.study.dubbo;

import com.yaojinwei.study.dubbo.interfaces.SpeakInterface;
import org.apache.dubbo.config.spring.ServiceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
@Configuration
public class ProviderConfiguration {

    @Bean
    public ServiceBean speackInterface1(SpeakInterface speackInterface){
        ServiceBean serviceBean = new ServiceBean();
        serviceBean.setInterface("com.yaojinwei.study.dubbo.interfaces.SpeakInterface");
        serviceBean.setRef(speackInterface);
        return serviceBean;
    }
}
