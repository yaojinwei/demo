import org.apache.commons.pool2.impl.GenericObjectPool;
import pool.GenericPool;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 享元模式
 * GenericPool模拟创建apache的连接池，没有做空闲回收，需要使用定时任务完成
 * 运行结果，可以看到创建对象的数量为DEFAULT_MAX_TOTAL，最后回收对象的数量为DEFAULT_MAX_TOTAL-DEFAULT_MAX_IDLE
 *
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/8
 */
public class Client {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        GenericPool<DataConnection> pool = new GenericPool<>(new DataConnectionFactory());
        
//        GenericObjectPool pool = new GenericObjectPool(new DataConnectionFactory2());

        for(int i = 0;i<100;i++){
            System.out.println(i);
            executorService.submit(new Task(pool, i));
        }
//        executorService.shutdown();

        new Scanner(System.in).nextLine();
    }

    static class Task implements Runnable{
        private GenericPool<DataConnection> pool;
        private int num;

        public Task(GenericPool<DataConnection> pool, int num) {
            this.pool = pool;
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println("thread num:" + num);
            DataConnection connection = null;
            try {
                connection = pool.borrowObject();
                System.out.println("the thread " + Thread.currentThread().getName() + ",current pool active num is " + pool.getNumActive());
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(connection != null){
//                connection.open();
            }
            else{
                throw new IllegalStateException("failed to borrow connection !");
//                System.err.print("failed to borrow connection");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            finally {
//                connection.close();
            try {
                if(null != connection){
                    System.out.println("the thread " + Thread.currentThread().getName() + " return object " );
                    pool.returnObject(connection);
                }
                System.out.println("the thread " + Thread.currentThread().getName() + ",after return object the pool active num is " + pool.getNumActive());
            } catch (Exception e) {
                e.printStackTrace();
            }
//            }
        }
    }
}
