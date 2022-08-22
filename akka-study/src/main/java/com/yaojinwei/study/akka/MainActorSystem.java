package com.yaojinwei.study.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * https://www.jianshu.com/p/7d941a3b0ccb Akka框架简介
 * https://blog.csdn.net/lsx2017/article/details/113922074
 * @author Yao Jinwei (jinwei.yjw@alibaba-inc.com)
 */
public class MainActorSystem {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("sms-scheduler-online");
        ActorRef senderActor = actorSystem.actorOf(Props.create(SenderActor.class), "online-notify");
        senderActor.tell("123123", ActorRef.noSender());
        System.out.println("${actorSystem.settings.LogLevel}");
    }
}
