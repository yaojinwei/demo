package com.yaojinwei.demo.spring.bean.order.bean;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author jinwei.yjw
 * @date 2019-09-02 17:11
 */
@Component
//@Order(1)
public class ThirdBean implements Rank {
    @Override
    public String toString() {
        return "ThirdBean{}";
    }
}
