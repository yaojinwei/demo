package com.yaojinwei.study.source;

import java.util.ArrayList;
import java.util.List;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author Yao Jinwei (jinwei.yjw@alibaba-inc.com)
 */
public class Job1FromCollection {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env
            = StreamExecutionEnvironment.getExecutionEnvironment();
        
        List<String> data = new ArrayList<>();
        data.add("flink1");
        data.add("flink2");
        data.add("flink3");

        DataStreamSource<String> stringDataStreamSource = env.fromCollection(data)
            .setParallelism(1);

        SingleOutputStreamOperator<String> result = stringDataStreamSource.map(new MapFunction<String, String>() {
            @Override
            public String map(String s) throws Exception {
                return s + " map operator";
            }
        });

        result.print();

        env.execute("Job1FromCollection");
    }
}
