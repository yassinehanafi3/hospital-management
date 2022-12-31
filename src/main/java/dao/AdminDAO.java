package dao;

import com.mongodb.client.MongoCollection;
import config.ConnectionMongoDB;
import entities.Admin;
import org.bson.Document;
import static config.Helpers.*;

public class AdminDAO {

    private final MongoCollection mongoCollection = ConnectionMongoDB.getMongoCollection("Admins");


    public Admin findByAnyField(String field, String value) {
        return GSON.fromJson(GSON.toJson(this.mongoCollection.find(new Document(field, value)).first()), Admin.class);
    }

}
