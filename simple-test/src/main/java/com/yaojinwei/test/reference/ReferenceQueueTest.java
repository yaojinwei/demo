package com.yaojinwei.test.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author jinwei.yjw
 * @date 2018/10/29 14:55
 * https://www.cnblogs.com/jabnih/p/6580665.html
 *
 */
public class ReferenceQueueTest {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个引用队列
        ReferenceQueue queue = new ReferenceQueue();
        // 创建虚引用，此时状态为Active，并且Reference.pending为空，当前Reference.queue = 上面创建的queue，并且next=null
        // 这里直接传字符串，gc后队列为空，怀疑为值对象存储在永久代导致的
        WeakReference reference = new WeakReference(new String("123"), queue);
        System.out.println(reference.get());
        // 当GC执行后，由于是虚引用，所以回收该object对象，并且置于pending上，此时reference的状态为PENDING
        System.gc();

        /* ReferenceHandler从pending中取下该元素，并且将该元素放入到queue中，此时Reference状态为ENQUEUED，Reference.queue = ReferenceENQUEUED */
        /* 当从queue里面取出该元素，则变为INACTIVE，Reference.queue = Reference.NULL */
        Reference reference1 = queue.remove(0);
        // 回收后这里输出null
        System.out.println(reference1.get());
    }
}
