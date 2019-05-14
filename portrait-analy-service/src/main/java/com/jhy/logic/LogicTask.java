package com.jhy.logic;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;

import java.util.*;

/**
 * 用Flink实现分布式逻辑回归算法
 *
 * Created by JHy on 2019/5/14.
 */
public class LogicTask {

	public static void main(String[] args) {
		final ParameterTool params = ParameterTool.fromArgs(args);

		// set up the execution environment
		final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

		// make parameters available in the web interface
		env.getConfig().setGlobalJobParameters(params);

		// get input data
		DataSet<String> text = env.readTextFile(params.get("input"));

		// 定义算子
		DataSet<LogicInfo> mapresult = text.map(new LogicMap());
		DataSet<ArrayList<Double>> reduceresult = mapresult.groupBy("groupbyfield").reduceGroup(new LogicReduce());
		try {
			// 获取权值结果集
			List<ArrayList<Double>> resultlist = reduceresult.collect();
			int groupsize  = resultlist.size();

			// Integer是下角标，Double是权值，需要排序，所以用TreeMap
			Map<Integer,Double> summap = new TreeMap<Integer,Double>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o1.compareTo(o2);
				}
			});

			// 遍历将结果相加
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

			// 启动程序
			env.execute("LogicTask analy");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
