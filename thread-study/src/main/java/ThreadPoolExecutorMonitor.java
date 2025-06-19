import java.util.concurrent.*;

/**
 * @author jinwei.yjw
 * @date 2025/6/19
 * @description
 */
public class ThreadPoolExecutorMonitor {
    public static void main(String[] args) {

        ThreadPoolExecutor fastTriggerPool = new ThreadPoolExecutor(
                4,
                200,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "xxl-job, admin JobTriggerPoolHelper-fastTriggerPool-" + r.hashCode());
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(Thread.currentThread().getId() + ">>>>>>>>>>> xxl-job, fast trigger pool is full, rejected task. " +
                                        "Running: " +  executor.getPoolSize()
                                        + ", Active: " + executor.getActiveCount() + ", Queued:"  + executor.getQueue().size() + ", Completed: " + executor.getCompletedTaskCount()
                                        );

                    }
                });

        for(int i=0;i<10;i++){
            fastTriggerPool.submit((Runnable) () -> {
                System.out.println(">>>>>>>>>>> xxl-job, fast trigger pool run.");
                while(true){
                    int queueSize = fastTriggerPool.getQueue().size();
                    int activeCount = fastTriggerPool.getActiveCount();
                    int poolSize = fastTriggerPool.getPoolSize();
                    int queueCapacity = fastTriggerPool.getQueue().remainingCapacity() + queueSize;
                    double queueUsage = (double) queueSize / queueCapacity;
                    double activeUsage = (double) activeCount / poolSize;

                    System.out.println(Thread.currentThread().getId() + ">>>>>>>>>>> xxl-job, fast trigger pool status: " +
                            "Running: " +  poolSize
                            + ", Active: " + activeCount + ", Queued:"  + queueSize + ", Completed: " + fastTriggerPool.getCompletedTaskCount()
                            + ", QueueUsage:" + queueUsage + ", ActiveUsage:" + activeUsage
                    );
                }
            });
        }


        System.out.println("end========================================");
    }

}
