package com.jhy.task;

import com.jhy.entity.KeyWordEntity;
import com.jhy.map.KeywordMap;
import com.jhy.map.KeywordMap2;
import com.jhy.reduce.KeyWordReduce2;
import com.jhy.reduce.KeywordReduce;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;

import java.util.*;

/**
 * 用户月度商品关键词任务类
 *
 * Created by li on 2019/1/6.
 */
public class MonthKeyWordTask {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);

        // 1-- 获取运行时  set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        // 2-- 添加source  get input data
        DataSet<String> text = env.readTextFile(params.get("input"));

		// 3-- 定义算子
		// 3--1 规整用户词集数据，把一个userid下的所有数据聚合到一起
        DataSet<KeyWordEntity> mapresult = text.map(new KeywordMap());
        DataSet<KeyWordEntity> reduceresutl = mapresult.groupBy("userid").reduce(new KeywordReduce());

		// 定义Map算子，计算TF
        DataSet<KeyWordEntity> mapresult2 = reduceresutl.map(new KeywordMap2());
        DataSet<KeyWordEntity> reduceresult2 = mapresult2.reduce(new KeyWordReduce2());
        Long totaldoucment = 0L;
        try {
			// 4-- 处理数据
			// 4--1 获取总文档数
            totaldoucment = reduceresult2.collect().get(0).getTotaldocumet();
			// 4--2 定义Map算子，计算IDF、TF-IDF，并存储到HDFS路径上
            DataSet<KeyWordEntity> mapfinalresult = mapresult.map(new KeyWordMapfinal(totaldoucment,3,"month"));
            mapfinalresult.writeAsText("hdfs://jhy/test/month");		// hdfs的路径

			// 5-- 启动程序
			env.execute("MonthrKeyWordTask analy");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
