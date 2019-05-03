package com.jhy.reduce;

import com.jhy.entity.CarrierInfo;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * 手机运营商标签Reduce
 *      统计下这些用户群体的数量
 *
 * Created by JHy on 2019/04/28
 */
public class CarrierReduce implements ReduceFunction<CarrierInfo> {
    @Override
    public CarrierInfo reduce(CarrierInfo carrierInfo, CarrierInfo t1) throws Exception {
        String carrier = carrierInfo.getCarrier();
        Long count1 = carrierInfo.getCount();
        Long count2 = t1.getCount();

        CarrierInfo carrierInfoFinal = new CarrierInfo();
        carrierInfoFinal.setCarrier(carrier);
        carrierInfoFinal.setCount(count1+count2);

        return carrierInfoFinal;
    }
}
