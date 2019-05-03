package com.jhy.map;

import com.jhy.entity.BaiJiaInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * 败家指数标签Map
 *
 * Created by JHy on 2019/04/25
 */
public class BaiJiaMap implements MapFunction<String, BaiJiaInfo> {

    @Override
    public BaiJiaInfo map(String s) throws Exception {
        if(StringUtils.isBlank(s)){
            return null;
        }

        // 获取用户的订单信息
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

        // 把用户ID作为分组条件进行聚合，这里就不操作HBase了
        BaiJiaInfo baiJiaInfo = new BaiJiaInfo();
        baiJiaInfo.setUserid(userid);
        baiJiaInfo.setCreatetime(createtime);
        baiJiaInfo.setAmount(amount);
        baiJiaInfo.setPaytype(paytype);
        baiJiaInfo.setPaytime(paytime);
        baiJiaInfo.setPaystatus(paystatus);
        baiJiaInfo.setCouponamount(couponamount);
        baiJiaInfo.setTotalamount(totalamount);
        baiJiaInfo.setRefundamount(refundamount);
        String groupfield = "baijia=="+userid;
        baiJiaInfo.setGroupfield(groupfield);
        List<BaiJiaInfo> list = new ArrayList<BaiJiaInfo>();
        list.add(baiJiaInfo);
        return baiJiaInfo;
    }
}
