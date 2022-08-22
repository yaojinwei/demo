package com.yaojinwei.study.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ActorHierarchyExperiments {
    public static void main(String[] args) {
        // 创建名为“testSystem”的ActorSytem
        ActorSystem system = ActorSystem.create("testSystem");
        // 打印actorSystem
        System.out.println("actroSytem:" + system);
        // 创建parentActor
        ActorRef actorRef = system.actorOf(Props.create(ParentActor.class), "parent");
        // 给parentActor发消息
        actorRef.tell("hello world", actorRef);
    }
}