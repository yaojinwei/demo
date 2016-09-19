package com.yaojinwei.study.proxy.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class TableDAOFactory {
    private static TableDAO tDao = new TableDAO();   
    public static TableDAO getInstance(){   
        return tDao;   
    }
    public static TableDAO getAuthInstance(AuthProxy authProxy){
        Enhancer en = new Enhancer();
        //进行代理
        en.setSuperclass(TableDAO.class);
//        en.setCallback(authProxy);
        //filter
        //NoOp.INSTANCE是CGlib所提供的实际是一个没有任何操作的拦截器，他们是有序的。一定要和CallbackFilter里面的顺序一致
        en.setCallbacks(new Callback[]{authProxy, NoOp.INSTANCE});
        en.setCallbackFilter(new AuthProxyFilter());
        //生成代理实例
        return (TableDAO)en.create();
    }
} 