package advanced;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/8
 */
public abstract class Coffee {
    protected MilkBehavior milkBehavior;

    public MilkBehavior getMilkBehavior() {
        return MilkBehaviorSingleton.getMilkBehavior();
    }

    public void setMilkBehavior(MilkBehavior milkBehavior) {
        this.milkBehavior = milkBehavior;
    }

    public abstract void pourCoffee();
}
