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
 * Created by li on 2019/1/5.
 */
public class ConsumptionLevelTask {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);

        // set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        // get input data
        DataSet<String> text = env.readTextFile(params.get("input"));

        DataSet<ConsumptionLevel> mapresult = text.map(new CounsumptionLevelMap());
        DataSet<ConsumptionLevel> reduceresult = mapresult.groupBy("groupfield").reduceGroup(new ConsumptionLevelReduce());
        DataSet<ConsumptionLevel> reduceresultfinal = reduceresult.groupBy("groupfield").reduce(new ConsumptionLeaveFinalReduce());
        try {
            List<ConsumptionLevel> reusltlist = reduceresultfinal.collect();
            for(ConsumptionLevel consumptionLevel:reusltlist){
                String consumptiontype = consumptionLevel.getConsumptiontype();
                Long count = consumptionLevel.getCount();

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
            env.execute("ConsumptionLevelTask analy");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
