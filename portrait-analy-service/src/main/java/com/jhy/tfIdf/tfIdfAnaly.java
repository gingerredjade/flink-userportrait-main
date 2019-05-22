package com.jhy.tfIdf;

import com.jhy.util.IkUtils;
import com.jhy.util.MapUtils;

import java.io.*;
import java.util.*;

/**
 * TF-IDF分析类
 *
 * Created by li on 2019/1/20.
 */
public class tfIdfAnaly {
    public static void main(String[] args) throws Exception {

    	// 1-- 准备计算每个词的TF
    	// 定义词频TF（String：词，Long：词出现的次数）
        Map<String,Long> tfmap = new HashMap<String,Long>();
        // 定义存储总的TF文档（String：TF文档编号，Map：TF文档）
		// 一条数据共用一个编号，对应有多个词频TF
        Map<String,Map<String,Long>> documenttfmap = new HashMap<String,Map<String,Long>>();

        String filepath = "";
        File file = new File(filepath);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String temp = "";
        // 分词集合
        Set<String> wordset = new HashSet<String>();
        // 行数据集
        List<String> datalist = new ArrayList<String>();
        // 逐行读取文件数据进行分词
        while((temp = bufferedReader.readLine())!= null){
        	// 调用IK分析工具类获取分词结果
            List<String> listword = IkUtils.getIkWord(temp);
			// 一条数据对应一个文档ID
            String docid = UUID.randomUUID().toString();
            datalist.add(temp);
            for(String inner : listword){
                tfmap = documenttfmap.get(docid)== null?new HashMap<String,Long>():documenttfmap.get(docid);
                Long pre = tfmap.get(inner)==null?0L:tfmap.get(inner);
                tfmap.put(inner,pre+1);
                documenttfmap.put(docid,tfmap);
                wordset.add(inner);
            }
        }

        // 2-- 准备计算每个词的IDF
		// 存储出现某词的文档总数
        Map<String,Long> idfMap = new HashMap<String,Long>();
        for(String word : wordset){
            for(String tempdata:datalist){
                if(IkUtils.getIkWord(tempdata).contains(word)){
                    Long pre = idfMap.get(word)==null?0L:idfMap.get(word);
                    idfMap.put(word,pre+1);
                }
            }
        }

        // 3-- 计算TF、IDF、TF-IDF
        // 所有文档数（一行数据一个编号，即总共有多少行数据）-语料库中的文件总数
        int alldocumtnums = documenttfmap.keySet().size();
        Set<Map.Entry<String,Map<String,Long>>> set = documenttfmap.entrySet();
        for(Map.Entry<String,Map<String,Long>> entry:set){
            String documentid = entry.getKey();
            Map<String,Double> tfidfmap = new HashMap<String,Double>();
            Map<String,Long> tfmaptemp = entry.getValue();
            Collection<Long> collections = tfmaptemp.values();
            long sumtf = 0L;
            for(Long templong:collections){
                sumtf+=templong;
            }
            Set<Map.Entry<String,Long>> tfentry = tfmaptemp.entrySet();
            for(Map.Entry<String,Long> entrytf:tfentry){
                String word = entrytf.getKey();
                long count = entrytf.getValue();
                double tf = Double.valueOf(count)/ Double.valueOf(sumtf);
                double idf = Math.log(Double.valueOf(alldocumtnums)/Double.valueOf(idfMap.get(word)));
                double tfIdf =tf*idf;
                tfidfmap.put(word,tfIdf);
            }

            LinkedHashMap<String, Double> sortedMap =  MapUtils.sortMapByValue(tfidfmap);
            Set<Map.Entry<String,Double>> setfinal = sortedMap.entrySet();
            int count =1;
            for (Map.Entry<String,Double> entryfinal:setfinal){
                if(count > 3){
                    break;
                }
                System.out.println(entryfinal.getKey());
                count ++;
            }



        }

    }
}
