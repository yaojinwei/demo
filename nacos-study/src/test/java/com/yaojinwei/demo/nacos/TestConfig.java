package com.yaojinwei.demo.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class TestConfig {
    public static void main(String[] args) throws NacosException, InterruptedException {
        String serverAddr = "11.164.62.162:8848";
        String dataId = "234";
        String group = "DEFAULT_GROUP";
        Properties properties = new Properties();


        properties.put(PropertyKeyConst.NAMESPACE, "test");
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        properties.put(PropertyKeyConst.ENABLE_REMOTE_SYNC_CONFIG, "true");

        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfigAndSignListener(dataId, group, 5000, new Listener() {

            public void receiveConfigInfo(String configInfo) {
                System.out.println("recieve:" + configInfo);
            }

            public Executor getExecutor() {
                return null;
            }
        });

        System.out.println(content);
        configService.addListener(dataId, group, new Listener() {

            public void receiveConfigInfo(String configInfo) {
                System.out.println("recieve:" + configInfo);
            }

            public Executor getExecutor() {
                return null;
            }
        });

        boolean isPublishOk = configService.publishConfig(dataId, group, "content");
        System.out.println(isPublishOk);

        Thread.sleep(3000);
        content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);

//        boolean isRemoveOk = configService.removeConfig(dataId, group);
//        System.out.println(isRemoveOk);
//        Thread.sleep(3000);

        content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);
        Thread.sleep(300000);
    }
}
