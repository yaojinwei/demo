import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kingtakepc on 2016/9/20.
 */
public class Client {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //测试饿汉式单例
        for(int i=0;i<100;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    StaticSingleton staticSignleton = StaticSingleton.getInstance();
                }
            });
        }

        //测试懒汉式单例，不安全，会创建出来多个
        for(int i=0;i<100;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    SynchronizedSingleton synchronizedSingleton = SynchronizedSingleton.getInstance();
                }
            });
        }

        //测试懒汉式双检锁单例
        for(int i=0;i<100;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    DoubleCheckSingleton doubleCheckSingleton = DoubleCheckSingleton.getInstance();
                }
            });
        }
//        executorService.shutdown();

        //测试内部类单例
        for(int i=0;i<100;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    InnerClassSingleton inner = InnerClassSingleton.getInstance();
                }
            });
        }
        executorService.shutdown();
    }
}
