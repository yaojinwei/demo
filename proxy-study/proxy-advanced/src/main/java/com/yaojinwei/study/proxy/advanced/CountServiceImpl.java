package com.yaojinwei.study.proxy.advanced;

/**
 * Created by kingtakepc on 2016/9/14.
 */
public class CountServiceImpl implements CountService {
    private int cnt=0;
    @Override
    public int count() {
        return cnt++;
    }

    protected int num(){
        return 1;
    }

    private int num2(){
        return 2;
    }
}
