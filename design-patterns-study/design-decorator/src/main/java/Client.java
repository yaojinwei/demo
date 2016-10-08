import java.util.HashMap;
import java.util.Map;

/**
 * 装饰者模式
 *   以对客户端透明的方式扩展对象的功能，是继承关系的一个替代方案；
 * 使用场景：
 *   1、需要在不影响其他对象的情况下，以动态、透明的方式给对象添加职责。
 *   2、如果不适合使用子类来进行扩展的时候，可以考虑使用装饰器模式。
 *   3、 Decorator模式采用对象组合而非继承的手法，实现了在运行时动态的扩展对象功能的能力，
 *   而且可以根据需要扩展多个功能，避免了单独使用继承带来的“灵活性差”和“多子类衍生问题”。
 *   同时它很好地符合面向对象设计原则中“优先使用对象组合而非继承”和“开放-封闭”原则。
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/8
 */
public class Client {
    public static void main(String[] args) {

        Component component = new TextInput("name", "yaojinwei");
        System.out.println("装饰前：");
        System.out.println(component.paint());

        //使用label装饰
        Decorator decorator = new DecoratorLabeled(component, "姓名");
        System.out.println("label装饰后：");
        System.out.println(decorator.paint());

        //使用无效样式进行装饰
        Decorator decorator1 = new DecoratorInvalid(decorator);
        System.out.println("invalid装饰后");
        System.out.println(decorator1.paint());

        System.out.println("------------批量构建组件-------------");
        //使用FormHandler构建组件
        Map map = new HashMap();
        map.put("firstName", "yao");
        map.put("lastName", ""); //invalid
        map.put("email", "yjw0909@qq.com");
        FormHandler formHandler = new FormHandler();
        Component[] components = formHandler.build(map);
        formHandler.validate(map, components);
        for(Component component1: components){
            System.out.println(component1.paint());
        }
    }
}
