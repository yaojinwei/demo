package com.yaojinwei.study.flatmap;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author Yao Jinwei (jinwei.yjw@alibaba-inc.com)
 */
public class FlatMapAndTimeWindowDemo {
    public static void main(String[] args) throws Exception {
        //1.程序入口
        StreamExecutionEnvironment environmentWithWebUI
            = StreamExecutionEnvironment.getExecutionEnvironment();
        //2. 数据源
        DataStream<String> stringDataStream = environmentWithWebUI.socketTextStream("127.0.0.1", 999);
        //3. ETL逻辑
        SingleOutputStreamOperator<Word> sum = stringDataStream
            .flatMap(new FlatMapFunction<String, Word>() {
                @Override
                public void flatMap(String line, Collector<Word> collector) throws Exception {
                    String[] split = line.split(",");
                    for (String word : split) {
                        collector.collect(new Word(word, 1));
                    }
                }
            })
            .keyBy("word").sum("count")
            .setParallelism(2);

        sum.print();

        environmentWithWebUI.execute("FlatMapAndTimeWindowDemo");
    }

    public static class Word {
        private String word;
        private Integer count;

        public Word() {
        }

        public Word(String word, Integer count) {
            this.word = word;
            this.count = count;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
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
                "word='" + word + '\'' +
                ", count=" + count +
                '}';
        }
    }
}
