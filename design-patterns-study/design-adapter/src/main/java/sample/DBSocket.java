package sample;

/**
 * 德国插座
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/28
 */
public class DBSocket implements DBSocketInterface {
    @Override
    public void powerWithTwoRound() {
        System.out.println("使用两项圆头的插孔供电");
    }
}
