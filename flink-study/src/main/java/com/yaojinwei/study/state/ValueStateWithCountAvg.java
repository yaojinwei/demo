package com.yaojinwei.study.state;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;

/**
 * @author Yao Jinwei (jinwei.yjw@alibaba-inc.com)
 */
public class ValueStateWithCountAvg extends RichFlatMapFunction<Tuple2<Long, Long>, Tuple2<Long, Double>> {
    private ValueState<Tuple2<Long, Double>> countAndSum ;
    @Override
    public void open(Configuration parameters) throws Exception {
        //自定义状态
        /**
         * state 里面存储的数据结构：Tuple2<Long, Double>
         *     Long：key出现的次数
         *     Double：相同key的值的累加
         */
        ValueStateDescriptor<Tuple2<Long, Double>> valueStateDescriptor = new ValueStateDescriptor<Tuple2<Long, Double>>(
            "avg",
            Types.TUPLE(Types.LONG, Types.DOUBLE)
        );
        countAndSum = getRuntimeContext().getState(valueStateDescriptor);

        //super.open(parameters);
    }

    @Override
    public void flatMap(Tuple2<Long, Long> value, Collector<Tuple2<Long, Double>> out) throws Exception {
        //操作状态 countAndSum
        //获取状态数据
        Tuple2<Long, Double> currentState = countAndSum.value();
        //如果状态还没有初始化，那么就初始化状态
        if(currentState == null){
            //第一个0，次数
            //第二个0，表示累计的值
            currentState = Tuple2.of(0L, 0D);
        }
        //更新状态中的元素的个数
        currentState.f0 += 1;

        //更新状态的累计值
        currentState.f1 += value.f1;

        //更新状态
        countAndSum.update(currentState);

        //是否出现了3次
        if(currentState.f0 >= 3){
            //如果满足条件就说明，key是有了3次了，那么就开始计算平均值
            double v = currentState.f1 / currentState.f0;
            out.collect(Tuple2.of(value.f0, v));

            //清空状态值
            countAndSum.clear();
        }
    }
}
