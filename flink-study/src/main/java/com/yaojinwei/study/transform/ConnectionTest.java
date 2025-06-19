package com.yaojinwei.study.transform;

import com.yaojinwei.study.source.NotParallelSource;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;

/**
 * @author Yao Jinwei (jinwei.yjw@alibaba-inc.com)
 */
public class ConnectionTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env
            = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Long> data1 = env.addSource(new NotParallelSource()).setParallelism(1);
        DataStreamSource<Long> data2 = env.addSource(new NotParallelSource()).setParallelism(1);
        // data1 和 data2合并到一起，数据类型必须一致

        SingleOutputStreamOperator<String> data2_s = data2.map(new MapFunction<Long, String>() {
            @Override
            public String map(Long aLong) throws Exception {
                return aLong + "s";
            }
        });

        ConnectedStreams<Long, String> connect = data1.connect(data2_s);

        /**
         *
         */
        SingleOutputStreamOperator<Object> map = connect.map(new CoMapFunction<Long, String, Object>() {
            @Override
            public Object map1(Long aLong) throws Exception {
                return aLong + " 10086";
            }

            @Override
            public Object map2(String s) throws Exception {
                return s + "map2";
            }
        });

        map.print();

        env.execute("ConnectionTest");
    }
}
