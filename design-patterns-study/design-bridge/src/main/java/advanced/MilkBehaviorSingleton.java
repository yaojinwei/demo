package advanced;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/8
 */
public class MilkBehaviorSingleton {
    private static MilkBehavior milkBehavior;

    public MilkBehaviorSingleton(MilkBehavior milkBehavior) {
        this.milkBehavior = milkBehavior;

    }

    public static MilkBehavior getMilkBehavior(){
        return milkBehavior;
    }
}
