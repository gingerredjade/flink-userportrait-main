package com.jhy.reduce;

import com.jhy.entity.EmailInfo;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * 邮件运营商标签Reduce
 *      统计下这些用户群体的数量
 *
 * Created by JHy on 2019/04/28
 */
public class EmailReduce implements ReduceFunction<EmailInfo> {

    @Override
    public EmailInfo reduce(EmailInfo emailInfo, EmailInfo t1) throws Exception {
        String emailtype = emailInfo.getEmailtype();
        Long count1 = emailInfo.getCount();

        Long count2 = t1.getCount();

        EmailInfo emaiInfofinal = new EmailInfo();
        emaiInfofinal.setEmailtype(emailtype);
        emaiInfofinal.setCount(count1+count2);

        return emaiInfofinal;
    }
}
