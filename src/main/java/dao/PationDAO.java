package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCursor;
import config.ConnectionMongoDB;
import entities.Pation;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PationDAO {

    private final MongoCollection mongoCollection = ConnectionMongoDB.getMongoCollection("Pations");

    private final Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL).create();

    public List<Pation> findAll() {
        List<Pation> pationList = new ArrayList<>();
        FindIterable iterable = this.mongoCollection.find();
        try (MongoCursor<Document> cursor = iterable.iterator()) {
            while (cursor.hasNext()) {
                Pation pation = gson.fromJson(gson.toJson(cursor.next()), Pation.class);
                pationList.add(pation);
            }
        }
        return pationList;
    }

    public Pation findByCni(String cni) {
        return gson.fromJson(gson.toJson(this.mongoCollection.find(new Document("cni", cni)).first()), Pation.class);
    }

    public Pation findByUsername(String userName) {
        return gson.fromJson(gson.toJson(this.mongoCollection.find(new Document("userName", userName)).first()), Pation.class);
    }

    public Pation findByBirthDate(Date birthDate) {
        return gson.fromJson(gson.toJson(this.mongoCollection.find(new Document("birthDate", birthDate)).first()), Pation.class);
    }

    public Pation findByAnyField(String field, String value) {
        return gson.fromJson(gson.toJson(this.mongoCollection.find(new Document(field, value)).first()), Pation.class);
    }

    private Document CreatePation(Pation pation) {
        Document doc = new Document();
        doc.append("cni", pation.getCni());
        doc.append("firstName", pation.getFirstName());
        doc.append("lastName", pation.getLastName());
        doc.append("createdAt", pation.getCreatedAt());
        doc.append("birthDate", pation.getBirthDate());
        doc.append("userName", pation.getUserName());
        doc.append("userPassword", pation.getUserPassword());
        doc.append("phone", pation.getPhone());
        return doc;
    }

    public boolean addPation(Pation pation) {
        this.mongoCollection.insertOne(this.CreatePation(pation));
        return true;
    }

    public boolean updatePation(BasicDBObject filter, BasicDBObject NewVal) {
        return this.mongoCollection.updateOne(filter, NewVal).wasAcknowledged();
    }

    public boolean updatePation(String filterKey, String filterValue, String updateKey, String updateValue) {
        BasicDBObject filter = new BasicDBObject(filterKey, filterValue);
        BasicDBObject NewVal = new BasicDBObject(updateKey, updateValue);
        return this.mongoCollection.updateOne(filter, NewVal).wasAcknowledged();
    }

    public boolean deleteByCni(String cni) {
        return this.mongoCollection.deleteOne(new Document("cni", cni)).wasAcknowledged();
    }




}
