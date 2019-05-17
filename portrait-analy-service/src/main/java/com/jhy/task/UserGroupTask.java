package com.jhy.task;

import com.jhy.entity.UserGroupInfo;
import com.jhy.kmeans.Cluster;
import com.jhy.kmeans.KMeansRunbyusergroup;
import com.jhy.kmeans.Point;
import com.jhy.map.KMeansFinalusergroupMap;
import com.jhy.map.UserGroupMap;
import com.jhy.map.UserGroupMapbyreduce;
import com.jhy.reduce.UserGroupInfoReduce;
import com.jhy.reduce.UserGroupbykmeansReduce;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;

import java.util.*;

/**
 * 用户分群任务类
 *
 * Created by JHy on 2019/5/15.
 */
public class UserGroupTask {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);

        // 1--1 获取运行时-set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // 1--2 获取运行时-make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        // 2-- 添加source-get input data
        DataSet<String> text = env.readTextFile(params.get("input"));

        // 3-- 定义算子
        DataSet<UserGroupInfo> mapresult = text.map(new UserGroupMap());
        DataSet<UserGroupInfo> reduceresult = mapresult.groupBy("groupfield").reduce(new UserGroupInfoReduce());
        // 定义Map算子，根据上步的Reduce结果计算用户分群指标
        DataSet<UserGroupInfo> mapbyreduceresult = reduceresult.map(new UserGroupMapbyreduce());
        // 定义Reduce算子，将数据转换成KMeans所需要的计算格式
        DataSet<ArrayList<Point>> finalresult =  mapbyreduceresult.groupBy("groupfield").reduceGroup(new UserGroupbykmeansReduce());

        try {
        	// 4-- 获取Reduce结果
            List<ArrayList<Point>> resultlist = finalresult.collect();
            ArrayList<float[]> dataSet = new ArrayList<float[]>();
            for(ArrayList<Point> array:resultlist){
                for(Point point:array){
                    dataSet.add(point.getlocalArray());
                }
            }

            // 5-- 对结果集数据进行训练
            // 5--1 根据结果集获取KMeansRunbyusergroup对象
            KMeansRunbyusergroup kMeansRunbyusergroup =new KMeansRunbyusergroup(6, dataSet);

            // 5--2 调用run方法进行训练
            Set<Cluster> clusterSet = kMeansRunbyusergroup.run();
            // 5--3 根据训练结果构造最终的中心点
            List<Point> finalClutercenter = new ArrayList<Point>();
            int count= 100;
            for(Cluster cluster:clusterSet){
                Point point = cluster.getCenter();
                point.setId(count++);
                finalClutercenter.add(point);
            }
            // 6-- 定义算子Map，为每个点Point打上它属于哪个中心点的标签
            DataSet<Point> flinalMap = mapbyreduceresult.map(new KMeansFinalusergroupMap(finalClutercenter));
			// 最终数据保存到HBase里
            // 7-- 启动程序
            env.execute("UserGroupTask analy");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
