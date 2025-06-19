package com.yaojinwei.study.state;

import java.util.ArrayList;
import java.util.UUID;

import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.MapState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.shaded.guava18.com.google.common.collect.Lists;
import org.apache.flink.util.Collector;

/**
 * 两种方法记录次数
 *
 * 1、value用字符串进行记录
 * map.put(10,"20,40,60")
 * 2、key用随机字符串
 * map.put("123123", 60)
 * @author Yao Jinwei (jinwei.yjw@alibaba-inc.com)
 */
public class MapStateWithCountAvg extends RichFlatMapFunction<Tuple2<Long, Long>, Tuple2<Long, Double>> {
    private MapState<String, Long> mapState;

    @Override
    public void open(Configuration parameters) throws Exception {
        MapStateDescriptor<String, Long> descriptor = new MapStateDescriptor<String, Long>(
            "mapStateAvg",
            String.class, Long.class
        ) ;
        mapState = getRuntimeContext().getMapState(descriptor);
    }

    @Override
    public void flatMap(Tuple2<Long, Long> value, Collector<Tuple2<Long, Double>> out) throws Exception {
        mapState.put(UUID.randomUUID().toString(), value.f1);

        ArrayList<Long> longs = Lists.newArrayList(mapState.values());
        if(longs.size() == 3){
            long count = 0;
            double sum = 0;
            for(Long ele : longs){
                count++;
                sum += ele;
            }
            double avg = sum / count;

            out.collect(Tuple2.of(value.f0, avg));

            //清除状态
            mapState.clear();
        }
    }
}
