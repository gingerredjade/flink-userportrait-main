package com.jhy.kmeans;

import java.util.ArrayList;
import java.util.Set;

/**
 * Kmeans流程运行测试类
 * Created by JHy on 2019/5/15.
 */
public class Main {
 
    public static void main(String[] args) {
    	// 1-- 构建数据集，实际数据由文件读入，若数据不是数字的话，要对其做数据化、归一化变成数据格式然后就可以计算它了
        ArrayList<float[]> dataSet = new ArrayList<float[]>();
 
        dataSet.add(new float[] { 1, 2, 3 });
        dataSet.add(new float[] { 3, 3, 3 });
        dataSet.add(new float[] { 3, 4, 4});
        dataSet.add(new float[] { 5, 6, 5});
        dataSet.add(new float[] { 8, 9, 6});
        dataSet.add(new float[] { 4, 5, 4});
        dataSet.add(new float[] { 6, 4, 2});
        dataSet.add(new float[] { 3, 9, 7});
        dataSet.add(new float[] { 5, 9, 8});
        dataSet.add(new float[] { 4, 2, 10});
        dataSet.add(new float[] { 1, 9, 12});
        dataSet.add(new float[] { 7, 8, 112});
        dataSet.add(new float[] { 7, 8, 4});

        // 2-- 获取KMeansRun对象
        KMeansRun kRun =new KMeansRun(3, dataSet);

        // 3-- 调用run方法进行训练
        Set<Cluster> clusterSet = kRun.run();
        System.out.println("单次迭代运行次数："+kRun.getIterTimes());
        for (Cluster cluster : clusterSet) {
            System.out.println(cluster);
        }
    }
}
