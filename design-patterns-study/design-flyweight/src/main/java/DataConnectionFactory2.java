import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Apache连接池对象工厂
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/10
 */
public class DataConnectionFactory2 implements PooledObjectFactory<DataConnection> {

    @Override
    public PooledObject<DataConnection> makeObject() throws Exception {
        DataConnection connection = new DataConnection("ip=127.0.0.1");
        return new DefaultPooledObject<>(connection);
    }

    @Override
    public void destroyObject(PooledObject<DataConnection> p) throws Exception {
        p.getObject().close();
    }

    @Override
    public boolean validateObject(PooledObject<DataConnection> p) {
        return false;
    }

    @Override
    public void activateObject(PooledObject<DataConnection> p) throws Exception {
        p.getObject().open();
    }

    @Override
    public void passivateObject(PooledObject<DataConnection> p) throws Exception {

    }
}
