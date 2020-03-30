package com.yaojinwei.demo.spring.bean.order.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author jinwei.yjw
 * @date 2019-09-02 17:17
 */
@AutoConfigureBefore(FirstConfiguration.class)
public class SecondConfiguration {
    @Bean
//    @ConditionalOnMissingBean(Fank.class)
    @Primary
    public SecondFank secondFank() {
        return new SecondFank();
    }
}

