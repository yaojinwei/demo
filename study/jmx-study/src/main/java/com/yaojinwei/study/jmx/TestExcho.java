package com.yaojinwei.study.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @author Yao.Jinwei
 * @date 2016/12/22 18:11
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class TestExcho {
//    MBeanServer server = ManagementFactory.getPlatformMBeanServer();
//    ObjectName name = new ObjectName("com.demo.mbean:type=Excho");
//    Echo ei =new Echo();
//    server.registerMBean(ei,name);
//    server.invoke(name, "setName", new Object[] { "jack"}, new String[] {"java.lang.String"});
//    server.invoke(name, "showName", null, null);
//    String yourName = (String)server.invoke(name, "fetchName", new Object[] { "mimi",10}, new String[] {"java.lang.String","int"});
//    System.out.println("~~~"+yourName);
//    CompositeData d =  (CompositeData) server.invoke(name, "fetchConfig", null, null);
//    String fn = (String)d.get("firstName");
//    String ln = (String)d.get("lastName");
//    System.out.println("~@@@~~"+fn+"@@"+ln);
//    int num = (Integer)server.getAttribute(name, "Num");
//    System.out.println("~OOOOO~~"+num);
//    Thread.currentThread().sleep(Integer.MAX_VALUE);
}
