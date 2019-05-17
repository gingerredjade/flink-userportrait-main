package com.jhy.map;

import com.jhy.entity.ConsumptionLevel;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * 消费水平Map
 *
 * Created by JHy on 2019/5/17.
 */
public class CounsumptionLevelMap implements MapFunction<String,ConsumptionLevel>{
    @Override
    public ConsumptionLevel map(String s) throws Exception {
        // 1-- 参数检测
    	if(StringUtils.isBlank(s)){
            return null;
        }

    	// 2-- 参数解析
        String[] orderinfos = s.split(",");
        String id= orderinfos[0];
        String productid = orderinfos[1];
        String producttypeid = orderinfos[2];
        String createtime = orderinfos[3];
        String amount = orderinfos[4];
        String paytype = orderinfos[5];
        String paytime = orderinfos[6];
        String paystatus = orderinfos[7];
        String couponamount = orderinfos[8];
        String totalamount = orderinfos[9];
        String refundamount = orderinfos[10];
        String num = orderinfos[11];
        String userid = orderinfos[12];

		// 3-- 构造消费水平实体对象
        ConsumptionLevel consumptionLevel = new ConsumptionLevel();
        consumptionLevel.setUserid(userid);
        consumptionLevel.setAmounttotal(totalamount);
        consumptionLevel.setGroupfield("=== consumptionLevel=="+userid);

        return consumptionLevel;
    }
}
