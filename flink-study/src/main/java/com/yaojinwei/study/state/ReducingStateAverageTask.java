package com.yaojinwei.study.state;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * 需求：当接收到相同key 的元素个数等于3个或者超过3个的时候，就计算这些元素的value的平均值
 *
 * @author Yao Jinwei (jinwei.yjw@alibaba-inc.com)
 */
public class ReducingStateAverageTask {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Tuple2<Long, Long>> dataStream = env.fromElements(Tuple2.of(5L, 11L),
            Tuple2.of(5L, 8L), Tuple2.of(6L, 12L), Tuple2.of(6L, 10L), Tuple2.of(6L, 2L), Tuple2.of(5L, 1L), Tuple2.of(6L, 1L));

        dataStream
            .keyBy(0)
            .flatMap(new ReducingStateWithAvg()).print();

        env.execute("ValueStateAverageTask");
    }
}
