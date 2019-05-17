package com.jhy.reduce;

import com.jhy.entity.ChaomanAndWomenInfo;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * [统计潮流男装/潮流女装的数量]
 *
 * Created by JHy on 2019/5/17.
 */
public class ChaomanwomenfinalReduce implements ReduceFunction<ChaomanAndWomenInfo>{


    @Override
    public ChaomanAndWomenInfo reduce(ChaomanAndWomenInfo chaomanAndWomenInfo1, ChaomanAndWomenInfo chaomanAndWomenInfo2) throws Exception {
        String chaotype = chaomanAndWomenInfo1.getChaotype();

        long count1 = chaomanAndWomenInfo1.getCount();

        long count2 = chaomanAndWomenInfo2.getCount();

        ChaomanAndWomenInfo finalchao = new ChaomanAndWomenInfo();
        finalchao.setChaotype(chaotype);
        finalchao.setCount(count1+count2);

        return finalchao;
    }
}
