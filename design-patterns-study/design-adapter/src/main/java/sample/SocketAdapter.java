package sample;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/28
 */
public class SocketAdapter implements GBSocketInterface {

    private DBSocketInterface gbSocket;

    public SocketAdapter(DBSocketInterface gbSocket) {
        this.gbSocket = gbSocket;
    }

    /**
     * 将国标接口的调用对接到德标的接口上
     */
    @Override
    public void powerWithThreeFlat() {
        gbSocket.powerWithTwoRound();
    }
}
