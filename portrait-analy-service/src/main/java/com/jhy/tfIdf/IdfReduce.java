package com.jhy.tfIdf;

import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * Created by li on 2019/1/5.
 */
public class IdfReduce implements ReduceFunction<TfIdfEntity>{


    @Override
    public TfIdfEntity reduce(TfIdfEntity tfIdfEntity1, TfIdfEntity tfIdfEntity2) throws Exception {

        long count1 = tfIdfEntity1.getTotaldocumet();
        long count2 = tfIdfEntity2.getTotaldocumet();
        TfIdfEntity tfIdfEntity = new TfIdfEntity();
        tfIdfEntity.setTotaldocumet(count1 + count2);
        return tfIdfEntity;
    }
}
