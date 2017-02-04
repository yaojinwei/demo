package pool;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/10
 */
public interface Pool<T> {

    T borrowObject() throws Exception;
    void returnObject(T obj) throws Exception;
    public int getNumActive() ;
}
