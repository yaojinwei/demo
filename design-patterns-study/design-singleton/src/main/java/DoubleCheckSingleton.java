/**
 * 懒汉式--双检锁
 * http://blog.csdn.net/lg312200538/article/details/4930451
 *
 * Created by kingtakepc on 2016/9/20.
 */
public class DoubleCheckSingleton {
    private static DoubleCheckSingleton singleton;

    private DoubleCheckSingleton(){
        System.out.println("DoubleCheckSingleton construct!");
    }

    /**
     * 同步代码块，双重检查锁
     * @return
     */
    public static DoubleCheckSingleton getInstance(){
        if(singleton == null){
            synchronized (DoubleCheckSingleton.class){
                if(singleton == null){
                    singleton = new DoubleCheckSingleton();
                }
            }
        }
        return singleton;
    }
}
