package com.yaojinwei.study.state;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.shaded.guava18.com.google.common.collect.Lists;
import org.apache.flink.util.Collector;

/**
 * @author Yao Jinwei (jinwei.yjw@alibaba-inc.com)
 */
public class ListStateWithCountAvg extends RichFlatMapFunction<Tuple2<Long, Long>, Tuple2<Long, Double>> {
    private ListState<Tuple2<Long, Long>> listState;

    @Override
    public void open(Configuration parameters) throws Exception {
        ListStateDescriptor listStateDescriptor = new ListStateDescriptor<Tuple2<Long, Double>>(
            "avg",
            Types.TUPLE(Types.LONG, Types.DOUBLE)
        );

        listState = getRuntimeContext().getListState(listStateDescriptor);
        super.open(parameters);
    }

    @Override
    public void flatMap(Tuple2<Long, Long> value, Collector<Tuple2<Long, Double>> out) throws Exception {
        //listState.get();//获取状态的值

        //listState.add();//添加数据
        //listState.clear();//清空状态
        Iterable<Tuple2<Long, Long>> currentState = listState.get();

        if(currentState == null){
            //构建空的集合
            listState.addAll(Collections.emptyList());
        }

        //
        listState.add(value);
        ArrayList<Tuple2<Long, Long>> es = Lists.newArrayList(listState.get());
        if(es.size() == 3){
            long count = 0;
            double sum = 0;
            for(Tuple2<Long, Long> e : es){
                count ++;
                sum+= e.f1;
            }

            double avg = sum / count;
            out.collect(Tuple2.of(value.f0, avg));

            //清除状态
            listState.clear();
        }
    }
}
