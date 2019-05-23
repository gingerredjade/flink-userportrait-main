package com.jhy.reduce;

import com.jhy.entity.KeyWordEntity;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * KeyWordReduce2
 * 		[对总文档数进行聚合]
 *
 * Created by li on 2019/1/5.
 */
public class KeyWordReduce2 implements ReduceFunction<KeyWordEntity>{


    @Override
    public KeyWordEntity reduce(KeyWordEntity keyWordEntity1, KeyWordEntity keyWordEntity2) throws Exception {

        long count1 = keyWordEntity1.getTotaldocumet()==null?1L:keyWordEntity1.getTotaldocumet();
        long count2 = keyWordEntity2.getTotaldocumet()==null?1L:keyWordEntity2.getTotaldocumet();
        KeyWordEntity keyWordEntityFinal = new KeyWordEntity();
		keyWordEntityFinal.setTotaldocumet(count1 + count2);
        return keyWordEntityFinal;
    }
}
