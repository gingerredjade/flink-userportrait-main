package com.jhy.map;

import com.jhy.entity.EmailInfo;
import com.jhy.util.EmailUtils;
import com.jhy.util.HBaseUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * 邮件运营商标签Map
 *      来一条数据就把用户状态给它更新到库里
 * Created by JHy on 2019/04/25
 */
public class EmailMap implements MapFunction<String, EmailInfo> {
    @Override
    public EmailInfo map(String value) throws Exception {
        if(StringUtils.isBlank(value)){
            return null;
        }
        String[] userinfos = value.split(",");
        String userid = userinfos[0];
        String username = userinfos[1];
        String sex = userinfos[2];
        String telphone = userinfos[3];
        String email = userinfos[4];
        String age = userinfos[5];
        String registerTime = userinfos[6];
        String usetype = userinfos[7];                  //'终端类型：0、pc端；1、移动端；2、小程序端'

        String emailtype = EmailUtils.getEmailtypeBy(email);

        // 保存邮件运营商信息到HBase中
        String tablename = "userflaginfo";
        String rowkey = userid;
        String famliyname = "baseinfo";
        String colum = "emailinfo";                     //运营商
        HBaseUtils.putdata(tablename,rowkey,famliyname,colum,emailtype);
        EmailInfo emailInfo = new EmailInfo();
        String groupfield = "emailInfo=="+emailtype;
        emailInfo.setEmailtype(emailtype);
        emailInfo.setCount(1l);
        emailInfo.setGroupfield(groupfield);
        return emailInfo;
    }
}
