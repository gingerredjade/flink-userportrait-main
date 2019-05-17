package com.jhy.map;

import com.alibaba.fastjson.JSONObject;
import com.jhy.entity.UserGroupInfo;
import com.jhy.kmeans.DistanceCompute;
import com.jhy.kmeans.Point;
import com.jhy.util.HBaseUtils;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * KMeansFinalusergroupMap
 * 		[该类负责给每个点Point打上它属于哪个中心点的标签,返回点，同时为用户打上用户分群标签]
 *
 * Created by JHy on 2019/5/16.
 */
public class KMeansFinalusergroupMap implements MapFunction<UserGroupInfo, Point>{

    private List<Point> centers = new ArrayList<Point>();
    private DistanceCompute disC = new DistanceCompute();

    public KMeansFinalusergroupMap(List<Point> centers){
            this.centers = centers;
    }
    @Override
    public Point map(UserGroupInfo userGroupInfo) throws Exception {
        // 1-- 构建当前点信息
    	float[] f = new float[]{Float.valueOf(userGroupInfo.getAvramout()+""),Float.valueOf(userGroupInfo.getMaxamout()+""),Float.valueOf(userGroupInfo.getDays()),
                Float.valueOf(userGroupInfo.getBuytype1()),Float.valueOf(userGroupInfo.getBuytype2()),Float.valueOf(userGroupInfo.getBuytype3()),
                Float.valueOf(userGroupInfo.getBuytime1()),Float.valueOf(userGroupInfo.getBuytime2()),Float.valueOf(userGroupInfo.getBuytime3()),
                Float.valueOf(userGroupInfo.getBuytime4())};
        Point self = new Point(Integer.valueOf(userGroupInfo.getUserid()),f);

        // 2-- 计算距离得出中心点信息
        float min_dis = Integer.MAX_VALUE;
        for (Point point : centers) {
            float tmp_dis = (float) Math.min(disC.getEuclideanDis(self, point), min_dis);
            if (tmp_dis != min_dis) {
                min_dis = tmp_dis;
                self.setClusterId(point.getId());
                self.setDist(min_dis);
                self.setClusterPoint(point);
            }
        }

        // 3-- 给用户打上用户群体标签
        String tablename = "userflaginfo";
        String rowkey = self.getId()+"";		// 用户ID
        String famliyname = "usergroupinfo";
        String colum = "usergroupinfo";			// 用户分群信息
        HBaseUtils.putdata(tablename,rowkey,famliyname,colum, JSONObject.toJSONString(self));

        return self;
    }
}
