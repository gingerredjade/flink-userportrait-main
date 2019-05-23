package com.jhy.map;

import com.jhy.entity.CarrierInfo;
import com.jhy.util.CarrierUtils;
import com.jhy.util.HBaseUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * 手机运营商标签Map
 *
 * Created by JHy on 2019/04/28
 */
public class CarrierMap implements MapFunction<String, CarrierInfo> {
    @Override
    public CarrierInfo map(String s) throws Exception {
        if(StringUtils.isBlank(s)){
            return null;
        }
        String[] userinfos = s.split(",");
        String userid = userinfos[0];
        String username = userinfos[1];
        String sex = userinfos[2];
        String telphone = userinfos[3];
        String email = userinfos[4];
        String age = userinfos[5];
        String registerTime = userinfos[6];
        String usetype = userinfos[7];          //'终端类型：0、pc端；1、移动端；2、小程序端'

        int carriertype = CarrierUtils.getCarrierByTel(telphone);
        String carriertypestring = carriertype==0?"未知运营商":carriertype==1?"移动用户":carriertype==2?"联通用户":"电信用户";

        // 将运营商类型信息存入HBase中
        String tablename = "userflaginfo";
        String rowkey = userid;
        String famliyname = "baseinfo";
        String colum = "carrierinfo";           //运营商
        HBaseUtils.putdata(tablename,rowkey,famliyname,colum,carriertypestring);
        CarrierInfo carrierInfo = new CarrierInfo();
        String groupfield = "carrierInfo=="+carriertype;
        carrierInfo.setCount(1L);
        carrierInfo.setCarrier(carriertypestring);
        carrierInfo.setGroupfield(groupfield);
        return carrierInfo;
    }
}
