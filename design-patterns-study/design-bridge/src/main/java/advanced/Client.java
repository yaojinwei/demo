package advanced;

/**
 * 桥接模式--共享Implementor对象
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/8
 */
public class Client {
    public static void main(String[] args) {
        //共享一瓶奶
        MilkBehaviorSingleton milkBehaviorSingleton = new MilkBehaviorSingleton(new WithMilkBehavior());

        //先冲一大杯咖啡
        Coffee coffee = new LargeCupCoffee();
        coffee.pourCoffee();
        //再冲一小杯咖啡
        Coffee coffee1 = new SmallCupCoffee();
        coffee1.pourCoffee();


    }
}
