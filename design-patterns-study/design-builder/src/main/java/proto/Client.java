package proto;

/**
 * Created by yaojinwei on 2016/9/24.
 */
public class Client {
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        director.construct();
        Product product = builder.getResult();
    }
}
