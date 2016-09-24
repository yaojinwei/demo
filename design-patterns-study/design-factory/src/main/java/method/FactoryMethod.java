package method;


/**
 * 工厂方法模式
 * 应用场景
 * 1、构造函数需要做大量的初始化工作
 * 2、对以后增加的子类可以方便的扩展
 * 3、增加子类不需要修改代码
 * Created by yaojinwei on 2016/9/24.
 */
public interface FactoryMethod {
    Sample create();
}
