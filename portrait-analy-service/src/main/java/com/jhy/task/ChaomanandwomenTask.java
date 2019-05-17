package com.jhy.task;

import com.jhy.entity.ChaomanAndWomenInfo;
import com.jhy.kafka.KafkaEvent;
import com.jhy.kafka.KafkaEventSchema;
import com.jhy.map.ChaomanAndwomenMap;
import com.jhy.map.ChaomanAndwomenbyreduceMap;
import com.jhy.reduce.ChaoManAndWomenSink;
import com.jhy.reduce.ChaomanandwomenReduce;
import com.jhy.reduce.ChaomanwomenfinalReduce;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;

import javax.annotation.Nullable;

/**
 * 潮男潮女任务类
 *		[根据数据实时计算是否是潮男/潮女]
 *
 * Created by JHy on 2019/5/17.
 */
public class ChaomanandwomenTask {
    public static void main(String[] args) {
        // 0-- 准备参数 parse input arguments
        args = new String[]{"--input-topic","scanProductLog","--bootstrap.servers","192.168.75.20:9092","--zookeeper.connect","192.168.75.20:2181","--group.id","jhy"};
        final ParameterTool parameterTool = ParameterTool.fromArgs(args);

//		if (parameterTool.getNumberOfParameters() < 5) {
//			System.out.println("Missing parameters!\n" +
//					"Usage: Kafka --input-topic <topic> --output-topic <topic> " +
//					"--bootstrap.servers <kafka brokers> " +
//					"--zookeeper.connect <zk quorum> --group.id <some id>");
//			return;
//		}

		// 1-- 获取运行时
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().disableSysoutLogging();
        env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(4, 10000));
        env.enableCheckpointing(5000); 								// create a checkpoint every 5 seconds
        env.getConfig().setGlobalJobParameters(parameterTool); 		// make parameters available in the web interface
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        // 2-- 添加source
        DataStream<KafkaEvent> input = env
                .addSource(
                        new FlinkKafkaConsumer010<>(
                                parameterTool.getRequired("input-topic"),
                                new KafkaEventSchema(),
                                parameterTool.getProperties())
                                .assignTimestampsAndWatermarks(new CustomWatermarkExtractor()));
        // 3-- 定义算子
        DataStream<ChaomanAndWomenInfo> chaomanAndWomenMap = input.flatMap(new ChaomanAndwomenMap());

        DataStream<ChaomanAndWomenInfo> chaomanAndWomenReduce = chaomanAndWomenMap.keyBy("groupbyfield").timeWindowAll(Time.seconds(2)).reduce(new ChaomanandwomenReduce()).flatMap(new ChaomanAndwomenbyreduceMap());
        DataStream<ChaomanAndWomenInfo> chaomanAndWomenReducefinal = chaomanAndWomenReduce.keyBy("groupbyfield").reduce(new ChaomanwomenfinalReduce());
        // 4-- 定义sink
        chaomanAndWomenReducefinal.addSink(new ChaoManAndWomenSink());
        try {
        	// 5-- 启动程序
            env.execute("ChaomanandwomenTask analy");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class CustomWatermarkExtractor implements AssignerWithPeriodicWatermarks<KafkaEvent> {

        private static final long serialVersionUID = -742759155861320823L;

        private long currentTimestamp = Long.MIN_VALUE;

        @Override
        public long extractTimestamp(KafkaEvent event, long previousElementTimestamp) {
            // the inputs are assumed to be of format (message,timestamp)
            this.currentTimestamp = event.getTimestamp();
            return event.getTimestamp();
        }

        @Nullable
        @Override
        public Watermark getCurrentWatermark() {
            return new Watermark(currentTimestamp == Long.MIN_VALUE ? Long.MIN_VALUE : currentTimestamp - 1);
        }
    }
}
