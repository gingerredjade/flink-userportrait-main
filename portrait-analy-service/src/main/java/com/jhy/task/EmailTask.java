package com.jhy.task;

import com.jhy.entity.EmailInfo;
import com.jhy.map.EmailMap;
import com.jhy.reduce.EmailReduce;
import com.jhy.util.MongoUtils;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.bson.Document;

import java.util.List;

/**
 * 邮件运营商标签任务类-给用户打上一个标签即该用户是属于哪个邮件运营商的用户
 *
 * Created by JHy on 2019/04/28
 */
public class EmailTask {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);

        // set up the execution environment创建一个Flink执行环境的上下文
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        // get input data
        DataSet<String> text = env.readTextFile(params.get("input"));

        DataSet<EmailInfo> mapresult = text.map(new EmailMap());
        DataSet<EmailInfo> reduceresult = mapresult.groupBy("groupfield").reduce(new EmailReduce());
        try {
            // 将统计的结果数量存到Mongo里
            List<EmailInfo> resultlist = reduceresult.collect();
            for(EmailInfo emailInfo:resultlist){
                String emailtype = emailInfo.getEmailtype();
                Long count = emailInfo.getCount();

                Document doc = MongoUtils.findoneby("emailstatics","jhyPortrait",emailtype);
                if(doc == null){
                    doc = new Document();
                    doc.put("info",emailtype);
                    doc.put("count",count);
                }else{
                    Long countpre = doc.getLong("count");
                    Long total = countpre+count;
                    doc.put("count",total);
                }
                MongoUtils.saveorupdatemongo("emailstatics","jhyPortrait",doc);
            }
            env.execute("email analy");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
