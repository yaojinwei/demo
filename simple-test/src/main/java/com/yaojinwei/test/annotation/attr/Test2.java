package com.yaojinwei.test.annotation.attr;

import org.springframework.core.annotation.AnnotationUtils;

/**
 * @author jinwei.yjw
 * @date 2019/6/14 20:21
 */
public class Test2 {
    public static void main(String[] args) {
        Attr2 attr2 = AnnotationUtils.findAnnotation(Test1.class, Attr2.class);
        System.out.println(attr2);

        Attr1 attr1 = AopUtils.findMethodAnnotation(Test1.class, Test1.class.getMethods()[0], Attr1.class);
        System.out.println(attr1);
    }
}
