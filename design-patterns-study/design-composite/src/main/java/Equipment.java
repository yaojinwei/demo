import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/28
 */
public class Equipment {
    private List equipments = new ArrayList();

    private int price;

    public Equipment(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    void add(Equipment child){
        equipments.add(child);
    }
    void remove(Equipment child){
        equipments.remove(child);
    }

    public int price(){
        Iterator iterator = equipments.iterator();
        int p = 0;
        while(iterator.hasNext()){
            Equipment equipment = (Equipment)iterator.next();
            p += equipment.price();
        }
        return p + getPrice();
    }
}
