package com.jhy.reduce;

import com.jhy.entity.BrandLike;
import com.jhy.util.MongoUtils;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.bson.Document;

/**
 * 用户品牌偏好标签Sink
 *      将统计的结果数量存到Mongo里
 *
 * Created by JHy on 2019/05/13
 */
public class BrandLikeSink implements SinkFunction<BrandLike> {

	@Override
	public void invoke(BrandLike value, Context context) throws Exception {
		String brand = value.getBrand();
		long count = value.getCount();
		Document doc = MongoUtils.findoneby("brandlikestatics","jhyPortrait",brand);
		if(doc == null){
			doc = new Document();
			doc.put("info",brand);
			doc.put("count",count);
		}else{
			Long countpre = doc.getLong("count");
			Long total = countpre+count;
			doc.put("count",total);
		}
		MongoUtils.saveorupdatemongo("brandlikestatics","jhyPortrait",doc);
	}
}
