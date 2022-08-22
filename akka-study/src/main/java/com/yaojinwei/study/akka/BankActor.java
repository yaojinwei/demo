package com.yaojinwei.study.akka;

import java.util.concurrent.CountDownLatch;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.dispatch.OnComplete;
import akka.pattern.Patterns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;

/**
 * https://blog.csdn.net/zhangjikuan/article/details/76599357
 */
public class BankActor extends UntypedActor {
 
    private static final Logger log = LoggerFactory.getLogger(BankActor.class);
    private int count;
 
    @Override
    public void preStart() throws Exception, Exception {
        super.preStart();
        count = 0;
    }
 
    @Override
    public void onReceive(Object message) throws Throwable {
        log.info("thread:" + Thread.currentThread());
        // 可以使用枚举或者动态代理类来实现方法调用
        if (message instanceof Command) {
            Command cmd = (Command) message;
            switch (cmd) {
                case ADD:
                    log.info("Add 1 from {} to {}", count, ++count);
                    break;
                case MINUS:
                    log.info("Minus 1 from {} to {}", count, --count);
                    break;
                case GET:
                    log.info("Return current count " + getSender());
                    getSender().tell(count, this.getSelf());
                    break;
                default:
                    log.warn("UnSupport cmd: " + cmd);
            }
        } else {
            log.warn("Discard unknown message: {}", message);
        }
    }

    public enum Command{
        ADD,MINUS,GET
    }

    public static void main(String[] args) throws InterruptedException {
        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        final ActorRef actorRef = actorSystem.actorOf(Props.create(BankActor.class).withDispatcher("coupon-grant-dispatcher"), "bank-actor");

        //final ActorRef actorRef = actorSystem.actorOf(Props.create(BankActor.class), "bank-actor");

        CountDownLatch addCount = new CountDownLatch(20);
        CountDownLatch minusCount = new CountDownLatch(10);

        Thread addCountT = new Thread(new Runnable() {
            @Override
            public void run() {
                while (addCount.getCount() > 0) {
                    actorRef.tell(Command.ADD, null);
                    addCount.countDown();
                }
            }
        });

        Thread minusCountT = new Thread(new Runnable() {
            @Override
            public void run() {
                while (minusCount.getCount() > 0) {
                    actorRef.tell(Command.MINUS, null);
                    minusCount.countDown();
                }
            }
        });

        minusCountT.start();
        addCountT.start();
        minusCount.await();
        addCount.await();

        Future<Object> count = Patterns.ask(actorRef, Command.GET, 1000);
        count.onComplete(
            new OnComplete<Object>() {
                @Override
                public void onComplete(Throwable failure, Object success) throws Throwable {
                    if (failure != null) {
                        failure.printStackTrace();
                    } else {
                        log.info("Get result from " + success);
                    }
                }
            }, new ExecutionContext() {
                @Override
                public void execute(Runnable runnable) {
                    runnable.run();
                }

                @Override
                public void reportFailure(Throwable cause) {
                    cause.printStackTrace();
                }
            });
        //actorSystem.
    }
}