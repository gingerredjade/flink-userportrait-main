package com.jhy.controller;


import com.alibaba.fastjson.JSONObject;
import com.jhy.entity.ResultMessage;
import com.jhy.log.AttentionProductLog;
import com.jhy.log.BuyCartProductLog;
import com.jhy.log.CollectProductLog;
import com.jhy.log.ScanProductLog;
import com.jhy.utils.ReadProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by JHy on 2019/04/28.
 */
@RestController
@RequestMapping("infolog")
public class InfoInControl {

    private final String _propFileName = "jhy.properties";

    /**
     * 获取配置文件中定义的Kafka Topic
     */
    private final String attentionProductLogTopic = ReadProperties.getKey("attentionProductLog", _propFileName);
    private final String buyCartProductLogTopic = ReadProperties.getKey("buyCartProductLog", _propFileName);
    private final String collectProductLogTopic = ReadProperties.getKey("collectProductLog", _propFileName);
    private final String scanProductLogTopic = ReadProperties.getKey("scanProductLog", _propFileName);

    // 自动注入KafkaTemplate
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * REST请求测试接口
     * @param req
     * @return
     */
    @RequestMapping(value = "helloworld",method = RequestMethod.GET)
    public String helloworld(HttpServletRequest req){
        String ip = req.getRemoteAddr();
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setMessage("hello:"+ip);
        resultMessage.setStatus("success");
        String result = JSONObject.toJSONString(resultMessage);
        return result;
    }

    /**
     * 收集每种日志，包括：
     *      关注商品、购物车行为、收藏商品行为、浏览商品行为
     *  AttentionProductLog:{productid:productid....}
        BuyCartProductLog:{productid:productid....}
        CollectProductLog:{productid:productid....}
        ScanProductLog:{productid:productid....}
     * @param recevicelog
     * @param req
     * @return
     */
    @RequestMapping(value = "receivelog",method = RequestMethod.POST)
    public String hellowolrd(String recevicelog,HttpServletRequest req){
        if(StringUtils.isBlank(recevicelog)){
            return null;
        }
        String[] rearrays = recevicelog.split(":",2);
        String classname = rearrays[0];
        String data = rearrays[1];
        String resultmessage= "";

        if("AttentionProductLog".equals(classname)){
            /** 知识点记录
             * ①：JSONObject.parseObject将JSON字符串转化成对象时，会去填充名称相同的属性，对于JSON字符串中没有，而Test类有的属性，会为null；
             * ②：JSONObject.toJSONString将对象转化成JSON字符串时，对于JSON字符串有，但是Test类没有的，不做任何处理。
             */
            // 将指定的JSON字符串转换成自己的实体类的对象，其中data是“键值对”形式的JSON字符串
            AttentionProductLog attentionProductLog = JSONObject.parseObject(data,AttentionProductLog.class);
            // 将实体类对象转换成String类型的JSON字符串
            resultmessage = JSONObject.toJSONString(attentionProductLog);
            // 调用Kafka的Template发送消息到Kafka
            kafkaTemplate.send(attentionProductLogTopic,resultmessage+"##1##"+new Date().getTime());
        }else if("BuyCartProductLog".equals(classname)){
            BuyCartProductLog buyCartProductLog = JSONObject.parseObject(data, BuyCartProductLog.class);
            resultmessage = JSONObject.toJSONString(buyCartProductLog);
            kafkaTemplate.send(buyCartProductLogTopic,resultmessage+"##1##"+new Date().getTime());
        }else if("CollectProductLog".equals(classname)){
            CollectProductLog collectProductLog = JSONObject.parseObject(data, CollectProductLog.class);
            resultmessage = JSONObject.toJSONString(collectProductLog);
            kafkaTemplate.send(collectProductLogTopic,resultmessage+"##1##"+new Date().getTime());
        }else if("ScanProductLog".equals(classname)){
            ScanProductLog scanProductLog = JSONObject.parseObject(data,ScanProductLog.class);
            resultmessage = JSONObject.toJSONString(scanProductLog);
            kafkaTemplate.send(scanProductLogTopic,resultmessage+"##1##"+new Date().getTime());
        }
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setMessage(resultmessage);
        resultMessage.setStatus("success");
        String result = JSONObject.toJSONString(resultMessage);
        return result;
    }


}
