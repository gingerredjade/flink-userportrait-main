package com.jhy.util;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * MongoDB工具类
 * Created by JHy on 2019/04/25
 */
public class MongoUtils {

    private static MongoClient mongoClient = new MongoClient("192.168.75.20",27017);

    /**
     * 查询
     * @param tablename
     * @param database
     * @param yearbasetype
     * @return
     */
    public static Document findoneby(String tablename, String database, String yearbasetype){
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        MongoCollection mongoCollection = mongoDatabase.getCollection(tablename);
        Document  doc = new Document();
        doc.put("info", yearbasetype);
        FindIterable<Document> itrer = mongoCollection.find(doc);
        MongoCursor<Document> mongocursor = itrer.iterator();
        if(mongocursor.hasNext()){
            return mongocursor.next();
        }else{
            return null;
        }
    }

    /**
     * 保存/更新mongo
     * @param tablename
     * @param database
     * @param doc
     */
    public static void saveorupdatemongo(String tablename,String database,Document doc) {
        // 获取库
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        // 获取表
        MongoCollection<Document> mongocollection = mongoDatabase.getCollection(tablename);
        if(!doc.containsKey("_id")){
            ObjectId objectid = new ObjectId();
            doc.put("_id", objectid);
            mongocollection.insertOne(doc);
            return;
        }
        Document matchDocument = new Document();
        String objectid = doc.get("_id").toString();
        matchDocument.put("_id", new ObjectId(objectid));
        FindIterable<Document> findIterable =  mongocollection.find(matchDocument);
        if(findIterable.iterator().hasNext()){
            mongocollection.updateOne(matchDocument, new Document("$set",doc));
            try {
                System.out.println("come into saveorupdatemongo ---- update---"+ JSONObject.toJSONString(doc));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            mongocollection.insertOne(doc);
            try {
                System.out.println("come into saveorupdatemongo ---- insert---"+ JSONObject.toJSONString(doc));
            }catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
