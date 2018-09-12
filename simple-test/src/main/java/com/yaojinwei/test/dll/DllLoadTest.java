package com.yaojinwei.test.dll;

import java.io.File;
import java.lang.reflect.Field;

/**
 * -Djava.library.path=D:\opensource\dcm4che\dcm4che-assembly\target\dcm4che-5.13.3-bin\dcm4che-5.13.3\lib\win-x86_64\
 * @author jinwei.yjw
 * @date 2018/6/25 9:51
 */
public class DllLoadTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        //将class目录添加到java.library.path
        File f = new File(DllLoadTest.class.getResource("/").getPath());
        //打印当前路径
        System.out.println(f);
        System.setProperty("java.library.path", System.getProperty("java.library.path")
            + ";" + f.getAbsoluteFile());
        //必须将sys_paths置为null，添加的路径才会生效
        // https://blog.csdn.net/u012414590/article/details/52382476
        Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
        fieldSysPath.setAccessible(true);
        fieldSysPath.set(null, null);
        //添加当前路径java.library.path
        System.out.println(System.getProperty("java.library.path"));

        //当前JVM位数
        System.out.println(System.getProperty("sun.arch.data.model"));
        //尝试加载dll
        System. loadLibrary ("opencv_java");
    }
}
