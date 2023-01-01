package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import config.ConnectionMongoDB;
import entities.Admin;
import org.bson.Document;
import static config.Helpers.*;

public class AdminDAO {

    private final MongoCollection mongoCollection = ConnectionMongoDB.getMongoCollection("Admins");


    public Admin findByAnyField(String field, String value) {
        return GSON.fromJson(GSON.toJson(this.mongoCollection.find(new Document(field, value)).first()), Admin.class);
    }


    public boolean updateField(String filterField, String filterValue, String updateField, String updateValue ) {
        //BasicDBObject filter = new BasicDBObject(filterField, filterValue);
        return this.mongoCollection.updateOne(new Document(filterField, filterValue), Updates.set(updateField,updateValue)).wasAcknowledged();
    }

}
