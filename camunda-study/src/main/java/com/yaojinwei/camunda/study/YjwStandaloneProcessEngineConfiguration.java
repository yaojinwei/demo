package com.yaojinwei.camunda.study;

import org.camunda.bpm.engine.impl.cfg.StandaloneProcessEngineConfiguration;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class YjwStandaloneProcessEngineConfiguration extends StandaloneProcessEngineConfiguration {

    @Override
    protected void init() {
        System.out.println("##########开始init");
        super.init();
        System.out.println("########结束init");
    }

    @Override
    public void initHistoryLevel() {
        super.initHistoryLevel();
    }
}
