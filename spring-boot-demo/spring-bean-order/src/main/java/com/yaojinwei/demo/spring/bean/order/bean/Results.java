package com.yaojinwei.demo.spring.bean.order.bean;

import com.yaojinwei.demo.spring.bean.order.configuration.Fank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jinwei.yjw
 * @date 2019-09-02 17:12
 */
@Component
public class Results {
    @Autowired
    private List<Rank> ranks;
    @Autowired
    private List<Fank> fanks;

    @Override
    public String toString() {
        return "Results{" +
                "ranks=" + ranks +
                ", fanks=" + fanks +
                '}';
    }
}


