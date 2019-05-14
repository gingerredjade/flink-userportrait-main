package com.jhy.tfIdf;

import com.jhy.util.IkUtils;
import com.jhy.util.MapUtils;
import scala.tools.cmd.gen.AnyVals;

import java.io.*;
import java.util.*;

/**
 * Created by li on 2019/1/20.
 */
public class tfIdfAnaly {
    public static void main(String[] args) throws Exception {
        Map<String,Long> tfmap = new HashMap<String,Long>();
        Map<String,Map<String,Long>> documenttfmap = new HashMap<String,Map<String,Long>>();
        String filepath = "";
        File file = new File(filepath);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String temp = "";
        Set<String> wordset = new HashSet<String>();
        List<String> datalist = new ArrayList<String>();
        while((temp = bufferedReader.readLine())!= null){
            List<String> listword = IkUtils.getIkWord(temp);
            String docid = UUID.randomUUID().toString();
            datalist.add(temp);
            for(String inner : listword){
                tfmap = documenttfmap.get(docid)== null ?new HashMap<String,Long>():documenttfmap.get(docid);
                Long pre = tfmap.get(inner)==null?0l:tfmap.get(inner);
                tfmap.put(inner,pre+1);
                documenttfmap.put(docid,tfmap);
                wordset.add(inner);
            }
        }

        Map<String,Long> idfMap = new HashMap<String,Long>();
        for(String word :wordset){
            for(String tempdata:datalist){
                if(IkUtils.getIkWord(tempdata).contains(word)){
                    Long pre = idfMap.get(word)==null?0l:idfMap.get(word);
                    idfMap.put(word,pre+1);
                }
            }
        }

        int alldocumtnums = documenttfmap.keySet().size();
        Set<Map.Entry<String,Map<String,Long>>> set = documenttfmap.entrySet();
        for(Map.Entry<String,Map<String,Long>> entry:set){
            String documentid = entry.getKey();
            Map<String,Double> tfidfmap = new HashMap<String,Double>();
            Map<String,Long> tfmaptemp = entry.getValue();
            Collection<Long> collections = tfmaptemp.values();
            long sumtf = 0l;
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
