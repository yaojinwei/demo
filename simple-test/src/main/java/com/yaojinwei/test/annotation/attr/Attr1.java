package com.yaojinwei.test.annotation.attr;

/**
 * @author jinwei.yjw
 * @date 2019/6/14 20:19
 */
public @interface Attr1 {
    Attr2 attr2() default @Attr2();
}
