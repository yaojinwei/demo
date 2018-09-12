package com.yaojinwei.demo.common;

import java.io.Serializable;

/**
 * @author Yao.Jinwei
 * @date 2016/10/17 15:55
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class UserInfo implements Serializable{

    private static final long serialVersionUID = -4393697877792334716L;

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
