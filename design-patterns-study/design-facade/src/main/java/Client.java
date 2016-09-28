/**
 * 为系统的一组接口提供一致的界面
 * 使用场景：
 * 1、当你要为一个复杂子系统提供一个简单接口时。子系统往往因为不断演化而变得越来越复杂。大多数模式使用时都会产生更多更小的类。
 * 2、外观模式就是实现代码重构以便达到“迪米特法则”要求的一个强有力的武器。
 * 要点：
 * 1、不定义新功能，新的行为的增加应该通过修改原有子系统类或增加新的子系统类来实现
 * 2、通常来讲，仅需要一个Facade对象，因此Facade对象通常属于Singleton模式。外观模式最大的缺点在于违背了“开闭原则”，当增加新的子系统或者移除子系统时需要修改外观类，可以通过引入抽象外观类在一定程度上解决该问题，客户端针对抽象外观类进行编程。
 *
 * https://my.oschina.net/u/1995545/blog/363373
 *
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/28
 */
public class Client {

    public static void main(String[] args) {
        Facade f = new Facade();
        f.method1();
        f.method2();
        f.method3();
    }
}
