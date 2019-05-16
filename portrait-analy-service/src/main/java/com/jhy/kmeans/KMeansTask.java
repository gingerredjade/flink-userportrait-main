package com.jhy.kmeans;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;

import java.util.*;

/**
 * Created by li on 2019/1/6.
 */
public class KMeansTask {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);

        // 1-- 获取运行时 	set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // 2-- 设置参数 	make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        // 3-- 添加source   get input data
        DataSet<String> text = env.readTextFile(params.get("input"));

        // 4-- 定义算子
        DataSet<KMeans> mapresult = text.map(new KMeansMap());
        DataSet<ArrayList<Point>> reduceresutl = mapresult.groupBy("groupbyfield").reduceGroup(new KMeansReduce());
        try {
        	// 5-- 获取结果集
            List<ArrayList<Point>> reusltlist = reduceresutl.collect();
            ArrayList<float[]> dataSet = new ArrayList<float[]>();
            for(ArrayList<Point> array:reusltlist){
                for(Point point:array){
                    dataSet.add(point.getlocalArray());
                }
            }
			// 6-- 根据结果集获取KMeansRun对象
            KMeansRun kRun =new KMeansRun(6, dataSet);

            // 7-- 调用run方法进行训练
            Set<Cluster> clusterSet = kRun.run();

            // 8-- 构造最终的中心点
            List<Point> finalClustercenter = new ArrayList<Point>();
            int count= 100;
            for(Cluster cluster:clusterSet){
                Point point = cluster.getCenter();
                point.setId(count++);
				finalClustercenter.add(point);
            }

            // 10-- 定义算子Map，给每个点Point打上它属于哪个中心点的标签
            DataSet<Point> flinalMap = text.map(new KMeansFinalMap(finalClustercenter));
            flinalMap.writeAsText(params.get("out"));

            // 11-- 启动程序
            env.execute("LogicTask analy");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
