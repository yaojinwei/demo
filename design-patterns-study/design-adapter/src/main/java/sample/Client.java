package sample;

/**
 * 适配器模式
 *
 * http://www.2cto.com/kf/201401/275535.html
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/28
 */
public class Client {
    public static void main(String[] args) {
        //德国旅馆提供德标接口
        DBSocketInterface dbSocketInterface = new DBSocket();
        //使用适配器转换为国标接口
        SocketAdapter socketAdapter = new SocketAdapter(dbSocketInterface);
        //手机插上适配器接口上
        Phone phone = new Phone(socketAdapter);
        //手机充电
        phone.charge();
    }
    public void invoke(){

    }
    protected class Inner{
        public void run(){
            invoke();
        }
    }
}
