package com.jhy.reduce;

import com.jhy.entity.ConsumptionLevel;
import com.jhy.util.HBaseUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.util.Collector;

import java.util.Iterator;

/**
 * 消费水平Reduce
 * Created by li on 2019/1/5.
 */
public class ConsumptionLevelReduce implements GroupReduceFunction<ConsumptionLevel,ConsumptionLevel> {


    @Override
    public void reduce(Iterable<ConsumptionLevel> iterable, Collector<ConsumptionLevel> collector) throws Exception {
        Iterator<ConsumptionLevel> iterator = iterable.iterator();
        int sum=0;
        double totalamount = 0d;
        String userid = "-1";
        while(iterator.hasNext()){
            ConsumptionLevel comsumptionLevel = iterator.next();
            userid = comsumptionLevel.getUserid();
            String amounttotaol = comsumptionLevel.getAmounttotaol();
            double amoutndouble = Double.valueOf(amounttotaol);
            totalamount += amoutndouble;
            sum++;
        }
        double avramout = totalamount/sum;	// 高消费5000 中等消费 1000 低消费 小于1000
        String flag = "low";
        if(avramout >=1000 && avramout <5000){
                flag = "middle";
        }else if(avramout >= 5000){
            flag = "high";
        }

        String tablename = "userflaginfo";
        String rowkey = userid+"";
        String famliyname = "consumerinfo";
        String colum = "consumptionlevel";
        String data = HBaseUtils.getdata(tablename,rowkey,famliyname,colum);
        if(StringUtils.isBlank(data)){
            ConsumptionLevel consumptionLevel = new ConsumptionLevel();
            consumptionLevel.setConsumptiontype(flag);
            consumptionLevel.setCount(1l);
            consumptionLevel.setGroupfield("==consumptionLevelfinal=="+flag);
            collector.collect(consumptionLevel);
        }else if(!data.equals(flag)){
            ConsumptionLevel consumptionLevel = new ConsumptionLevel();
            consumptionLevel.setConsumptiontype(data);
            consumptionLevel.setCount(-1l);
            consumptionLevel.setGroupfield("==consumptionLevelfinal=="+data);

            ConsumptionLevel consumptionLevel2 = new ConsumptionLevel();
            consumptionLevel2.setConsumptiontype(flag);
            consumptionLevel2.setCount(1l);
            consumptionLevel.setGroupfield("==consumptionLevelfinal=="+flag);
            collector.collect(consumptionLevel);
            collector.collect(consumptionLevel2);
        }

        HBaseUtils.putdata(tablename,rowkey,famliyname,colum,flag);
    }
}
