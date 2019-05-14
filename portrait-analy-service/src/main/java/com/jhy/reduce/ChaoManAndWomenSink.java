package com.jhy.reduce;

import com.jhy.entity.ChaomanAndWomenInfo;
import com.jhy.util.MongoUtils;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.bson.Document;

/**
 * Created by li on 2019/1/6.
 */
public class ChaoManAndWomenSink implements SinkFunction<ChaomanAndWomenInfo> {
    @Override
    public void invoke(ChaomanAndWomenInfo value, Context context) throws Exception {
        String chaotype = value.getChaotype();
        long count = value.getCount();
        Document doc = MongoUtils.findoneby("chaoManAndWomenstatics","jhyPortrait",chaotype);
        if(doc == null){
            doc = new Document();
            doc.put("info",chaotype);
            doc.put("count",count);
        }else{
            Long countpre = doc.getLong("count");
            Long total = countpre+count;
            doc.put("count",total);
        }
        MongoUtils.saveorupdatemongo("chaoManAndWomenstatics","jhyPortrait",doc);
    }
}
