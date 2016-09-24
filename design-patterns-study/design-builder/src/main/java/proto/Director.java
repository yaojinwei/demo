package proto;

/**
 * 这是导演，负责流程规范，在导演类中可以注入建造者对象。
 * Created by yaojinwei on 2016/9/24.
 */
public class Director {
    private Builder builder;
    // 构造方法中也可以传递builder
    public Director(Builder builder){
        this.builder = builder;
    }
    // 传递builder
    public void setBuilder(Builder builder) {
        this.builder = builder;
    }
    // 这个方法用来规范流程，产品构建和组装方法
    public void construct(){
        System.out.println("按照导演规定的流程开始建造");
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        System.out.println("产品建造完毕");
    }
}
