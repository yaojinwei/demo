import java.util.Observable;
import java.util.Observer;

/**
 * @author Yao.Jinwei
 * @date 2016/10/12 15:26
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class PriceObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Float){
            System.out.println("PriceObserver:price changed to " + arg);
        }
    }
}
