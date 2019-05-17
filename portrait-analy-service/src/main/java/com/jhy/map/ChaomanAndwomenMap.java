package com.jhy.map;

import com.alibaba.fastjson.JSONObject;
import com.jhy.entity.ChaomanAndWomenInfo;
import com.jhy.kafka.KafkaEvent;
import com.jhy.log.ScanProductLog;
import com.jhy.utils.ReadProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.List;

/**
 * ChaomanAndwomenMap
 *		[接收Kafka数据构建潮男潮女实体]
 *
 * Created by JHy on 2019/5/17.
 */
public class ChaomanAndwomenMap implements FlatMapFunction<KafkaEvent, ChaomanAndWomenInfo>  {

    @Override
    public void flatMap(KafkaEvent kafkaEvent, Collector<ChaomanAndWomenInfo> collector) throws Exception {
            // 接收Kafka传来的数据
    		String data = kafkaEvent.getWord();

    		// 构建潮男潮女实体信息
            ScanProductLog scanProductLog = JSONObject.parseObject(data, ScanProductLog.class);
            int userid = scanProductLog.getUserid();
            int productid = scanProductLog.getProductid();
            ChaomanAndWomenInfo chaomanAndWomenInfo = new ChaomanAndWomenInfo();
            chaomanAndWomenInfo.setUserid(userid+"");

            // 根据商品ID判断是否是潮流商品-潮流男装/潮流女装
            String chaotype = ReadProperties.getKey(productid+"","productChaoLiudic.properties");
            // 为空表示非潮流商品
            if(StringUtils.isNotBlank(chaotype)){
                chaomanAndWomenInfo.setChaotype(chaotype);
                chaomanAndWomenInfo.setCount(1l);
                chaomanAndWomenInfo.setGroupbyfield("chaomanAndWomen=="+userid);	// 根据用户id分组
                List<ChaomanAndWomenInfo> list = new ArrayList<ChaomanAndWomenInfo>();
                list.add(chaomanAndWomenInfo);
                collector.collect(chaomanAndWomenInfo);
            }

    }

}
