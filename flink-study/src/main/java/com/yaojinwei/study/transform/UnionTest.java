package com.yaojinwei.study.transform;

import com.yaojinwei.study.source.NotParallelSource;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * @author Yao Jinwei (jinwei.yjw@alibaba-inc.com)
 */
public class UnionTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env
            = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Long> data1 = env.addSource(new NotParallelSource()).setParallelism(1);
        DataStreamSource<Long> data2 = env.addSource(new NotParallelSource()).setParallelism(1);
        // data1 和 data2合并到一起，数据类型必须一致
        DataStream<Long> union = data1.union(data2);

        SingleOutputStreamOperator<Long> result = union.map(new MapFunction<Long, Long>() {
            @Override
            public Long map(Long aLong) {
                System.out.println("接收到的数据：" + aLong);
                return aLong;
            }
        });

        SingleOutputStreamOperator<Long> sum = result.timeWindowAll(Time.seconds(2)).sum(0);
        sum.print().setParallelism(1);
        env.execute("UnionTest");
    }
}
