/**
 * 数据库连接模拟
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/10
 */
public class DataConnection {
    private String connectionString;

    public DataConnection(String connectionString) {
        this.connectionString = connectionString;
        System.out.println("create new connection, connection string is:" + connectionString);
    }

    public void open(){
        System.out.println("连接开启");
    }

    public void close(){
        System.out.println("连接关闭");
    }
}
