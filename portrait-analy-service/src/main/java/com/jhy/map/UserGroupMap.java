package com.jhy.map;

import com.jhy.entity.UserGroupInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户分群Map
 * 		[接收的日志信息就是订单信息,封装成用户分群实体]
 *
 * Created by JHy on 2019/5/16.
 */
public class UserGroupMap implements MapFunction<String, UserGroupInfo> {

    @Override
    public UserGroupInfo map(String s) throws Exception {
        // 1-- 参数校验
    	if(StringUtils.isBlank(s)){
            return null;
        }

    	// 2-- 获取参数信息
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

        // 3-- 封装UserGroupInfo对象
        UserGroupInfo userGroupInfo = new UserGroupInfo();
        userGroupInfo.setUserid(userid);
        userGroupInfo.setCreatetime(createtime);
        userGroupInfo.setAmount(amount);
        userGroupInfo.setPaytype(paytype);
        userGroupInfo.setPaytime(paytime);
        userGroupInfo.setPaystatus(paystatus);
        userGroupInfo.setCouponamount(couponamount);
        userGroupInfo.setTotalamount(totalamount);
        userGroupInfo.setRefundamount(refundamount);
        userGroupInfo.setCount(Long.valueOf(num));
        userGroupInfo.setProducttypeid(producttypeid);
        userGroupInfo.setGroupfield(userid+"==userGroupinfo");
        List<UserGroupInfo> list = new ArrayList<UserGroupInfo>();
        list.add(userGroupInfo);
        userGroupInfo.setList(list);

		// 4-- 返回UserGroupInfo对象
        return userGroupInfo;
    }
}
