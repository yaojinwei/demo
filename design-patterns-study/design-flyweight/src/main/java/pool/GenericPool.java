package pool;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author yaojinwei<yjw0909@gmail.com>d
 * @since 2016/10/10
 */
public class GenericPool<T> implements ObjectPool<T>{

    private static final int DEFAULT_MAX_WAIT_MILLIS = 30000;

    private static final int DEFAULT_MIN_IDLE = 0;
    private static final int DEFAULT_MAX_IDLE = 8;
    private static final int DEFAULT_MAX_TOTAL = 20;

    public GenericPool(PooledObjectFactory<T> factory) {
        this.factory = factory;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    private PooledObject<T> create() throws Exception{
        int localMaxTotal = getMaxTotal();
        long newCreateCount = createCount.incrementAndGet();
        if(localMaxTotal > -1 && newCreateCount > localMaxTotal ||
                newCreateCount > Integer.MAX_VALUE){
            createCount.decrementAndGet();
            return null;
        }

        PooledObject<T> p= null;

        try {
            p = factory.makeObject();
        } catch (Exception e) {
            createCount.decrementAndGet();
            throw e;
        }

        allObjects.put(new IdentityWrapper(p.getObject()), p);
        return p;
    }

    @Override
    public T borrowObject() throws Exception{
        boolean create = false;
        PooledObject<T> p = null;
        while( p == null){
            p = idleObjects.poll();
            if(p == null){
                p = create();
                if(p != null){
                    create = true;
                }
            }

            if(p == null){
                if(maxWaitMillis < 0){
                    p = idleObjects.take();
                }
                else{
                    p = idleObjects.poll(maxWaitMillis, TimeUnit.MILLISECONDS);
//                    if(p != null){
//                        System.out.println("poll p");
//                    }
                }
            }

            if(p  == null){
                throw new NoSuchElementException(
                        "Timeout waiting for idle object");
            }

            if(!p.allocate()){
                p = null;
            }

            if(p != null){
                try{
                    factory.activateObject(p);
                }
                catch (Exception ex){
                    try{
                        detroy(p);
                    }
                    catch (Exception e1){

                    }
                    p = null;

                    if(create){
                        NoSuchElementException nsee = new NoSuchElementException("Unable to activate object");
                        nsee.initCause(ex);
                        throw nsee;
                    }
                }
            }
        }
//        System.out.println("return p");
        return p.getObject();
    }

    @Override
    public void returnObject(T obj) throws Exception{
        PooledObject<T> p = allObjects.get(new IdentityWrapper<T>(obj));
        if(p == null){
            throw new IllegalStateException(
                    "Returned object not currently part of this pool");
        }

        synchronized (p){
            PooledObjectState state = p.getState();

            if(state != PooledObjectState.ALLOCATED){
                throw new IllegalStateException(
                        "Object has already been returned to this pool or is invalid");
            }
            else{
                p.markRetruning();
            }
        }

        if (!p.deallocate()) {
            throw new IllegalStateException(
                    "Object has already been returned to this pool or is invalid");
        }

        int maxIdleSave =  getMaxIdle();

        if(maxIdleSave > -1 && maxIdleSave <= idleObjects.size()){
            detroy(p);
        }
        else{
            idleObjects.add(p);
//            System.out.println("idleObjects.put");
//            System.out.println(idleObjects.size());
        }

    }

    @Override
    public int getNumActive() {
//        System.out.println("getNumActive");
//        System.out.println(allObjects.size());
//        System.out.println(idleObjects.size());
        return allObjects.size() - idleObjects.size();
    }

    private void detroy(PooledObject<T> toDestroy) throws Exception {
        idleObjects.remove(toDestroy);
        allObjects.remove(new IdentityWrapper<T>(toDestroy.getObject()));
        try {
            factory.destroyObject(toDestroy);
        } finally {
            createCount.decrementAndGet();
        }
    }

    class IdentityWrapper<T>{
        private final T instance;

        public IdentityWrapper(T instance) {
            this.instance = instance;
        }

        @Override
        public int hashCode(){return System.identityHashCode(instance);}

        @Override
        public boolean equals(Object other){ return ((IdentityWrapper) other).instance == instance;}

        public T getObject(){return instance;}
    }

    // --- configuration attributes --------------------------------------------
    private volatile int maxWaitMillis = DEFAULT_MAX_WAIT_MILLIS;

    private volatile int minIdle = DEFAULT_MIN_IDLE;
    private volatile int maxIdle = DEFAULT_MAX_IDLE;
    private volatile int maxTotal = DEFAULT_MAX_TOTAL;
    private final PooledObjectFactory<T> factory;

    // --- internal attributes -------------------------------------------------
    private LinkedBlockingQueue<PooledObject<T>> idleObjects = new LinkedBlockingQueue<PooledObject<T>>();
    private Map<IdentityWrapper<T>, PooledObject<T>> allObjects = new ConcurrentHashMap<IdentityWrapper<T>, PooledObject<T>>();


    private AtomicLong createCount = new AtomicLong(0);
}
