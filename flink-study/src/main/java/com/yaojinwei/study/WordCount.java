package com.yaojinwei.study;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * 使用flink 实现词频统计
 *
 * @author Yao Jinwei (jinwei.yjw@alibaba-inc.com)
 */
public class WordCount {
    public static void main(String[] args) throws Exception {

        //1. 创建程序入口，无论是本地，还是集群模式都一样的
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        //2. 运行 nc -lk 999
        DataStream<String> stringDataStream = executionEnvironment.socketTextStream("127.0.0.1", 999);
        //指定数据源：socketTextStream
        //executionEnvironment.addSource()

        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = stringDataStream.flatMap(
            new FlatMapFunction<String, Tuple2<String, Integer>>() {
                @Override
                public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                    String[] values = value.split(",");
                    for (String word : values) {
                        out.collect(Tuple2.of(word, 1));
                    }
                }
            }).keyBy(0).sum(1);

        sum.print();

        executionEnvironment.execute("job1");
    }
}
