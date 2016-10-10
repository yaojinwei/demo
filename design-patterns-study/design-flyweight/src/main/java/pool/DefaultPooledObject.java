package pool;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/10
 */
public class DefaultPooledObject<T> implements PooledObject<T> {
    private T object;

    private PooledObjectState state = PooledObjectState.IDLE; // @GuardedBy("this") to ensure transitions are valid
    private final long createTime = System.currentTimeMillis();
    private volatile long lastBorrowTime = createTime;
    private volatile long lastUseTime = createTime;
    private volatile long lastReturnTime = createTime;
    private volatile long borrowedCount = 0;

    public DefaultPooledObject(T object) {
        this.object = object;
    }

    public long getLastReturnTime() {
        return lastReturnTime;
    }

    public long getLastBorrowTime() {
        return lastBorrowTime;
    }

    public long getLastUseTime() {
        return lastUseTime;
    }

    public long getBorrowedCount() {
        return borrowedCount;
    }

    @Override
    public int compareTo(PooledObject<T> other) {
        return 0;
    }

    @Override
    public synchronized boolean allocate() {
        if(this.state == PooledObjectState.IDLE){
            state = PooledObjectState.ALLOCATED;
            lastBorrowTime = System.currentTimeMillis();
            lastUseTime = lastBorrowTime;
            borrowedCount++;
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean deallocate() {
        if(this.state == PooledObjectState.ALLOCATED ||
                state == PooledObjectState.RETURNING){
            state = PooledObjectState.IDLE;
            lastReturnTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    @Override
    public T getObject() {
        return object;
    }

    @Override
    public PooledObjectState getState() {
        return state;
    }

    @Override
    public void markRetruning() {
        this.state = PooledObjectState.RETURNING;
    }
}
