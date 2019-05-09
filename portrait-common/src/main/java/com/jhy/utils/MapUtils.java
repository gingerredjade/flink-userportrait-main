package com.jhy.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by JHy on 2019/4/26.
 */
public class MapUtils {

    /**
     *
     * @param datamap
     * @return
     */
    public static String getmaxbyMap(Map<String,Long> datamap){
        if(datamap.isEmpty()){
                return  null;
        }
        TreeMap<Long,String> map = new TreeMap<Long, String>(new Comparator<Long>() {
            public int compare(Long o1, Long o2) {
                return o2.compareTo(o1);
            }
        });
        Set<Map.Entry<String,Long>> set = datamap.entrySet();
        for(Map.Entry<String,Long> entry :set){
            String key = entry.getKey();
            Long value = entry.getValue();
            map.put(value,key);
        }
        return map.get(map.firstKey());
    }
}
