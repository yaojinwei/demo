/**
 * 懒汉式--双检锁
 * http://blog.csdn.net/lg312200538/article/details/4930451
 *
 * Created by kingtakepc on 2016/9/20.
 */
public class SynchronizedSingleton {
    private static SynchronizedSingleton singleton;

    private SynchronizedSingleton(){
        System.out.println("StaticSingleton construct!");
    }

    public static SynchronizedSingleton getInstance(){
        if(singleton == null){
            singleton = new SynchronizedSingleton();
        }
        return singleton;
    }

    /**
     * 同步代码块，双重检查锁
     * @return
     */
    public static SynchronizedSingleton getInstance2(){
        if(singleton == null){
            synchronized (SynchronizedSingleton.class){
                if(singleton == null){
                    singleton = new SynchronizedSingleton();
                }
            }
        }
        return singleton;
    }
}
