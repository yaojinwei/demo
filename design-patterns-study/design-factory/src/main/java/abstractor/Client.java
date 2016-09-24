package abstractor;

/**
 * Created by yaojinwei on 2016/9/24.
 */
public class Client {
    public static void main(String[] args) {
        AbstractFactory factory = new Factory1();

        SampleA sampleA = factory.createSampleA();
        SampleB sampleB = factory.createSampleB();
        sampleA.operation1();
        sampleA.operation2();

        sampleB.operation1();
        sampleB.operation2();
    }
}
