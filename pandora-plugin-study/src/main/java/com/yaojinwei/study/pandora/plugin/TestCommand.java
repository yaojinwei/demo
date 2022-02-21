package com.yaojinwei.study.pandora.plugin;

import com.taobao.pandora.sample.plugin.export.PandoraDemoExport;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
@Service
public class TestCommand  implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        PandoraDemoExport ex = new PandoraDemoExport();
    }
}
