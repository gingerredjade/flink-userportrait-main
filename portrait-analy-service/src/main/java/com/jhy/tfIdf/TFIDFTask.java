package com.jhy.tfIdf;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;

/**
 * TF-IDF分析任务类
 * 		批处理-[读到数据，借助HBase计算每个值的TF-IDF]
 *
 * Created by JHy on 2019/5/22.
 */
public class TFIDFTask {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);

        // 1-- 获取运行时   set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        // 2-- 添加source   get input data
        DataSet<String> text = env.readTextFile(params.get("input"));

        // 3-- 定义算子
		// 计算TF
        DataSet<TfIdfEntity> mapresult = text.map(new IdfMap());
        // 统计总文档数
        DataSet<TfIdfEntity> reduceresult = mapresult.reduce(new IdfReduce());
        Long totaldocument = 0L;
        try {
        	// 4-- 处理数据
			// 4--1 获取总文档数
			totaldocument = reduceresult.collect().get(0).getTotaldocument();
            // 4--2 定义Map算子，计算IDF、TF-IDF，并存储到HDFS
            DataSet<TfIdfEntity> mapfinalresult = mapresult.map(new IdfMapfinal(totaldocument,3));
            mapfinalresult.writeAsText("hdfs://jhy/test");			// hdfs的路径

			// 5-- 启动程序
            env.execute("TF-IDFTask analy");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
