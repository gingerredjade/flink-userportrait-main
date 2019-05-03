package com.jhy.reduce;

import com.jhy.entity.BaiJiaInfo;
import org.apache.flink.api.common.functions.ReduceFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * 败家指数标签Reduce
 *
 *
 * Created by JHy on 2019/04/28
 */
public class BaiJiaReduce implements ReduceFunction<BaiJiaInfo> {

    @Override
    public BaiJiaInfo reduce(BaiJiaInfo baiJiaInfo, BaiJiaInfo t1) throws Exception {
        String userid = baiJiaInfo.getUserid();
        List<BaiJiaInfo> baijialist1 = baiJiaInfo.getList();
        List<BaiJiaInfo> baijialist2 = t1.getList();
        List<BaiJiaInfo> finallist = new ArrayList<BaiJiaInfo>();
        finallist.addAll(baijialist1);
        finallist.addAll(baijialist2);

        BaiJiaInfo baiJiaInfofinal = new BaiJiaInfo();
        baiJiaInfofinal.setUserid(userid);
        baiJiaInfofinal.setList(finallist);
        return baiJiaInfofinal;
    }
}
