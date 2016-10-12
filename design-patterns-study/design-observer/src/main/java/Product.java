import java.util.Observable;

/**
 * @author Yao.Jinwei
 * @date 2016/10/12 15:24
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Product extends Observable {

    private String name;
    private float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        synchronized (this){
            setChanged();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notifyObservers(name);
        }

    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
        synchronized (this){
            setChanged();
            notifyObservers(price);
        }

    }
}
