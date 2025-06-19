package com.yaojinwei.study.source;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author Yao Jinwei (jinwei.yjw@alibaba-inc.com)
 */
public class FlinkWithoutParallelSourceTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env
            = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Long> longDataStreamSource = env.addSource(new NotParallelSource());

        SingleOutputStreamOperator<Long> result = longDataStreamSource.filter(new FilterFunction<Long>() {
            @Override
            public boolean filter(Long aLong) throws Exception {
                System.out.println("接收到的数据:" + aLong);
                return aLong % 2 == 0;
            }
        });

        result.print().setParallelism(1);

        env.execute("FlinkWithoutParallelSourceTest");
    }
}
