package com.yaojinwei.test.management;

import java.lang.management.BufferPoolMXBean;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.List;
 
import javax.management.MBeanServerConnection;

/**
 *
 PlatformManagementObject接口：所有的管理接口都要继承该接口，这个接口是从1.7才出现的，从其文档的注释中可以看到其存在的价值是为以后平台的扩展而设计的，而不是为了应用程序。
 BufferPoolMXBean 接口：缓冲池管理接口包括direct和mapped类型的缓冲池。
 ClassLoadingMXBean	类加载管理接口，可以监控管理虚拟机类加载系统。
 CompilationMXBean	用于 Java 虚拟机的编译系统的管理接口。
 GarbageCollectorMXBean	虚拟机垃圾收集的管理接口，通过该接口可以查看垃圾收集的时间和次数。
 MemoryManagerMXBean	该接口用于内存管理，其中，垃圾收集器属于该类型的内存管理器。
 MemoryMXBean	用于虚拟机的内存管理，执行GC、获取堆内存和非堆内存相关数据
 MemoryPoolMXBean	用于内存池的管理，所谓的内存池表示的是虚拟机使用和内存管理者管理的内存资源。
 OperatingSystemMXBean	操作系统管理接口，可以查看系统的平均负载、系统参数、可用的进程数、系统版本和名称等等。
 PlatformLoggingMXBean接口：日志管理接口，可以设置日志级别、获取日志名称等等。
 RuntimeMXBean	虚拟机运行时管理接口，获取虚拟机的名称、虚拟机版本、获取java的classpath、获取系统参数等。
 ThreadMXBean  虚拟机线程管理。可以获取线程数、获取线程Id、线程信息、当前线程CPU时间、当前线程用户时间、查看死锁线程等等。
 https://blog.csdn.net/wangyang1354/article/details/55514054
 */
public class MBeanDemo {

    public static void main(String[] args) {

        showJvmInfo();
        showMemoryInfo();
        showSystem();
        showClassLoading();
        showCompilation();
        showThread();
        showGarbageCollector();
        showMemoryManager();
        showMemoryPool();
    }

    public static void showBufferPoolInfo(){
        //BufferPoolMXBean bufferPoolMXBean = ManagementFactory
    }
    /**
     * Java 虚拟机的运行时系统
     */
    public static void showJvmInfo() {
        RuntimeMXBean mxbean = ManagementFactory.getRuntimeMXBean();
        String vendor = mxbean.getVmVendor();
        System.out.println("jvm name:" + mxbean.getVmName());
        System.out.println("jvm version:" + mxbean.getVmVersion());
        System.out.println("jvm bootClassPath:" + mxbean.getBootClassPath());
        System.out.println("jvm start time:" + mxbean.getStartTime());
    }

    /**
     * Java 虚拟机的内存系统
     */
    public static void showMemoryInfo() {
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        MemoryUsage heap = mem.getHeapMemoryUsage();
        System.out.println("Heap committed:" + heap.getCommitted() + " init:" + heap.getInit() + " max:"
            + heap.getMax() + " used:" + heap.getUsed());
    }

    /**
     * Java 虚拟机在其上运行的操作系统
     */
    public static void showSystem() {
        OperatingSystemMXBean op = ManagementFactory.getOperatingSystemMXBean();
        System.out.println("Architecture: " + op.getArch());
        System.out.println("Processors: " + op.getAvailableProcessors());
        System.out.println("System name: " + op.getName());
        System.out.println("System version: " + op.getVersion());
        System.out.println("Last minute load: " + op.getSystemLoadAverage());
    }

    /**
     * Java 虚拟机的类加载系统
     */
    public static void showClassLoading() {
        ClassLoadingMXBean cl = ManagementFactory.getClassLoadingMXBean();
        System.out.println("TotalLoadedClassCount: " + cl.getTotalLoadedClassCount());
        System.out.println("LoadedClassCount" + cl.getLoadedClassCount());
        System.out.println("UnloadedClassCount:" + cl.getUnloadedClassCount());
    }

    /**
     * Java 虚拟机的编译系统
     */
    public static void showCompilation() {
        CompilationMXBean com = ManagementFactory.getCompilationMXBean();
        System.out.println("TotalCompilationTime:" + com.getTotalCompilationTime());
        System.out.println("name:" + com.getName());
    }

    /**
     * Java 虚拟机的线程系统
     */
    public static void showThread() {
        ThreadMXBean thread = ManagementFactory.getThreadMXBean();
        System.out.println("ThreadCount" + thread.getThreadCount());
        System.out.println("AllThreadIds:" + thread.getAllThreadIds());
        System.out.println("CurrentThreadUserTime" + thread.getCurrentThreadUserTime());
        //......还有其他很多信息
    }

    /**
     * Java 虚拟机中的垃圾回收器。
     */
    public static void showGarbageCollector() {
        List<GarbageCollectorMXBean> gc = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean GarbageCollectorMXBean : gc) {
            System.out.println("name:" + GarbageCollectorMXBean.getName());
            System.out.println("CollectionCount:" + GarbageCollectorMXBean.getCollectionCount());
            System.out.println("CollectionTime" + GarbageCollectorMXBean.getCollectionTime());
        }
    }

    /**
     * Java 虚拟机中的内存管理器
     */
    public static void showMemoryManager() {
        List<MemoryManagerMXBean> mm = ManagementFactory.getMemoryManagerMXBeans();
        for (MemoryManagerMXBean eachmm : mm) {
            System.out.println("name:" + eachmm.getName());
            System.out.println("MemoryPoolNames:" + eachmm.getMemoryPoolNames().toString());
        }
    }

    /**
     * Java 虚拟机中的内存池
     */
    public static void showMemoryPool() {
        List<MemoryPoolMXBean> mps = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean mp : mps) {
            System.out.println("name:" + mp.getName());
            System.out.println("CollectionUsage:" + mp.getCollectionUsage());
            System.out.println("type:" + mp.getType());
        }
    }

    /**
     * 访问 MXBean 的方法的三种方法
     */
    public static void visitMBean() {

        //第一种直接调用同一 Java 虚拟机内的 MXBean 中的方法。
        RuntimeMXBean mxbean = ManagementFactory.getRuntimeMXBean();
        String vendor1 = mxbean.getVmVendor();
        System.out.println("vendor1:" + vendor1);

        //第二种通过一个连接到正在运行的虚拟机的平台 MBeanServer 的 MBeanServerConnection。
        MBeanServerConnection mbs = null;
        // Connect to a running JVM (or itself) and get MBeanServerConnection
        // that has the JVM MXBeans registered in it
 
        /*
        try {
            // Assuming the RuntimeMXBean has been registered in mbs
            ObjectName oname = new ObjectName(ManagementFactory.RUNTIME_MXBEAN_NAME);
            String vendor2 = (String) mbs.getAttribute(oname, "VmVendor");
            System.out.println("vendor2:" + vendor2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        //第三种使用 MXBean 代理
        //        MBeanServerConnection mbs3 = null;
        //        RuntimeMXBean proxy;
        //        try {
        //            proxy = ManagementFactory.newPlatformMXBeanProxy(mbs3,ManagementFactory.RUNTIME_MXBEAN_NAME,
        //                                                     RuntimeMXBean.class);
        //            String vendor = proxy.getVmVendor();
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }

    }

}