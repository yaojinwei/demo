package proto;

/**
 * 具体的建造者，最好是建造具体的产品，否则存在不确定性
 * Created by yaojinwei on 2016/9/24.
 */
public class ConcreteBuilder implements Builder  {
    private ConcreateProduct product;

    public ConcreteBuilder(){
        product = new ConcreateProduct();
    }

    @Override
    public void buildPartA() {
        PartA partA = new PartA();
        System.out.println("具体的工厂生产部件A");
        product.setPartA(partA);
    }

    @Override
    public void buildPartB() {
        PartB partB = new PartB();
        System.out.println("具体的工厂生产部件B");
        product.setPartB(partB);
    }

    @Override
    public void buildPartC() {
        PartC partC = new PartC();
        System.out.println("具体的工厂生产部件C");
        product.setPartC(partC);
    }

    @Override
    public Product getResult() {
        return this.product;
    }
}
