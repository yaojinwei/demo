import pool.DefaultPooledObject;
import pool.PooledObject;
import pool.PooledObjectFactory;

/**
 * DataConnection工厂
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/10
 */
public class DataConnectionFactory implements PooledObjectFactory<DataConnection>{
    @Override
    public PooledObject<DataConnection> makeObject() throws Exception {
        DataConnection connection = new DataConnection("ip=127.0.0.1");
        connection.open();
        return new DefaultPooledObject<>(connection);
    }

    @Override
    public void activateObject(PooledObject<DataConnection> object) throws Exception {
        System.out.println("activate object");
    }

    @Override
    public void destroyObject(PooledObject<DataConnection> object) throws Exception {
        object.getObject().close();
    }
}
