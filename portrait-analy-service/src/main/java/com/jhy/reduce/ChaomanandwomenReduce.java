package com.jhy.reduce;

import com.jhy.entity.ChaomanAndWomenInfo;
import org.apache.flink.api.common.functions.ReduceFunction;

import java.util.List;

/**
 * Created by li on 2019/1/6.
 */
public class ChaomanandwomenReduce implements ReduceFunction<ChaomanAndWomenInfo> {
    @Override
    public ChaomanAndWomenInfo reduce(ChaomanAndWomenInfo chaomanAndWomenInfo1, ChaomanAndWomenInfo chaomanAndWomenInfo2) throws Exception {
        String userid = chaomanAndWomenInfo1.getUserid();
        List<ChaomanAndWomenInfo> list1 = chaomanAndWomenInfo1.getList();

        List<ChaomanAndWomenInfo> list2 = chaomanAndWomenInfo2.getList();

        list1.addAll(list2);

        ChaomanAndWomenInfo chaomanAndWomenInfofinal = new ChaomanAndWomenInfo();
        chaomanAndWomenInfofinal.setUserid(userid);
        chaomanAndWomenInfofinal.setList(list1);

        return chaomanAndWomenInfofinal;
    }
}
