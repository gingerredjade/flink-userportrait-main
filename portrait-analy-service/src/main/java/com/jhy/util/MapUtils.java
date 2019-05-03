package com.jhy.util;

import java.util.*;

/**
 * Map工具类
 * Created by JHy on 2019/04/25
 */
public class MapUtils {

    /**
     * 使用 Map按value进行排序
     * @return
     */
    public static LinkedHashMap<String, Double> sortMapByValue(Map<String,Double> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        List<Map.Entry<String, Double>> entryList = new ArrayList<Map.Entry<String, Double>>(
                oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<String, Double>> iter = entryList.iterator();
        Map.Entry<String, Double> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    /**
     *
     */
    static class MapValueComparator implements Comparator<Map.Entry<String, Double>> {

        @Override
        public int compare(Map.Entry<String, Double> me1, Map.Entry<String, Double> me2) {

            return me1.getValue().compareTo(me2.getValue());
        }
    }

}
