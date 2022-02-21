package com.yaojinwei.demo.spring.cloud.stream;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public interface MySource {

    @Output("output1")
    MessageChannel output1();

    @Output("output2")
    MessageChannel output2();

    public static class Ab{
        private Integer test;

        public Integer getTest() {
            return test;
        }

        public void setTest(Integer test) {
            this.test = test;
        }
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Map map = new HashMap();
        map.put("test",123);
        Ab  ab= new Ab();

        BeanUtils.populate(ab, map);
        System.out.println(ab.getTest());
    }
}