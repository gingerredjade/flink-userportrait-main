package com.jhy.map;

import com.alibaba.fastjson.JSONObject;
import com.jhy.entity.BrandLike;
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
 * 用户品牌偏好标签Map
 * 		因为要发送多条数据，所以使用FlatMapFunction
 * 		输入是Kafka事件，输出是用户品牌偏好实体数据
 *
 * Created by JHy on 2019/05/13
 */
public class BrandLikeMap implements FlatMapFunction<KafkaEvent, BrandLike> {

	@Override
	public void flatMap(KafkaEvent kafkaEvent, Collector<BrandLike> collector) throws Exception {
		String data = kafkaEvent.getWord();
		ScanProductLog scanProductLog = JSONObject.parseObject(data, ScanProductLog.class);
		int userid = scanProductLog.getUserid();
		String brand = scanProductLog.getBrand();
		String tablename = "userflaginfo";
		String rowkey = userid+"";
		String famliyname = "userbehavior";
		String colum = "brandlist";				//运营
		String mapdata = HBaseUtils.getdata(tablename,rowkey,famliyname,colum);
		Map<String,Long> map = new HashMap<String,Long>();
		if(StringUtils.isNotBlank(mapdata)){
			map = JSONObject.parseObject(mapdata,Map.class);
		}
		// 获取之前的品牌偏好
		String maxprebrand = MapUtils.getmaxbyMap(map);

		// 1-- 存储用户所有喜欢的品牌
		long preparebrand = map.get(brand)==null?0l:map.get(brand);
		map.put(brand,preparebrand+1);
		String finalstring = JSONObject.toJSONString(map);
		HBaseUtils.putdata(tablename,rowkey,famliyname,colum,finalstring);

		// 2-- 存储用户最喜欢（浏览最多）的品牌（品牌偏好）
		String maxbrand = MapUtils.getmaxbyMap(map);

		// 2--1 若当前的品牌偏好和之前的品牌偏好不一致，则将原来偏好的品牌数量减1
		if (StringUtils.isNotBlank(maxbrand)&&!maxprebrand.equals(maxbrand)){
			BrandLike brandLike = new BrandLike();
			brandLike.setBrand(maxprebrand);
			brandLike.setCount(-1L);
			brandLike.setGroupbyfield("==brandlike=="+maxprebrand);
			collector.collect(brandLike);
		}

		// 2--2 存储当前的品牌偏好
		BrandLike brandLike = new BrandLike();
		brandLike.setBrand(maxbrand);
		brandLike.setCount(1L);
		collector.collect(brandLike);
		brandLike.setGroupbyfield("==brandlike=="+maxbrand);
		colum = "brandlike";
		HBaseUtils.putdata(tablename,rowkey,famliyname,colum,maxbrand);
	}
}
