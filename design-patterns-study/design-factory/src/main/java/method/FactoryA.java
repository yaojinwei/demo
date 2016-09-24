package method;

/**
 * Created by yaojinwei on 2016/9/24.
 */
public class FactoryA implements FactoryMethod{
    @Override
    public Sample create() {
        return new SampleA("aaa");
    }
}
