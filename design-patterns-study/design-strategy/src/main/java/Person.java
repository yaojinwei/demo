/**
 * @author Yao.Jinwei
 * @date 2016/10/13 10:19
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Person {
    private TravelStrategy travelStrategy;

    public Person(TravelStrategy travelStrategy) {
        this.travelStrategy = travelStrategy;
    }

    public void travel(){
        System.out.println("我要去旅行");
        System.out.println("怎么去旅行呢？");
        travelStrategy.travel();
    }
}
