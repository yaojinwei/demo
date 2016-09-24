package simple;

/**
 * 简单工厂模式
 * 应用场景
 * 1、构造函数需要做大量的初始化工作
 * 2、对以后增加的子类可以方便的扩展
 *
 * Created by yaojinwei on 2016/9/24.
 */
public class Factory {
    public static Sample create(int which){
        // getClass 产生Sample 一般可使用动态类装载装入类，例如反射、
        if(which == 1){
            return new SampleA("aaa");
        }
        else if(which == 2){
            return new SampleB("bbb");
        }
        return null;
    }
}
