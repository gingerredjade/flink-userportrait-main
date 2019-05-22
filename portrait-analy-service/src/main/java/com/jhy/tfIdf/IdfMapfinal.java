package com.jhy.tfIdf;

import com.jhy.util.HBaseUtils;
import com.jhy.util.MapUtils;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.*;

/**
 * IdfMapfinal
 * 		[计算IDF、TF-IDF]
 *
 * Created by li on 2019/1/20.
 */

public class IdfMapfinal implements MapFunction<TfIdfEntity, TfIdfEntity> {

    private long totaldoucments = 0L;
    private long words;
    public IdfMapfinal(long totaldoucments,long words){
        this.totaldoucments = totaldoucments;
        this.words =words;
    }

    @Override
    public TfIdfEntity map(TfIdfEntity tfIdfEntity) throws Exception {
    	// 定义存储TF-IDF的Map
        Map<String,Double> tfidfmap = new HashMap<String,Double>();

        // 文档ID
        String documentid = tfIdfEntity.getDocumentid();

        // 获取所有的TF
        Map<String,Double> tfmap = tfIdfEntity.getTfmap();
        Set<Map.Entry<String,Double>> set = tfmap.entrySet();

        String tablename = "tfidfdata";
        String rowkey = "word";
        String famliyname = "baseinfo";
        String colum ="idfcount";

        // 遍历TF,计算每个词的IDF、TF-IDF
        for(Map.Entry<String,Double> entry:set){
            String word = entry.getKey();
            Double value = entry.getValue();


            String data = HBaseUtils.getdata(tablename,rowkey,famliyname,colum);
            long viewcount = Long.valueOf(data);
            Double idf = Math.log(totaldoucments/viewcount+1);
            Double tfidf = value*idf;
            tfidfmap.put(word,tfidf);
        }
        LinkedHashMap<String,Double> resultfinal = MapUtils.sortMapByValue(tfidfmap);
        Set<Map.Entry<String,Double>> entryset = resultfinal.entrySet();
        List<String> finalword = new ArrayList<String>();
        int count =1;
        for(Map.Entry<String,Double> mapentry:entryset){
            finalword.add(mapentry.getKey());
            count++;
            if(count > words){
                break;
            }
        }

        TfIdfEntity tfIdfEntityfinal = new TfIdfEntity();
        tfIdfEntityfinal.setDocumentid(documentid);
        tfIdfEntityfinal.setFinalword(finalword);
        return tfIdfEntityfinal;
    }
}
