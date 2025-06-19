package com.yaojinwei.study;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * 引入这个jar，程序运行的时候可以通过webUI
 * <dependency>
 * <groupId>org.apache.flink</groupId>
 * <artifactId>flink-runtime-web_2.11</artifactId>
 * <version>${flink.version}</version>
 * </dependency>
 *
 * @author Yao Jinwei (jinwei.yjw@alibaba-inc.com)
 */
public class WordCount8 {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        // 指定端口
        //conf.setString(RestOptions.BIND_PORT, "8082-8089");

        //指定 Flink Web UI 端口为9091
        configuration.setInteger("rest.port", 9091);
        //1.程序入口 无论是本地，还是集群，入口都是一样的
        StreamExecutionEnvironment environmentWithWebUI
            = StreamExecutionEnvironment.getExecutionEnvironment();
        environmentWithWebUI.setParallelism(2);
        //2. 数据源
        DataStream<String> stringDataStream = environmentWithWebUI.socketTextStream("30.79.193.100", 999);
        //3. ETL逻辑
        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = stringDataStream
            .flatMap(new SplitTask())
            .setParallelism(3)
            .keyBy(0)
            .sum(1)
            .setParallelism(2);

        sum.print();

        environmentWithWebUI.execute("job1");
    }

    public static class SplitTask implements FlatMapFunction<String, Tuple2<String, Integer>> {

        @Override
        public void flatMap(String value,  Collector<Tuple2<String, Integer>> out) {
            String[] values = value.split(",");
            for (String word : values) {
                out.collect(Tuple2.of(word, 1));
            }

        }
    }

    public static class Word {
        private String word1;
        private Integer count;

        public Word() {
        }

        public Word(String word, Integer count) {
            this.word1 = word;
            this.count = count;
        }

        public String getWord1() {
            return word1;
        }

        public void setWord1(String word1) {
            this.word1 = word1;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "Word{" +
                "word='" + word1 + '\'' +
                ", count=" + count +
                '}';
        }
    }
}
