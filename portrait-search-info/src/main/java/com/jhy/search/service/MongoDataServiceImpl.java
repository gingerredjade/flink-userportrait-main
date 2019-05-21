package com.jhy.search.service;

import com.alibaba.fastjson.JSONObject;
import com.jhy.entity.AnalyResult;
import com.jhy.search.base.BaseMongo;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Mongo 服务类
 * Created by JHy on 2019/5/19.
 */
@Service
public class MongoDataServiceImpl extends BaseMongo {

	/**
	 * 通过表名查找数据列表通用接口，支持所有分析结果的查询
	 *
	 * @param tablename
	 * @return
	 */
    public List<AnalyResult> listMongoInfoby(String tablename) {
        List<AnalyResult> result = new ArrayList<AnalyResult>();


        MongoDatabase db = mongoClient.getDatabase("jhyPortrait");
        MongoCollection<Document> collection =  db.getCollection(tablename);


        Document groupFields = new Document();
        Document idFields = new Document();
        idFields.put("info", "$info");								// 根据info分组
        groupFields.put("_id", idFields);
        groupFields.put("count", new Document("$sum", "$count"));	// 加和字段

        Document group = new Document("$group", groupFields);


        Document projectFields = new Document();
        projectFields.put("_id", false);
        projectFields.put("info", "$_id.info");
        projectFields.put("count", true);
        Document project = new Document("$project", projectFields);
        AggregateIterable<Document> iterater = collection.aggregate(
                (List<Document>) Arrays.asList(group, project)
        );

        MongoCursor<Document> cursor = iterater.iterator();
        while(cursor.hasNext()){
            Document document = cursor.next();
            String jsonString = JSONObject.toJSONString(document);
            AnalyResult brandUser = JSONObject.parseObject(jsonString,AnalyResult.class);
            result.add(brandUser);
        }
        return result;
    }
}
