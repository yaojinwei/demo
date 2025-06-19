package com.yaojinwei.study.source;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

/**
 * @author Yao Jinwei (jinwei.yjw@alibaba-inc.com)
 */
public class NotParallelSource implements SourceFunction<Long> {

    private long num = 10L;

    private boolean isRunning = true;

    @Override
    public void run(SourceContext<Long> sourceContext) throws Exception {
        while(isRunning){
            //把数据写到下游
            sourceContext.collect(num);
            num++;
            Thread.sleep(1000);
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }
}
