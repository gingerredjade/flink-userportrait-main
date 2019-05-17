package com.jhy.reduce;

import com.jhy.entity.ConsumptionLevel;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * 消费水平Final Reduce
 * 		[统计消费水平对应的数量，返回新消费水平实体]
 *
 * Created by JHy on 2019/5/17.
 */
public class ConsumptionLeaveFinalReduce implements ReduceFunction<ConsumptionLevel>{
    @Override
    public ConsumptionLevel reduce(ConsumptionLevel consumptionLevel1, ConsumptionLevel consumptionLevel2) throws Exception {
        String consumptiontype = consumptionLevel1.getConsumptiontype();
        Long count1 = consumptionLevel1.getCount();

        Long count2 = consumptionLevel2.getCount();

        ConsumptionLevel consumptionLevel = new ConsumptionLevel();
        consumptionLevel.setConsumptiontype(consumptiontype);
        consumptionLevel.setCount(count1+count2);

        return consumptionLevel;
    }
}
