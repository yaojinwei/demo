package abstractor;

/**
 * 抽象工厂模式
 * 应对产品族，可以增加产品线，但无法新增产品
 * Created by yaojinwei on 2016/9/24.
 */
interface AbstractFactory {
    SampleA createSampleA();
    SampleB createSampleB();
}
