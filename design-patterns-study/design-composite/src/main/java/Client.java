/**
 * 组合模式
 * 首先应该想到树形结构图。组合体内的这些对象有相同的接口，
 * 当组合体一个对象的方法被调用执行时，Composite将遍历（Iterator）整个树形1结构，寻找同样包含这个方法的对象并实现调用执行。
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/28
 */
public class Client {
    public static void main(String[] args) {
        Equipment cpu = new Equipment(1000);
        Equipment disk = new Equipment(500);
        Equipment ram = new Equipment(200);
        Equipment keyboard = new Equipment(150);

        //组装电脑，组装费100
        Equipment compute = new Equipment(100);
        compute.add(cpu);
        compute.add(disk);
        compute.add(ram);
        compute.add(keyboard);

        System.out.println("电脑总价格：" + compute.price());
    }
}
