package noclient;

/**
 * 适配器模式
 * 2种实现方式：
 * 1、通过继承方式实现；
 * 2、通过组合方式实现。
 * • Target
 — 定义Client使用的与特定领域相关的接口。
 • Client
 — 与符合Ta rg e t接口的对象协同。
 • Adaptee
 — 定义一个已经存在的接口，这个接口需要适配。
 • Adapter
 — 对Adaptee的接口与Target接口进行适配
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/28
 */
public class MobilePowerAdapter implements Taget {
    private Power power ;

    public MobilePowerAdapter() {
        power = new Power();
    }

    @Override
    public int get10V() {
        int p = power.get220V();
        //将220V转换成10V
        System.out.println("通过组合方式实现Power（Adaptee）的适配器，将220V转换为10V");
        return 10;
    }
}
