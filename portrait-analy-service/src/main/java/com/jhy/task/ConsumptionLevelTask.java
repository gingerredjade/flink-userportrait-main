package com.jhy.task;

import com.jhy.entity.ConsumptionLevel;
import com.jhy.map.CounsumptionLevelMap;
import com.jhy.reduce.ConsumptionLeaveFinalReduce;
import com.jhy.reduce.ConsumptionLevelReduce;
import com.jhy.util.MongoUtils;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.bson.Document;

import java.util.List;

/**
 * 消费水平标签任务类
 *
 * Created by JHy on 2019/5/17.
 */
public class ConsumptionLevelTask {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);

        // 1--获取运行时
        // set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        // 2-- 添加source    get input data
        DataSet<String> text = env.readTextFile(params.get("input"));

        // 3-- 定义算子
        DataSet<ConsumptionLevel> mapresult = text.map(new CounsumptionLevelMap());
        DataSet<ConsumptionLevel> reduceresult = mapresult.groupBy("groupfield").reduceGroup(new ConsumptionLevelReduce());
        DataSet<ConsumptionLevel> reduceresultfinal = reduceresult.groupBy("groupfield").reduce(new ConsumptionLeaveFinalReduce());
        try {
        	// 4-- 获取Reduce结果
            List<ConsumptionLevel> reusltlist = reduceresultfinal.collect();
            for(ConsumptionLevel consumptionLevel:reusltlist){
                String consumptiontype = consumptionLevel.getConsumptiontype();
                Long count = consumptionLevel.getCount();

                // 5-- 将结果数据存储到Mongo
                Document doc = MongoUtils.findoneby("consumptionlevelstatics","jhyPortrait",consumptiontype);
                if(doc == null){
                    doc = new Document();
                    doc.put("info",consumptiontype);
                    doc.put("count",count);
                }else{
                    Long countpre = doc.getLong("count");
                    Long total = countpre+count;
                    doc.put("count",total);
                }
                MongoUtils.saveorupdatemongo("consumptionlevelstatics","jhyPortrait",doc);
            }

            // 6-- 启动程序
            env.execute("ConsumptionLevelTask analy");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
