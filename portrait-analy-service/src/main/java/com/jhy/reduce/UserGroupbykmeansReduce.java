package com.jhy.reduce;

import com.jhy.entity.UserGroupInfo;
import com.jhy.kmeans.Cluster;
import com.jhy.kmeans.KMeansRunbyusergroup;
import com.jhy.kmeans.Point;
import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * 用户分群Reduce
 * 		[将用户分群数据转换成KMeans所需要的计算格式]
 *
 * Created by JHy on 2019/5/16.
 */
public class UserGroupbykmeansReduce implements GroupReduceFunction<UserGroupInfo,ArrayList<Point>> {
    @Override
    public void reduce(Iterable<UserGroupInfo> iterable, Collector<ArrayList<Point>> collector) throws Exception {
        Iterator<UserGroupInfo> iterator = iterable.iterator();
        ArrayList<float[]> dataSet = new ArrayList<float[]>();

        // 1-- 构建各个维度的数据
        while(iterator.hasNext()){
            UserGroupInfo userGroupInfo = iterator.next();
            float[] f = new float[]{Float.valueOf(userGroupInfo.getUserid()+""),Float.valueOf(userGroupInfo.getAvramout()+""),Float.valueOf(userGroupInfo.getMaxamout()+""),Float.valueOf(userGroupInfo.getDays()),
                    Float.valueOf(userGroupInfo.getBuytype1()),Float.valueOf(userGroupInfo.getBuytype2()),Float.valueOf(userGroupInfo.getBuytype3()),
                    Float.valueOf(userGroupInfo.getBuytime1()),Float.valueOf(userGroupInfo.getBuytime2()),Float.valueOf(userGroupInfo.getBuytime3()),
                    Float.valueOf(userGroupInfo.getBuytime4())};
            dataSet.add(f);
        }

        // 2-- 根据结果集获取KMeansRunbyusergroup对象
        KMeansRunbyusergroup kMeansRunbyusergroup = new KMeansRunbyusergroup(6, dataSet);

        // 3-- 调用run方法进行训练
        Set<Cluster> clusterSet = kMeansRunbyusergroup.run();
        // 4-- 根据训练结果构造最终的中心点
        ArrayList<Point> arrayList = new ArrayList<Point>();
        for(Cluster cluster:clusterSet){
            arrayList.add(cluster.getCenter());
        }
        collector.collect(arrayList);
    }
}
