package com.jhy.task;

import com.jhy.entity.YearBase;
import com.jhy.map.YearBaseMap;
import com.jhy.reduce.YearBaseReduce;
import com.jhy.util.MongoUtils;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.bson.Document;

import java.util.List;

/**
 * 年代标签-年代群体数量统计任务类
 *
 * 【年代标签定义】
 * 年代：40年代 50年代 60年代 70年代 80年代 90年代 00后 10后
 * 统计每个年代群里的数量，做到近实时统计，每半小时会进行一次任务统计。
 *
 * Created by JHy on 2019/04/25
 */
public class YearBaseTask {

    public static void main(String[] args){
        // 配置项
        final ParameterTool params = ParameterTool.fromArgs(args);

        // 构建环境变量 set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // 设置配置make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        /**
        get input data
        通过读取数据产生DataSet对象
        通过input参数获取路径，这个路径是在HDFS上
        通过sqoop同步过来后，HDFS上就会有个路径，直接读取路径就可以读到相应的数据
         */
        DataSet<String> text = env.readTextFile(params.get("input"));

        YearBaseMap yearBaseMap = new YearBaseMap();
        DataSet<YearBase> mapresult =  text.map(yearBaseMap);
        DataSet<YearBase> reduceresult = mapresult.groupBy("groupfield").reduce(new YearBaseReduce());

        try {
            List<YearBase> resultlist = reduceresult.collect();
            /**
             * Flink结合Mongo保存年代群体数量(把统计数据放到Mongo里面)
             */
            for (YearBase yearBase:resultlist) {
                    String yeartype = yearBase.getYeartype();
                    Long count = yearBase.getCount();

                Document doc = MongoUtils.findoneby("yearbasestatics","jhyPortrait",yeartype);
                if(doc == null){
                    doc = new Document();
                    doc.put("info",yeartype);
                    doc.put("count",count);
                } else {
                    Long countpre = doc.getLong("count");
                    Long total = countpre+count;
                    doc.put("count",total);
                }
                MongoUtils.saveorupdatemongo("yearbasestatics","jhyPortrait",doc);
            }
            // 执行
            env.execute("year base analy");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
