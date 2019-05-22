package com.jhy.tfIdf;

import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * IdfReduce
 * 		[统计文档总数量]
 *
 * Created by li on 2019/1/5.
 */
public class IdfReduce implements ReduceFunction<TfIdfEntity>{


    @Override
    public TfIdfEntity reduce(TfIdfEntity tfIdfEntity1, TfIdfEntity tfIdfEntity2) throws Exception {

        long count1 = tfIdfEntity1.getTotaldocument();
        long count2 = tfIdfEntity2.getTotaldocument();
        TfIdfEntity tfIdfEntity = new TfIdfEntity();
        tfIdfEntity.setTotaldocument(count1 + count2);
        return tfIdfEntity;
    }
}
