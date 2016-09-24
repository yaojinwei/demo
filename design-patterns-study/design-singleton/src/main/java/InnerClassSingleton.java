/**
 * 内部类
 * http://blog.csdn.net/lg312200538/article/details/4930451
 *
 * Created by yaojinwei on 2016/9/20.
 */
public class InnerClassSingleton {
    private InnerClassSingleton(){
        System.out.println("InnerClassSingleton construct!");
    }

    private static class SignletonHolder{
        private static InnerClassSingleton innerClassSingleton = new InnerClassSingleton();
    }

    public static InnerClassSingleton getInstance(){
        return SignletonHolder.innerClassSingleton;
    }
}
