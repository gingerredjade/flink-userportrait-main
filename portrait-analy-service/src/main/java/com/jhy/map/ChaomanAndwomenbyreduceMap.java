package com.jhy.map;

import com.alibaba.fastjson.JSONObject;
import com.jhy.entity.ChaomanAndWomenInfo;
import com.jhy.util.HBaseUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import java.util.*;

/**
 * 		[]
 * Created by JHy on 2019/5/17.
 */
public class ChaomanAndwomenbyreduceMap implements FlatMapFunction<ChaomanAndWomenInfo,ChaomanAndWomenInfo>  {

    @Override
    public void flatMap(ChaomanAndWomenInfo chaomanAndWomenInfo, Collector<ChaomanAndWomenInfo> collector) throws Exception {
        Map<String, Long> resultMap = new HashMap<String, Long>();
        String rowkey = "-1";
        if (rowkey.equals("-1")) {
            rowkey = chaomanAndWomenInfo.getUserid() + "";
        }
        // 1-- 根据潮男潮女信息的潮流类型构建对应的数量
		// 1--1 将当前潮男潮女类型及数量信息存入内存
        String chaotype = chaomanAndWomenInfo.getChaotype();
        Long count = chaomanAndWomenInfo.getCount();
        long pre = resultMap.get(chaotype) == null ? 0l : resultMap.get(chaotype);
        resultMap.put(chaotype, pre + count);

        String tablename = "userflaginfo";

        String famliyname = "userbehavior";
        String colum = "chaomanandwomen";
        String data = HBaseUtils.getdata(tablename, rowkey, famliyname, colum);
        // 1--2 将HBase中的潮男潮女类型及数量信息存入内存
        if (StringUtils.isNotBlank(data)) {
            Map<String, Long> datamap = JSONObject.parseObject(data, Map.class);
            Set<String> keys = resultMap.keySet();
            for (String key : keys) {
                Long pre1 = datamap.get(key) == null ? 0l : datamap.get(key);
                resultMap.put(key, pre1 + resultMap.get(key));
            }
        }

        // 2-- 将潮流类型及其数量存入HBase
        if (!resultMap.isEmpty()) {
        	// 2--1 潮流类型及其数量存入HBase
            String chaomandanwomenmap = JSONObject.toJSONString(resultMap);
            HBaseUtils.putdata(tablename, rowkey, famliyname, colum, chaomandanwomenmap);
            long chaoman = resultMap.get("1") == null ? 0l : resultMap.get("1");
            long chaowomen = resultMap.get("2") == null ? 0l : resultMap.get("2");
            String flag = "women";
            long finalcount = chaowomen;
            if (chaoman > chaowomen) {
                flag = "man";
                finalcount = chaoman;
            }
            // 2--2 设定一个阈值，若超过这个阈值就认为用户是潮男或潮女，并将潮流类型存入HBase
            if (finalcount > 2000) {
                colum = "chaotype";

                // 2--2-1 构建返回的潮男潮女实体信息
                ChaomanAndWomenInfo chaomanAndWomenInfotemp = new ChaomanAndWomenInfo();
                chaomanAndWomenInfotemp.setChaotype(flag);
                chaomanAndWomenInfotemp.setCount(1l);
                chaomanAndWomenInfotemp.setGroupbyfield(flag + "==chaomanAndWomenInforeduce");
                String type = HBaseUtils.getdata(tablename, rowkey, famliyname, colum);
                if (StringUtils.isNotBlank(type) && !type.equals(flag)) {
                    ChaomanAndWomenInfo chaomanAndWomenInfopre = new ChaomanAndWomenInfo();
                    chaomanAndWomenInfopre.setChaotype(type);
                    chaomanAndWomenInfopre.setCount(-1l);
                    chaomanAndWomenInfopre.setGroupbyfield(type + "==chaomanAndWomenInforeduce");
                    collector.collect(chaomanAndWomenInfopre);
                }

                // 2--2-2 潮男/潮女标识存入HBase
                HBaseUtils.putdata(tablename, rowkey, famliyname, colum, flag);
                collector.collect(chaomanAndWomenInfotemp);
            }

        }
    }

}
