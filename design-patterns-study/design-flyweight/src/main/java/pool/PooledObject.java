package pool;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/10
 */
public interface PooledObject<T>  extends Comparable<PooledObject<T>>{
    @Override
    int compareTo(PooledObject<T> other);

    @Override
    boolean equals(Object obj);

    @Override
    int hashCode();

    boolean allocate();

    boolean deallocate();

    T getObject();

    PooledObjectState getState();

    void markRetruning();
}
