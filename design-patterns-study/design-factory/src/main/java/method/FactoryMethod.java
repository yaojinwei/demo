package method;


/**
 * 工厂方法模式
 * 应用场景
 * 1、构造函数需要做大量的初始化工作
 * 2、对以后增加的子类可以方便的扩展
 * 3、增加子类不需要修改代码
 *
 * 在实际应用中，工厂方法用得比较多一些，而且是和动态类装入器组合在一起应用
 * Created by yaojinwei on 2016/9/24.
 */
public interface FactoryMethod {
    Sample create();
}
