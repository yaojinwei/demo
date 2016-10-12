import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 观察者模式
 * 使用java自带的 Observable 、Observer 接口实现
 * setChanged 和 notifyObservers 如果不同步的话，会导致最新的通知有可能丢失，这在Observable的notifyObservers中有说明
 *
 * @author Yao.Jinwei
 * @date 2016/10/12 15:33
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Client {
    public static void main(String[] args) {
        final Product product = new Product();
        NameObserver nameObserver = new NameObserver();
        PriceObserver priceObserver = new PriceObserver();
        product.addObserver(nameObserver);
        product.addObserver(priceObserver);

//        product.setName("娃哈哈");
//        product.setPrice(1.256F);

        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            final int index = i;
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    product.setName("娃哈哈" + index);
                    product.setPrice(Float.parseFloat(index+""));
                }
            });
        }
    }
}
