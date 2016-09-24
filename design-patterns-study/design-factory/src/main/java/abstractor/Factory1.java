package abstractor;

/**
 * 应对产品族，可以增加产品线，但无法新增产品
 * Created by yaojinwei on 2016/9/24.
 */
class Factory1 implements AbstractFactory{

    @Override
    public SampleA createSampleA() {
        return new SampleA1();
    }

    @Override
    public SampleB createSampleB() {
        return new SampleB1();
    }
}
