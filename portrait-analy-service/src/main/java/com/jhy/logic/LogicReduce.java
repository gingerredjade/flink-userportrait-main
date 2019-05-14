package com.jhy.logic;

import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 逻辑回归算法Reduce
 *
 * Created by JHy on 2019/04/28
 */
public class LogicReduce implements GroupReduceFunction<LogicInfo, ArrayList<Double>> {

	@Override
	public void reduce(Iterable<LogicInfo> values, Collector<ArrayList<Double>> collector) throws Exception {
		Iterator<LogicInfo> iterator = values.iterator();

		// 1-- 定义训练数据集
		CreateDataSet trainingSet = new CreateDataSet();

		// 2-- 遍历构造数据集
		while(iterator.hasNext()){
			LogicInfo logicInfo = iterator.next();
			String variable1 = logicInfo.getVariable1();
			String variable2 = logicInfo.getVariable2();
			String variable3 = logicInfo.getVariable3();
			String label = logicInfo.getLabase();

			ArrayList<String> as = new ArrayList<String>();
			as.add(variable1);
			as.add(variable2);
			as.add(variable3);

			trainingSet.data.add(as);
			trainingSet.labels.add(label);
		}

		// 3-- 梯度下降法计算权值，并返回权值，numberIlter是训练次数
		ArrayList<Double> weights = new ArrayList<Double>();
		weights = Logistic.gradAscent1(trainingSet, trainingSet.labels, 500);
		collector.collect(weights);
	}
}
