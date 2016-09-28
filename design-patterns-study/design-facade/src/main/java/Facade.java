/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/28
 */
public class Facade {

    Service1 service1;
    Service2 service2;
    Service3 service3;

    public Facade() {
        service1 = new Service1Impl();
        service2 = new Service2Impl();
        service3 = new Service3Impl();
    }

    public void method1(){
        service1.method1();
        service2.method2();
    }

    public void method2(){
        service1.method1();
        service2.method3();
    }

    public void method3(){
        service1.method2();
        service2.method3();
    }
}
