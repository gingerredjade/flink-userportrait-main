package com.jhy.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Map工具类
 *
 * Created by JHy on 2019/5/13.
 */
public class MapUtils {

    /**
     * 获取最大value对应的key
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
