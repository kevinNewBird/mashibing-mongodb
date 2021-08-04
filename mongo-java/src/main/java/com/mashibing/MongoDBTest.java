package com.mashibing;

import com.alibaba.fastjson.JSON;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

/**
 * description  MongoDBTest <BR>
 * <p>
 * author: zhao.song
 * date: created in 13:41  2021/8/4
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Slf4j
public class MongoDBTest {

    private static final String DATABASE = "test";

    /**
     * description   获取数据库连接  <BR>
     *
     * @param connName:
     * @return {@link MongoCollection< Document>}
     * @author zhao.song  2021/8/4  13:44
     */
    public MongoCollection<Document> getDB(String connName) {
        //1.创建连接
        ConnectionString connString = new ConnectionString("mongodb://192.168.233.129:27017/");

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        return mongoClient.getDatabase(DATABASE).getCollection(connName);
    }

    @Test
    public void insert() {
        MongoCollection<Document> collection = getDB("shop");
        Document document = new Document();
        document.append("name", "手机");
        document.append("price", 8000);
        InsertOneResult insertOneResult = collection.insertOne(document);
        log.info(JSON.toJSONString(insertOneResult));

    }


    @Test
    public void update() {
        MongoCollection<Document> collection = getDB("shop");
        // 条件
        Bson condition = Filters.and(Filters.eq("name", "手机"));
        Document upDocument = new Document("$set", new Document().append("price", 6000));
        UpdateResult updateResult = collection.updateMany(condition, upDocument);
        log.info(JSON.toJSONString(updateResult));
    }

    @Test
    public void delete() {
        MongoCollection<Document> collection = getDB("shop");
        // 条件
        Bson condition = Filters.and(Filters.eq("name", "手机"));
        DeleteResult deleteResult = collection.deleteMany(condition);
        log.info(JSON.toJSONString(deleteResult));
    }

    @Test
    public void findAll() {
        MongoCollection<Document> collection = getDB("shop");
        FindIterable<Document> documents = collection.find();
        documents.forEach(item -> log.info(JSON.toJSONString(item)));
    }

    @Test
    public void findByCondition() {
        MongoCollection<Document> collection = getDB("shop");
        // 条件
        Bson condition = Filters.and(Filters.eq("name", "手机"));
        FindIterable<Document> documents = collection.find(condition);
        documents.forEach(item -> log.info(JSON.toJSONString(item)));
    }
}
