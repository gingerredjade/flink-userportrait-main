package com.jhy.kmeans;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.Random;

/**
 * KMeansFinalMap
 * Created by JHy on 2019/5/15.
 */
public class KMeansMap implements MapFunction<String, KMeans>{
    @Override
    public KMeans map(String s) throws Exception {
        if(StringUtils.isBlank(s)){
            return null;
        }
        // 2,3,4
        Random random = new Random();
        String [] temps = s.split(",");
        String variable1 = temps[0];
        String variable2 = temps[1];
        String variable3 = temps[2];
        KMeans kMeans = new KMeans();
        kMeans.setVariable1(variable1);
        kMeans.setVariable2(variable2);
        kMeans.setVariable3(variable3);
        kMeans.setGroupbyfield("logic=="+random.nextInt(10));
        return kMeans;
    }
}
