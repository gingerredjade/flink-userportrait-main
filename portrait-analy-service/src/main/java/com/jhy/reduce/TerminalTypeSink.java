package com.jhy.reduce;

import com.jhy.entity.TerminalTypeInfo;
import com.jhy.util.MongoUtils;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.bson.Document;

/**
 * 用户终端偏好标签Sink
 *      将统计的结果数量存到Mongo里
 *
 * Created by JHy on 2019/05/13
 */
public class TerminalTypeSink implements SinkFunction<TerminalTypeInfo> {

	@Override
	public void invoke(TerminalTypeInfo value, Context context) throws Exception {
		String terminaltype = value.getTerminaltype();
		long count = value.getCount();
		Document doc = MongoUtils.findoneby("terminaltypestatics","jhyPortrait",terminaltype);
		if(doc == null){
			doc = new Document();
			doc.put("info",terminaltype);
			doc.put("count",count);
		}else{
			Long countpre = doc.getLong("count");
			Long total = countpre+count;
			doc.put("count",total);
		}
		MongoUtils.saveorupdatemongo("terminaltypestatics","jhyPortrait",doc);
	}
}
