package com.yaojinwei.test.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

import javax.imageio.spi.IIOServiceProvider;

/**
 * @author jinwei.yjw
 * @date 2018/7/5 14:07
 */
public class ServiceLoaderTests {
    public static void main(String[] args) {
        ServiceLoaderTests.class.getClassLoader().getResource("/META-INF/services/javax.imageio.spi.ImageWriterSpi");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

    }
}
