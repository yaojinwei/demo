package pool;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/10
 */
public interface PooledObjectFactory<T> {
    PooledObject<T> makeObject() throws Exception;
    void activateObject(PooledObject<T> object) throws Exception;
    void destroyObject(PooledObject<T> object) throws Exception;
}
