package com.jhy.reduce;

import com.jhy.entity.KeyWordEntity;
import org.apache.flink.api.common.functions.ReduceFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by li on 2019/1/5.
 */
public class KeywordReduce implements ReduceFunction<KeyWordEntity>{


    @Override
    public KeyWordEntity reduce(KeyWordEntity keyWordEntity1, KeyWordEntity keyWordEntity2) throws Exception {
        String userid = keyWordEntity1.getUserid();
        List<String> words1 = keyWordEntity1.getOriginalwords();
        List<String> words2 = keyWordEntity2.getOriginalwords();

        List<String> finalwords = new ArrayList<String>();
        finalwords.addAll(words1);
        finalwords.addAll(words2);

        KeyWordEntity keyWordEntityfinal = new KeyWordEntity();
        keyWordEntityfinal.setOriginalwords(finalwords);
        keyWordEntityfinal.setUserid(userid);
        return keyWordEntityfinal;
    }
}
