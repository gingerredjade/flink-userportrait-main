package com.jhy.map;

import com.jhy.entity.KeyWordEntity;
import com.jhy.util.HBaseUtils;
import com.jhy.util.IkUtils;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.*;

/**
 * KeywordMap2
 * 		[计算TF]
 *
 * Created by li on 2019/1/20.
 */

/**
 */
public class KeywordMap2 implements MapFunction<KeyWordEntity, KeyWordEntity> {

    @Override
    public KeyWordEntity map(KeyWordEntity keyWordEntity) throws Exception {
        // 得到原始词集数据
    	List<String> words = keyWordEntity.getOriginalwords();

		// 词频TF（String：词，Long：词出现的次数）
        Map<String,Long> tfmap = new HashMap<String,Long>();
        Set<String> wordset = new HashSet<String>();

        // 每个单词调用一次IK分词算法
        for(String outerword:words){
        	// 调用IK分析工具类获取分词结果
            List<String> listdata = IkUtils.getIkWord(outerword);
            // 遍历分词结果，填充该词及其出现的次数
            for(String word:listdata){
                Long pre = tfmap.get(word)==null?0L:tfmap.get(word);
                tfmap.put(word,pre+1);
                wordset.add(word);
            }
        }

        KeyWordEntity keyWordEntityfinal = new KeyWordEntity();
        String userid = keyWordEntity.getUserid();
        keyWordEntityfinal.setUserid(userid);
        keyWordEntityfinal.setDatamap(tfmap);

        // 计算总数-文章中所有词的个数
        long sum = 0L;
        Collection<Long> longset = tfmap.values();
        for(Long templong:longset){
            sum += templong;
        }

		// 计算每个词的TF并存储到TF map中
        Map<String,Double> tfmapfinal = new HashMap<String,Double>();
        Set<Map.Entry<String,Long>> entryset = tfmap.entrySet();
        for(Map.Entry<String,Long> entry:entryset){
            String word = entry.getKey();
            long count = entry.getValue();
            double tf = Double.valueOf(count)/Double.valueOf(sum);
            tfmapfinal.put(word,tf);
        }
        keyWordEntityfinal.setTfmap(tfmapfinal);

		// 存入HBase
        // create "keyworddata,"baseinfo"
        for(String word:wordset){
            String tablename = "keyworddata";
            String rowkey=word;
            String famliyname="baseinfo";
            String colum="idfcount";
            String data = HBaseUtils.getdata(tablename,rowkey,famliyname,colum);
            Long pre = data==null?0L:Long.valueOf(data);
            Long total = pre+1;
            HBaseUtils.putdata(tablename,rowkey,famliyname,colum,total+"");
        }
        return keyWordEntity;
    }
}
