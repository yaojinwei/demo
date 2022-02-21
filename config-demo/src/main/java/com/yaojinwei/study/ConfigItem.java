package com.yaojinwei.study;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public interface ConfigItem {
    String key();

    String toJson();

    Object parse();


}
