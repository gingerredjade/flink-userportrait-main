package com.jhy.map;

import com.jhy.entity.YearBase;
import com.jhy.util.DateUtils;
import com.jhy.util.HBaseUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * 年代标签Map
 *      来一条数据就把用户状态给它更新到库里
 * Created by JHy on 2019/04/25
 */
public class YearBaseMap implements MapFunction<String, YearBase> {

    @Override
    public YearBase map(String value) throws Exception {
        if (StringUtils.isBlank(value)) {
            return null;
        }

        /*
        userinfos数组格式：
        [userid,username,sex,telphone,email,age,registerTime,terminalType]
         */
        String[] userinfos = value.split(",");
        String userid = userinfos[0];               // 用户编号ID
        String username = userinfos[1];             // 用户名
        String sex = userinfos[2];                  // 性别
        String telphone = userinfos[3];             // 手机号
        String email = userinfos[4];                // 邮箱
        String age = userinfos[5];                  // 年龄
        String registerTime = userinfos[6];         // 注册时间
        String terminalType = userinfos[7];         // 终端类型：0-PC端 1-移动端 2-小程序端

        // 根据年龄判断年代
        String yearbasetype = DateUtils.getYearbaseByAge(age);

        // 将年代存入HBase中
        String tablename = "userflaginfo";
        String rowkey = userid;
        String familyname = "baseinfo";
        String colum = "yearbase";//年代
        HBaseUtils.putdata(tablename,rowkey,familyname,colum,yearbasetype);
        HBaseUtils.putdata(tablename,rowkey,familyname,"age",age);
        YearBase yearBase = new YearBase();
        String groupfield = "yearbase=="+yearbasetype; //
        yearBase.setYeartype(yearbasetype);
        yearBase.setCount(1L);
        yearBase.setGroupfield(groupfield);
        return yearBase;
    }
}
