/**
 * 具体装饰器角色:
 * 为组件建立一个标签（lable），需要传入lable的内容，以及原始的组件
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/8
 */
public class DecoratorLabeled extends Decorator {

    private String label;

    public DecoratorLabeled(Component component, String label) {
        super(component);
        this.label = label;
    }

    @Override
    public String paint() {
        //装饰前
        return "<b>"  + label + "：</b>" + super.paint();
        //装饰后
    }

}
