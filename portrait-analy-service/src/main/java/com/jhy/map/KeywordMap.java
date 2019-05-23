package com.jhy.map;

import com.jhy.entity.KeyWordEntity;
import org.apache.flink.api.common.functions.MapFunction;

import java.util.*;

/**
 * KeywordMap
 * 		[获取用户对应的原始词集数据]
 *
 * Created by li on 2019/1/20.
 */

/**
 * 一条数据 userid,小米8 全面屏游戏智能手机 6GB+64GB 金色 全网通4G 双卡双待
 * 		[最终是对一个用户下的所有数据进行拆分，所以需要先根据userid进行聚合，把一个userid下的所有数据聚合到一起]
 */
public class KeywordMap implements MapFunction<String, KeyWordEntity> {

    @Override
    public KeyWordEntity map(String s) throws Exception {
        String[] productwordarray = s.split(",");
        String userid = productwordarray[0];
        String wordarray = productwordarray[1];

        KeyWordEntity keyWordEntity = new KeyWordEntity();
        keyWordEntity.setUserid(userid);
        List<String> words = new ArrayList<String>();
        words.add(wordarray);
        keyWordEntity.setOriginalwords(words);
        return keyWordEntity;
    }
}
