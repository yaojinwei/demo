package nodirector;


/**
 * 没有director的建造模式，这种方式建造过程可以任意扩展，其实就是工厂方法模式
 * Created by yaojinwei on 2016/9/24.
 */
public class ConcreateBuilder implements Builder{

    @Override
    public Product build() {
        Product product = new ConcreateProduct();
        return product;
        //建造各种部件

    }
}
