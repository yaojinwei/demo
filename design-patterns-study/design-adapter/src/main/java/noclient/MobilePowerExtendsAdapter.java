package noclient;

/**
 * 适配器模式
 * 2种实现方式：
 * 1、通过继承方式实现；
 * 2、通过组合方式实现。
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/28
 */
public class MobilePowerExtendsAdapter extends Power implements Taget {

    @Override
    public int get10V() {
        int p = get220V();
        //将220V转换成10V
        System.out.println("通过继承方式实现Power的适配器，将220V转换为10V");
        return 10;
    }
}
