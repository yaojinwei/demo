/**
 * 饿汉式
 * http://blog.csdn.net/lg312200538/article/details/4930451
 *
 * Created by kingtakepc on 2016/9/20.
 */
public class StaticSingleton {
    private static StaticSingleton singleton = new StaticSingleton();

    private StaticSingleton(){
        System.out.println("StaticSingleton construct!");
    }

    public static StaticSingleton getInstance(){
        return singleton;
    }
}
