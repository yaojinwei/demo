import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/28
 */
public class Computer implements Equipment{
    private List equipments = new ArrayList();


    @Override
    public int price() {
        Iterator iterator = equipments.iterator();
        int p = 0;
        while(iterator.hasNext()){

        }
    }
}
