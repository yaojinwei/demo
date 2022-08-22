package com.yaojinwei.study.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */

public class SenderActor extends AbstractActor {

    private Logger logger = LoggerFactory.getLogger(SenderActor.class);


    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(
                String.class,
                s -> {
                    logger.info("Received String message: {}", s);
                })
            .matchAny(o -> logger.info("received unknown message"))
            .build();
    }

    public static Props props() {
        return Props.create(SenderActor.class).withDispatcher("online-sms-user-dispatcher").withRouter(
            new RoundRobinPool(8));
    }
}
