package com.jhy.reduce;

import com.jhy.entity.ChaomanAndWomenInfo;
import com.jhy.util.MongoUtils;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.bson.Document;

/**
 * 潮男潮女标签Sink
 *      将统计的潮流类型存到Mongo里
 *
 * Created by JHy on 2019/5/17.
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
