package advanced;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/8
 */
public class SmallCupCoffee extends Coffee {
    @Override
    public void pourCoffee() {
        System.out.println("冲了一小杯" + getMilkBehavior().add() + "咖啡");
    }
}
