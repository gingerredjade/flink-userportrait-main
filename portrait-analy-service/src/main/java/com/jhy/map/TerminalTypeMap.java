package com.jhy.map;

import com.alibaba.fastjson.JSONObject;
import com.jhy.entity.TerminalTypeInfo;
import com.jhy.kafka.KafkaEvent;
import com.jhy.log.ScanProductLog;
import com.jhy.util.HBaseUtils;
import com.jhy.utils.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户终端偏好标签Map
 * 		因为数据要发送多份，故用FlatMapFunction
 * 		输入是Kafka事件，输出是用户品牌偏好实体数据
 *
 * Created by JHy on 2019/05/13
 */
public class TerminalTypeMap implements FlatMapFunction<KafkaEvent, TerminalTypeInfo> {

	@Override
	public void flatMap(KafkaEvent kafkaEvent, Collector<TerminalTypeInfo> collector) throws Exception {
		String data = kafkaEvent.getWord();
		ScanProductLog scanProductLog = JSONObject.parseObject(data,ScanProductLog.class);
		int userid = scanProductLog.getUserid();
		int terminaltype = scanProductLog.getTerminaltype();	//// 终端类型：0、pc端；1、移动端；2、小程序端
		String terminaltypename = terminaltype == 0?"pc端":terminaltype == 1?"移动端":"小程序端";
		String tablename = "userflaginfo";
		String rowkey = userid+"";
		String famliyname = "userbehavior";
		String colum = "terminaltypelist";				//运营
		String mapdata = HBaseUtils.getdata(tablename,rowkey,famliyname,colum);
		Map<String,Long> map = new HashMap<String,Long>();
		if(StringUtils.isNotBlank(mapdata)){
			map = JSONObject.parseObject(mapdata,Map.class);
		}
		// 获取之前的终端偏好
		String maxpreterminaltype = MapUtils.getmaxbyMap(map);

		// 1-- 存储用户所有喜欢的终端
		long preusetype = map.get(terminaltypename)==null?0l:map.get(terminaltypename);
		map.put(terminaltypename,preusetype+1);
		String finalstring = JSONObject.toJSONString(map);
		HBaseUtils.putdata(tablename,rowkey,famliyname,colum,finalstring);

		// 2-- 存储用户最喜欢（浏览最多）的终端（品牌偏好）
		String maxterminaltype = MapUtils.getmaxbyMap(map);

		// 2--1 若当前的终端偏好和之前的终端偏好不一致，则将原来偏好的终端数量减1
		if(StringUtils.isNotBlank(maxterminaltype)&&!maxpreterminaltype.equals(maxterminaltype)){
			TerminalTypeInfo terminalTypeInfo = new TerminalTypeInfo();
			terminalTypeInfo.setTerminaltype(maxpreterminaltype);
			terminalTypeInfo.setCount(-1l);
			terminalTypeInfo.setGroupbyfield("==terminaltypeinfo=="+maxpreterminaltype);
			collector.collect(terminalTypeInfo);
		}

		// 2--2 存储当前的终端偏好
		TerminalTypeInfo terminalTypeInfo = new TerminalTypeInfo();
		terminalTypeInfo.setTerminaltype(maxterminaltype);
		terminalTypeInfo.setCount(1l);
		terminalTypeInfo.setGroupbyfield("==terminaltypeinfo=="+maxterminaltype);
		collector.collect(terminalTypeInfo);
		colum = "terminaltype";
		HBaseUtils.putdata(tablename,rowkey,famliyname,colum,maxterminaltype);
	}
}
