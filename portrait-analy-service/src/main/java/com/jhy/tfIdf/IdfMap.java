package com.jhy.tfIdf;

import com.jhy.entity.BaiJiaInfo;
import com.jhy.util.HBaseUtils;
import com.jhy.util.IkUtils;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.*;

/**
 * Created by li on 2019/1/20.
 */

/**
 * 一段文本
 */
public class IdfMap implements MapFunction<String, TfIdfEntity> {

    @Override
    public TfIdfEntity map(String s) throws Exception {
        Map<String,Long> tfmap = new HashMap<String,Long>();
        List<String> listdata = IkUtils.getIkWord(s);
        Set<String> wordset = new HashSet<String>();
        for(String word:listdata){
            Long pre = tfmap.get(word)==null?0l:tfmap.get(word);
            tfmap.put(word,pre+1);
            wordset.add(word);
        }
        String docuemtnid = UUID.randomUUID().toString();
        TfIdfEntity tfIdfEntity = new TfIdfEntity();
        tfIdfEntity.setDocumentid(docuemtnid);
        tfIdfEntity.setDatamap(tfmap);

        // 计算总数
        long sum = 0l;
        Collection<Long> longset = tfmap.values();
        for(Long templong:longset){
                sum += templong;
        }

        Map<String,Double> tfmapfinal = new HashMap<String,Double>();
        Set<Map.Entry<String,Long>> entryset = tfmap.entrySet();
        for(Map.Entry<String,Long> entry:entryset){
                String word = entry.getKey();
                long count = entry.getValue();
                double tf = Double.valueOf(count)/Double.valueOf(sum);
                tfmapfinal.put(word,tf);
        }
        tfIdfEntity.setTfmap(tfmapfinal);

        // create "tfidfdata,"baseinfo"
        for(String word:wordset){
            String tablename = "tfidfdata";
            String rowkey=word;
            String famliyname="baseinfo";
            String colum="idfcount";
            String data = HBaseUtils.getdata(tablename,rowkey,famliyname,colum);
            Long pre = data==null?0l:Long.valueOf(data);
            Long total = pre+1;
			HBaseUtils.putdata(tablename,rowkey,famliyname,colum,total+"");
        }
        return tfIdfEntity;
    }
}
