package com.yaojinwei.study.transform;

import java.util.ArrayList;
import java.util.List;

import com.yaojinwei.study.source.NotParallelSource;
import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SplitStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author Yao Jinwei (jinwei.yjw@alibaba-inc.com)
 */
public class SplitAndSelectDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env
            = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Long> data1 = env.addSource(new NotParallelSource()).setParallelism(1);

        SplitStream<Long> split = data1.split(new OutputSelector<Long>() {
            @Override
            public Iterable<String> select(Long aLong) {
                List<String> out = new ArrayList<>();
                if (aLong % 2 == 0) {
                    out.add("even");
                } else {
                    out.add("odd");
                }
                return out;
            }
        });

        DataStream<Long> odd = split.select("odd");

        odd.print();
        env.execute("SplitAndSelectDemo");
    }
}
