package com.yaojinwei.study.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

class ParentActor extends AbstractActor {
    @Override
    public Receive createReceive() {
           return receiveBuilder().matchAny(msg->{
            // 打印收到的消息
            System.out.println("parent receive message:"+msg);
            // 打印自己
            System.out.println("parentActor:"+getSelf());
            //  创建子Actor
            ActorRef childActor = getContext().actorOf(Props.create(ChildActor.class),"child");
            childActor.tell(msg,getSelf());
        }).build();
    }
}