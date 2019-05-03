package com.jhy.reduce;

import com.jhy.entity.YearBase;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * 年代标签Reduce
 *      统计下这些用户群体的数量
 *
 * Created by JHy on 2019/04/28
 */
public class YearBaseReduce implements ReduceFunction<YearBase> {
    @Override
    public YearBase reduce(YearBase yearBase, YearBase t1) throws Exception {
        String yeartype = yearBase.getYeartype();
        Long count1 = yearBase.getCount();

        Long count2 = t1.getCount();

        YearBase finalyearBase = new YearBase();
        finalyearBase.setYeartype(yeartype);
        finalyearBase.setCount(count1+count2);

        return finalyearBase;
    }
}
