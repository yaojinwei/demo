/**
 * 装饰器接口，维持一个指向组件对象的接口对象， 并定义一个与组件接口一致的接口
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/8
 */
public class Decorator implements Component {
    /**
     * 持有组件对象
     */
    protected Component component;

    /**
     * 构造方法，传入组件对象
     * @param component
     */
    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public String paint() {
        /**
         * 转发请求给组件对象，可以在转发前后做一些附件动作
         */
        return component.paint();
    }
}
