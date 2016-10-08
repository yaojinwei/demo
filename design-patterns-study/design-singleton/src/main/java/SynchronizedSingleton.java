/**
 * 懒汉式--双检锁
 * http://blog.csdn.net/lg312200538/article/details/4930451
 *
 * Created by yaojinwei on 2016/9/20.
 */
public class SynchronizedSingleton {
    private static SynchronizedSingleton singleton;

    private SynchronizedSingleton(){
        System.out.println("SynchronizedSingleton construct!");
    }

    public static SynchronizedSingleton getInstance(){
        if(singleton == null){
            singleton = new SynchronizedSingleton();
        }
        return singleton;
    }

}
