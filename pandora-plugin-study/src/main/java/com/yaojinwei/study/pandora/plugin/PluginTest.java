package com.yaojinwei.study.pandora.plugin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.taobao.pandora.boot.PandoraBootstrap;

/**
 * Pandora Boot应用的入口类
 *
 * @author chengxu
 */
@SpringBootApplication
public class PluginTest {
    public static void main(String[] args) {
        PandoraBootstrap.run(args);
        SpringApplication.run(PluginTest.class, args);
        PandoraBootstrap.markStartupAndWait();
    }
}

