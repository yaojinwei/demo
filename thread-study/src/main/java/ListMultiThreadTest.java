
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 1、ArrayList不是线程安全的
 * 2、CopyOnWriteList是线程安全的，但效率非常低
 * 3、使用Sychronized和ReentrantLock可以确保线程安全，在只有写的场景下效率接近，约为CopyOnWritList的10倍
 *    如果设置读写
 * CopyOnWriteList适合读远远大于写的场景，比如缓存
 *
 * CopyOnWriteArrayList的另一个使用案例是观察者设计模式。如果事件监听器由多个不同的线程添加和移除，那么使用CopyOnWriteArrayList将会使得正确性和简单性得以保证。
 *
 * 绝对安全的
 * ReadWriteLock>CopyOnWriteArrayList>Sychronized
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/27
 */
public class ListMultiThreadTest {
    private Logger logger = LoggerFactory.getLogger(ListMultiThreadTest.class);

    public static void main(String[] args) {
        ListMultiThreadTest listMultiThreadTest = new ListMultiThreadTest();
        listMultiThreadTest.testProtoArrayList();
        listMultiThreadTest.testVector();
        listMultiThreadTest.testCopyOnWriteArrayList();
        listMultiThreadTest.testSychronizedArrayList();
        listMultiThreadTest.testReadWriteLockList();
        listMultiThreadTest.testCollectionSychronizedList();
        System.out.println("The test is ended!");
    }

