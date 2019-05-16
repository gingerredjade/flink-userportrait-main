package com.jhy.task;

import com.jhy.entity.SexPreInfo;
import com.jhy.map.SexPreMap;
import com.jhy.map.SexPresaveMap;
import com.jhy.reduce.SexPreReduce;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;

import java.util.*;

/**
 * Flink逻辑回归预测性别
 *
 * Created by li on 2019/1/6.
 */
public class SexPreTask {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);

        // 1--1 获取运行时-set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // 1--2 获取运行时-make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        // 2-- 添加source-get input data
        DataSet<String> text = env.readTextFile(params.get("input"));

        // 3-- 定义算子
        DataSet<SexPreInfo> mapresult = text.map(new SexPreMap());
        DataSet<ArrayList<Double>> reduceresult = mapresult.groupBy("groupfield").reduceGroup(new SexPreReduce());
        try {
        	// 4-- 处理Reduce结果--获取权值结果集
            List<ArrayList<Double>> resultlist = reduceresult.collect();
            int groupsize  = resultlist.size();

            // 4-- 处理Reduce结果--排序求平均值
			// Integer是下角标，Double是权值，需要排序，所以用TreeMap
            Map<Integer,Double> summap = new TreeMap<Integer,Double>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1.compareTo(o2);
                }
            });

			// 遍历将结果相加求平均权重值
            for(ArrayList<Double> array:resultlist){

                for(int i=0;i<array.size();i++){
                    double pre = summap.get(i)==null?0d:summap.get(i);
                    summap.put(i,pre+array.get(i));
                }
            }
            ArrayList<Double> finalweight = new ArrayList<Double>();
            Set<Map.Entry<Integer,Double>> set = summap.entrySet();
            for(Map.Entry<Integer,Double> mapentry :set){
                Integer key = mapentry.getKey();
                Double sumvalue = mapentry.getValue();
                double finalvalue = sumvalue/groupsize;
                finalweight.add(finalvalue);
            }

            // 5-- 根据weight进行性别预测
			// 5--1 添加source
            DataSet<String> text2 = env.readTextFile(params.get("input2"));
            // 5--2 定义算子
            text2.map(new SexPresaveMap(finalweight));

			// 6-- 启动程序
            env.execute("sexPreTask analy");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
