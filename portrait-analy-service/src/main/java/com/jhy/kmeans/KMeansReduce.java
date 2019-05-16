package com.jhy.kmeans;

import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * KMeansReduce
 * 	[该类负责训练KMeans数据，输出所有簇的中心点集合]
 *
 * Created by JHy on 2019/5/16.
 */
public class KMeansReduce implements GroupReduceFunction<KMeans,ArrayList<Point>> {
    @Override
    public void reduce(Iterable<KMeans> iterable, Collector<ArrayList<Point>> collector) throws Exception {
    	// 1-- 迭代KMeans数据，构造数据集
        Iterator<KMeans> iterator = iterable.iterator();
        ArrayList<float[]> dataSet = new ArrayList<float[]>();
        while(iterator.hasNext()){
            KMeans kMeans = iterator.next();
            float[] f = new float[]{Float.valueOf(kMeans.getVariable1()),Float.valueOf(kMeans.getVariable2()),Float.valueOf(kMeans.getVariable3())};
            dataSet.add(f);
        }
		// 2-- 获取KMeansRun对象
        KMeansRun kRun =new KMeansRun(6, dataSet);

		// 3-- 调用run方法进行训练
        Set<Cluster> clusterSet = kRun.run();

        // 4-- 构造输出参数
        ArrayList<Point> arrayList = new ArrayList<Point>();
        for(Cluster cluster:clusterSet){
            arrayList.add(cluster.getCenter());
        }
        collector.collect(arrayList);
    }
}
