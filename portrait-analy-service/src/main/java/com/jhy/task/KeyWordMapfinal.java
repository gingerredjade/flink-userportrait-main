package com.jhy.task;

import com.jhy.entity.KeyWordEntity;
import com.jhy.util.HBaseUtils;
import com.jhy.util.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.*;

/**
 * KeyWordMapfinal
 * 		[计算IDF、TF-IDF，并将关键词存到HBase备份]
 *
 * Created by li on 2019/1/20.
 */

public class KeyWordMapfinal implements MapFunction<KeyWordEntity, KeyWordEntity> {

    private long totaldoucments = 0L;
    private long words;
    private String columnName;

    public KeyWordMapfinal(long totaldoucments, long words,String columnName){
        this.totaldoucments = totaldoucments;
        this.words = words;
        this.columnName = columnName;
    }

    @Override
    public KeyWordEntity map(KeyWordEntity keyWordEntity) throws Exception {
		// 定义存储TF-IDF的Map
    	Map<String,Double> tfidfmap = new HashMap<String,Double>();

    	// 用户ID
        String userid = keyWordEntity.getUserid();

		// 获取所有的TF
        Map<String,Double> tfmap = keyWordEntity.getTfmap();
        Set<Map.Entry<String,Double>> set = tfmap.entrySet();

        String tablename = "keyworddata";
        String famliyname ="baseinfo";
        String colum ="idfcount";

		// 遍历TF，计算每个词的IDF、TF-IDF
        for(Map.Entry<String,Double> entry:set){
            String word = entry.getKey();
            Double value = entry.getValue();

            String data = HBaseUtils.getdata(tablename,word,famliyname,colum);
            long viewcount = Long.valueOf(data);
            Double idf = Math.log(totaldoucments/viewcount+1);
            Double tfidf = value*idf;
            tfidfmap.put(word,tfidf);
        }
        LinkedHashMap<String,Double> resultfinal = MapUtils.sortMapByValue(tfidfmap);
        Set<Map.Entry<String,Double>> entryset = resultfinal.entrySet();
        List<String> finalword = new ArrayList<String>();
        int count = 1;
        for(Map.Entry<String,Double> mapentry:entryset){
            finalword.add(mapentry.getKey());
            count++;
            if(count>words){
                break;
            }
        }
        KeyWordEntity keyWordEntityfinal = new KeyWordEntity();
        keyWordEntityfinal.setUserid(userid);
        keyWordEntityfinal.setFinalkeyword(finalword);

        String keywordstring = "";
        for(String keyword:finalword){
            keywordstring += keyword+",";
        }
        if (StringUtils.isNotBlank(keywordstring)){
            String tablename1 = "userkeywordlabel";
            String rowkey1 = userid;
            String famliyname1 = "baseinfo";
            String colum1 = columnName;
            HBaseUtils.putdata(tablename1,rowkey1,famliyname1,colum1,keywordstring);
        }

        return keyWordEntityfinal;
    }
}
