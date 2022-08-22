package com.yaojinwei.study.akka;

import akka.actor.AbstractActor;

class ChildActor extends AbstractActor {
      @Override
    public Receive createReceive() {
        return receiveBuilder().matchAny(msg->{
            // 打印收到的消息
            System.out.println("childActor receive message:"+msg);
            // 打印自己
            System.out.println("childActor:"+getSelf());
        }).build();
    }
}