package sample;

import sample.GBSocketInterface;

/**
 * 手机，需要使用国标的接口进行充电
 * 相当于适配器模式中的Client角色
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/9/28
 */
public class Phone {
    private GBSocketInterface gbSocket;

    public Phone(GBSocketInterface gbSocket) {
        this.gbSocket = gbSocket;
    }

    public void setGbSocket(GBSocketInterface gbSocket) {
        this.gbSocket = gbSocket;
    }

    /**
     * 充电
     */
    public void charge(){
        gbSocket.powerWithThreeFlat();
    }
}
