package com.yaojinwei.study.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import sun.misc.Launcher;

/**
 * jdbc 加载数据库驱动如何破坏双亲委托模式
 * https://www.cnblogs.com/huxuhong/p/11856786.html
 *
 * 为什么说JDBC驱动类加载破坏了双亲委派机制
 * 111 https://blog.csdn.net/weixin_34357928/article/details/92430549
 *
 * 通俗易懂的双亲委派机制
 * https://blog.csdn.net/codeyanbao/article/details/82875064
 *
 *
 * 真正理解线程上下文类加载器（多案例分析）---tomcat spring 类加载器
 * https://blog.csdn.net/yangcheng33/article/details/52631940
 * 当高层提供了统一接口让低层去实现，同时又要是在高层加载（或实例化）低层的类时，必须通过线程上下文类加载器来帮助高层的ClassLoader找到并加载该类。
 * 当使用本类托管类加载，然而加载本类的ClassLoader未知时，为了隔离不同的调用者，可以取调用者各自的线程上下文类加载器代为托管。
 * ————————————————
 * 版权声明：本文为CSDN博主「小杨Vita」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/yangcheng33/article/details/52631940
 *
 * DriverManager类：两种方式加载驱动类
 * 1、ServiceLoader 加载spi的驱动类，
 * 2、根据系统属性 jdbc.drivers 来加载驱动类
 * Class.forName(aDriver, true,
 *                         ClassLoader.getSystemClassLoader());
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class JdbcTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        /**
         * ClassLoader和Launcher的类加载器，输出为null，其实并不是没有类加载器的意思，而是在Java虚拟机中，获取类加载器时，null就代表着启动类加载器，也就是说，ClassLoader
         * 和Launcher类都是由启动类加载器加载到内存中的。我们也可以验证一下，Launcher类属于sun.misc包，这个包属于charsets
         * .jar包下，从上面的输出结果中，可以看到这个包是由启动类加载器加载的；而ClassLoader类是位于java.lang包下，位于resources.jar包下，同样也是由启动类加载器加载的。
         * ————————————————
         * 版权声明：本文为CSDN博主「巴斯光甜」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
         * 原文链接：https://blog.csdn.net/weixin_38950807/article/details/88680021
         */
        System.out.println(Launcher.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());

        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
        System.getProperty("java.system.class.loader");
        System.out.println(ClassLoader.class.getClassLoader());
        System.out.println(Launcher.class.getClassLoader());

        /**
         * 输出结果验证了我们的说法，系统类加载器和扩展类加载器都是由启动类加载器加载的，当加载这两个加载器时，也就是对这两个类的主动使用，会首先加载它们的父类，也就导致了ClassLoader的加载。
         */
        Launcher launcher = new Launcher();
        //获得系统类加载
        ClassLoader classLoader = launcher.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getClass().getClassLoader());
        //获得扩展类加载器
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getClass().getClassLoader());
        System.out.println(classLoader.getParent().getParent());
        /**
         * 版权声明：本文为CSDN博主「巴斯光甜」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
         *         原文链接：https://blog.csdn.net/weixin_38950807/article/details/88680021
         */




        DriverManager.setLogWriter(new PrintWriter(System.out));
        //Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

    }
}