    public void testProtoArrayList(){
        ExecutorService executorService =  Executors.newFixedThreadPool(30);
        List<Future> futureList = new ArrayList<>();
        long start = System.currentTimeMillis();
        final ReadWriteLock lock = new ReentrantReadWriteLock();
        final List list = new ArrayList();
        for(int j=0;j<15;j++){
            Future future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        try {
                            list.add(Thread.currentThread().getId() * 10000 + i);
                        }
                        catch (Exception e){
                            logger.error(e.getMessage());
                        }
//                        logger.info("Current thread:" + Thread.currentThread() + " value:" + i);
                    }
                }
            });
            futureList.add(future);
        }

        for(int i=0;i<15;i++){
            Future future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10;j++){
                        Iterator iterator = list.iterator();
                        while(iterator.hasNext()){
                            try {
                                iterator.next();
                            }
                            catch (Throwable t){
                                logger.error("ConcurrentModificationException");
                                break;
                            }
                        }
                    }
                }
            });
            futureList.add(future);
        }

        logger.info("future list size:"  + futureList.size());
        executorService.shutdown();
        try {
            for(int i=0;i<futureList.size();i++){
                Future future = futureList.get(i);
                future.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        logger.info("total list size is:" + list.size());
        logger.info("total time is:" + (System.currentTimeMillis()-start));
        logger.info(" qps:" + (list.size()*1000)/(System.currentTimeMillis()-start));
        logger.info("ProtoArrayList test is ended!");
        logger.info("-----------------------------------------------------------------------");
    }

    public void testVector(){
        ExecutorService executorService =  Executors.newFixedThreadPool(30);
        List<Future> futureList = new ArrayList<>();
        long start = System.currentTimeMillis();
        final List list = new Vector();
        for(int j=0;j<15;j++){
            Future future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        list.add(Thread.currentThread().getId() * 10000 +i);
//                        logger.info("Current thread:" + Thread.currentThread() + " value:" + i);
                    }
                }
            });
            futureList.add(future);
        }

        for(int i=0;i<15;i++){
            Future future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10;j++){
                        Iterator iterator = list.iterator();
                        while(iterator.hasNext()){
                            try {
                                iterator.next();
                            }
                            catch (Throwable t){
                                logger.error("ConcurrentModificationException");
                                break;
                            }
                        }
                    }
                }
            });
            futureList.add(future);
        }

        logger.info("future list size:"  + futureList.size());
        executorService.shutdown();
        try {
            for(int i=0;i<futureList.size();i++){
                Future future = futureList.get(i);
                future.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        logger.info("total list size is:" + list.size());
        logger.info("total time is:" + (System.currentTimeMillis()-start));
        logger.info(" qps:" + (list.size()*1000)/(System.currentTimeMillis()-start));
        logger.info("Vector test is ended!");
        logger.info("-----------------------------------------------------------------------");
    }

    public void testCopyOnWriteArrayList(){
        ExecutorService executorService =  Executors.newFixedThreadPool(30);
        List<Future> futureList = new ArrayList<>();
        long start = System.currentTimeMillis();
        final List list = new CopyOnWriteArrayList();
        for(int j=0;j<15;j++){
            Future future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        list.add(Thread.currentThread().getId() * 10000 +i);
//                        logger.info("Current thread:" + Thread.currentThread() + " value:" + i);
                    }
                }
            });
            futureList.add(future);
        }

        for(int i=0;i<15;i++){
            Future future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10;j++){
                        Iterator iterator = list.iterator();
                        while(iterator.hasNext()){
                            try {
                                iterator.next();
                            }
                            catch (Throwable t){
                                logger.error("ConcurrentModificationException");
                                break;
                            }
                        }
                    }
                }
            });
            futureList.add(future);
        }

        logger.info("future list size:"  + futureList.size());
        executorService.shutdown();
        try {
            for(int i=0;i<futureList.size();i++){
                Future future = futureList.get(i);
                future.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        logger.info("total list size is:" + list.size());
        logger.info("total time is:" + (System.currentTimeMillis()-start));
        logger.info(" qps:" + (list.size()*1000)/(System.currentTimeMillis()-start));
        logger.info("CopyOnWriteArrayList test is ended!");
        logger.info("-----------------------------------------------------------------------");
    }


    public void testSychronizedArrayList(){
        ExecutorService executorService =  Executors.newFixedThreadPool(30);
        List<Future> futureList = new ArrayList<>();
        long start = System.currentTimeMillis();
        final List list = new ArrayList();
        for(int j=0;j<15;j++){
            Future future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        synchronized (list){
                            list.add(Thread.currentThread().getId() * 10000 +i);
                        }
//                        logger.info("Current thread:" + Thread.currentThread() + " value:" + i);
                    }
                }
            });
            futureList.add(future);
        }

        for(int i=0;i<15;i++){
            Future future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    synchronized (list){
                        for(int j=0;j<10;j++){
                            Iterator iterator = list.iterator();
                            while(iterator.hasNext()){
                                try {
                                    iterator.next();
                                }
                                catch (Throwable t){
                                    logger.error("ConcurrentModificationException");
                                    break;
                                }
                            }
                        }
                    }
                }
            });
            futureList.add(future);
        }

        logger.info("future list size:"  + futureList.size());
        executorService.shutdown();
        try {
            for(int i=0;i<futureList.size();i++){
                Future future = futureList.get(i);
                future.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        logger.info("total list size is:" + list.size());
        logger.info("total time is:" + (System.currentTimeMillis()-start));
        logger.info(" qps:" + (list.size()*1000)/(System.currentTimeMillis()-start));
        logger.info("SychronizedArrayList test is ended!");
        logger.info("-----------------------------------------------------------------------");
    }

    public void testReadWriteLockList(){
        ExecutorService executorService =  Executors.newFixedThreadPool(30);
        List<Future> futureList = new ArrayList<>();
        long start = System.currentTimeMillis();
        final ReadWriteLock lock = new ReentrantReadWriteLock();
        final List list = new ArrayList();
        for(int j=0;j<15;j++){
            Future future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        lock.writeLock().lock();
                        list.add(Thread.currentThread().getId() * 10000 +i);
                        lock.writeLock().unlock();
//                        logger.info("Current thread:" + Thread.currentThread() + " value:" + i);
                    }
                }
            });
            futureList.add(future);
        }

        for(int i=0;i<15;i++){
            Future future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10;j++){
                        lock.readLock().lock();
                        Iterator iterator = list.iterator();
                        while(iterator.hasNext()){
                            try {
                                iterator.next();
                            }
                            catch (Throwable t){
                                logger.error("ConcurrentModificationException");
                                break;
                            }
                        }
                        lock.readLock().unlock();
                    }
                }
            });
            futureList.add(future);
        }

        logger.info("future list size:"  + futureList.size());
        executorService.shutdown();
        try {
            for(int i=0;i<futureList.size();i++){
                Future future = futureList.get(i);
                future.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        logger.info("total list size is:" + list.size());
        logger.info("total time is:" + (System.currentTimeMillis()-start));
        logger.info(" qps:" + (list.size()*1000)/(System.currentTimeMillis()-start));
        logger.info("ReadWriteLockList test is ended!");
        logger.info("-----------------------------------------------------------------------");
//        new Scanner(System.in).nextLine();
    }

    public void testCollectionSychronizedList(){
        ExecutorService executorService =  Executors.newFixedThreadPool(30);
        List<Future> futureList = new ArrayList<>();
        long start = System.currentTimeMillis();
        final ReadWriteLock lock = new ReentrantReadWriteLock();
        final List list = Collections.synchronizedList(new ArrayList());
        for(int j=0;j<15;j++){
            Future future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        list.add(Thread.currentThread().getId() * 10000 +i);
//                        logger.info("Current thread:" + Thread.currentThread() + " value:" + i);
                    }
                }
            });
            futureList.add(future);
        }

        for(int i=0;i<15;i++){
            Future future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10;j++){
                        Iterator iterator = list.iterator();
                        while(iterator.hasNext()){
                            try {
                                iterator.next();
                            }
                            catch (Throwable t){
                                logger.error("ConcurrentModificationException");
                                break;
                            }
                        }
                    }
                }
            });
            futureList.add(future);
        }

        logger.info("future list size:"  + futureList.size());
        executorService.shutdown();
        try {
            for(int i=0;i<futureList.size();i++){
                Future future = futureList.get(i);
                future.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        logger.info("total list size is:" + list.size());
        logger.info("total time is:" + (System.currentTimeMillis()-start));
        logger.info(" qps:" + (list.size()*1000)/(System.currentTimeMillis()-start));
        logger.info("CollectionSychronizedList test is ended!");
        logger.info("-----------------------------------------------------------------------");
//        new Scanner(System.in).nextLine();
    }
}
