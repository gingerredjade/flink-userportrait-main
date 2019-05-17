package com.jhy.reduce;

import com.jhy.entity.ConsumptionLevel;
import com.jhy.util.HBaseUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.util.Collector;

import java.util.Iterator;

/**
 * 消费水平Reduce
 * 		[接收数据-->统计消费金额-->判断消费水平-->打标签]
 *
 * Created by JHy on 2019/5/17.
 */
public class ConsumptionLevelReduce implements GroupReduceFunction<ConsumptionLevel,ConsumptionLevel> {

    @Override
    public void reduce(Iterable<ConsumptionLevel> iterable, Collector<ConsumptionLevel> collector) throws Exception {
        // 1-- 遍历输入输入，统计消费总金额
    	Iterator<ConsumptionLevel> iterator = iterable.iterator();
        int sum=0;
        double totalamount = 0d;
        String userid = "-1";
        while(iterator.hasNext()){
            ConsumptionLevel comsumptionLevel = iterator.next();
            userid = comsumptionLevel.getUserid();
            String amounttotal = comsumptionLevel.getAmounttotal();
            double amountdouble = Double.valueOf(amounttotal);
            totalamount += amountdouble;
            sum++;
        }

        // 2-- 计算平均消费，判断消费水平
        double avramout = totalamount/sum;	// 高消费5000 中等消费 1000 低消费 小于1000
        String flag = "low";
        if(avramout >=1000 && avramout <5000){
        	flag = "middle";
        }else if(avramout >= 5000){
            flag = "high";
        }

        // 3-- 构造输出数据
        String tablename = "userflaginfo";
        String rowkey = userid+"";
        String famliyname = "consumerinfo";
        String colum = "consumptionlevel";
        // 3--1 获取库里已有的消费水平标签值
        String data = HBaseUtils.getdata(tablename,rowkey,famliyname,colum);
        if(StringUtils.isBlank(data)){
        	// 3--2 HBase里消费水平为空，构造消费水平对象并返回
            ConsumptionLevel consumptionLevel = new ConsumptionLevel();
            consumptionLevel.setConsumptiontype(flag);
            consumptionLevel.setCount(1l);
            consumptionLevel.setGroupfield("==consumptionLevelfinal=="+flag);
            collector.collect(consumptionLevel);
        }else if(!data.equals(flag)){
        	// 3--3 判断HBase里之前的消费水平标签是否和现在的一致，不一致将之前的减掉，并设置新增的
            ConsumptionLevel consumptionLevel1 = new ConsumptionLevel();
			consumptionLevel1.setConsumptiontype(data);
			consumptionLevel1.setCount(-1l);
			consumptionLevel1.setGroupfield("==consumptionLevelfinal=="+data);

            ConsumptionLevel consumptionLevel2 = new ConsumptionLevel();
            consumptionLevel2.setConsumptiontype(flag);
            consumptionLevel2.setCount(1l);
			consumptionLevel2.setGroupfield("==consumptionLevelfinal=="+flag);
            collector.collect(consumptionLevel1);
            collector.collect(consumptionLevel2);
        }

        // 4-- 给用户打消费水平标签
        HBaseUtils.putdata(tablename,rowkey,famliyname,colum,flag);
    }
}
