package advanced;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/8
 */
public class WithoutMilkBehavior implements MilkBehavior {
    @Override
    public String add() {
        return "没有加奶，原味";
    }
}
