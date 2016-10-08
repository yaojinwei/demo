/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/8
 */
public class DecoratorInvalid extends Decorator {

    /**
     * 构造方法，传入组件对象
     *
     * @param component
     */
    public DecoratorInvalid(Component component) {
        super(component);
    }

    @Override
    public String paint() {
        //装饰前
        return "<span class='invalid'>"  + super.paint() + "</span>";
        //装饰后
    }
}
