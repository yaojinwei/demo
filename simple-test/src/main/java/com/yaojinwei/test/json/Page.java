package com.yaojinwei.test.json;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;

/**
 * @author jinwei.yjw
 * @date 2018/11/22 15:01
 */
public class Page<T> extends HashMap{
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static void main(String[] args) {
        Page page = new Page();
        page.setTotal(10);
        page.put("123","111");
        System.out.println(JSON.toJSON(page));
    }
}
