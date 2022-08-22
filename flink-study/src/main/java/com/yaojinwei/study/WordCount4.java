package com.yaojinwei.study;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.utils.ParameterTool;
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
 *  flink -run  -c com.yaojinwei.study.WordCount2 --hostname 127.0.0.1 --port 8081
 * @author Yao Jinwei (jinwei.yjw@alibaba-inc.com)
 */
public class WordCount4 {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        // 指定端口
        //conf.setString(RestOptions.BIND_PORT, "8082-8089");
        ParameterTool parameterTool = ParameterTool.fromArgs(args);
        String hostname = parameterTool.get("hostname");
        int port = parameterTool.getInt("port");
        //指定 Flink Web UI 端口为9091
        configuration.setInteger("rest.port", 9091);
        //1.程序入口
        StreamExecutionEnvironment environmentWithWebUI
            = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(configuration);
        environmentWithWebUI.setParallelism(2);
        //2. 数据源
        DataStream<String> stringDataStream = environmentWithWebUI.socketTextStream(hostname, port);
        //3. ETL逻辑
        SingleOutputStreamOperator<Word> sum = stringDataStream
            .flatMap(new SplitTask())
            .keyBy("word1").sum("count")
            .setParallelism(2);

        sum.print();

        environmentWithWebUI.execute("job1");
    }

    public static class SplitTask implements FlatMapFunction<String, Word> {

        @Override
        public void flatMap(String value,  Collector<Word> out) {
            String[] values = value.split(",");
            for (String word : values) {
                out.collect(new Word(word, 1));
            }

        }
    }

    public static class Word {
        private String word1;
        private Integer count;
        //这个必须要有，否则会报 This type (GenericType<com.yaojinwei.study.WordCount2.Word>) cannot be used as key.
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
