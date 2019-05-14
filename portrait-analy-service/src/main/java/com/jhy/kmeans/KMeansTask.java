package com.jhy.kmeans;

import com.youfan.logic.LogicInfo;
import com.youfan.logic.LogicMap;
import com.youfan.logic.LogicReduce;
import org.apache.flink.api.common.io.FileOutputFormat;
import org.apache.flink.api.common.io.OutputFormat;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;

import java.io.IOException;
import java.util.*;

/**
 * Created by li on 2019/1/6.
 */
public class KMeansTask {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);

        // set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        // get input data
        DataSet<String> text = env.readTextFile(params.get("input"));

        DataSet<KMeans> mapresult = text.map(new KMeansMap());
        DataSet<ArrayList<Point>> reduceresutl = mapresult.groupBy("groupbyfield").reduceGroup(new KMeansReduce());
        try {
            List<ArrayList<Point>> reusltlist = reduceresutl.collect();
            ArrayList<float[]> dataSet = new ArrayList<float[]>();
            for(ArrayList<Point> array:reusltlist){
                for(Point point:array){
                    dataSet.add(point.getlocalArray());
                }
            }
            KMeansRun kRun =new KMeansRun(6, dataSet);

            Set<Cluster> clusterSet = kRun.run();
            List<Point> finalClutercenter = new ArrayList<Point>();
            int count= 100;
            for(Cluster cluster:clusterSet){
                Point point = cluster.getCenter();
                point.setId(count++);
                finalClutercenter.add(point);
            }
            DataSet<Point> flinalMap = text.map(new KMeansFinalMap(finalClutercenter));
            flinalMap.writeAsText(params.get("out"));
            env.execute("LogicTask analy");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
