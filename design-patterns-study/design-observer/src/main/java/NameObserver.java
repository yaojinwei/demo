import java.util.Observable;
import java.util.Observer;

/**
 * @author Yao.Jinwei
 * @date 2016/10/12 15:25
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class NameObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String){
            System.out.println("NameObserver:name changed to " + arg);
        }
    }
}
