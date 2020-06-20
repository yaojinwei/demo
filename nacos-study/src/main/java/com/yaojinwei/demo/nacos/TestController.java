package com.yaojinwei.demo.nacos;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.alibaba.nacos.api.common.Constants.DEFAULT_GROUP;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
@RestController
public class TestController {

    @NacosInjected
    private ConfigService configService;

//    @NacosValue(value = "${test1}", autoRefreshed = true)
    private String value;
////
    @GetMapping("read")
    public String read() throws NacosException {
        value = configService.getConfig("test1", DEFAULT_GROUP, 5000);
        return value;
    }

    @GetMapping("write")
    public void write(String text) throws NacosException {
        configService.publishConfig("test", DEFAULT_GROUP, "gfssg");
    }

    @NacosConfigListener(dataId = "test1")
    public void onMessage(String config) {
//        assertEquals("mercyblitz", config); // asserts true
        value = config;
    }


}
