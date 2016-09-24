package method;

/**
 * Created by yaojinwei on 2016/9/24.
 */
public class FactoryB implements FactoryMethod{
    @Override
    public Sample create() {
        return new SampleB("bbb");
    }
}
