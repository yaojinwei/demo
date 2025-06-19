package com.yaojinwei.study.state;

import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ReducingState;
import org.apache.flink.api.common.state.ReducingStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;

/**
 * 需求：根据key
 * @author Yao Jinwei (jinwei.yjw@alibaba-inc.com)
 */
public class ReducingStateWithAvg extends RichFlatMapFunction<Tuple2<Long, Long>, Tuple2<Long, Long>> {

    private ReducingState<Long> reducingState;

    @Override
    public void open(Configuration parameters) throws Exception {
        ReducingStateDescriptor<Long> descriptor = new ReducingStateDescriptor<Long>(
            "ReducingAvg",
            new ReduceFunction<Long>() {
                @Override
                public Long reduce(Long value1, Long value2) throws Exception {
                    return value1+value2;
                }
            },
            Long.class
        );

        reducingState = getRuntimeContext().getReducingState(descriptor);

    }

    @Override
    public void flatMap(Tuple2<Long, Long> value, Collector<Tuple2<Long, Long>> out) throws Exception {
        reducingState.add(value.f1);

        out.collect(Tuple2.of(value.f0, reducingState.get()));


    }
}
