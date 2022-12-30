package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import config.ConnectionMongoDB;
import entities.Doctor;
import entities.DoctorSpeciality;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static config.Helpers.*;

public class DoctorSpecialityDAO {


    private final MongoCollection mongoCollection = ConnectionMongoDB.getMongoCollection("DoctorSpecialities");


    public List<DoctorSpeciality> findAll() {
        List<DoctorSpeciality> doctorSpecialities = new ArrayList<>();
        FindIterable iterable = this.mongoCollection.find();
        try (MongoCursor<Document> cursor = iterable.iterator()) {
            while (cursor.hasNext()) {
                DoctorSpeciality doctorSpeciality = GSON.fromJson(cursor.next().toJson(), DoctorSpeciality.class);
                doctorSpecialities.add(doctorSpeciality);
            }
        }
        return doctorSpecialities;
    }

    public List<String> findAllAsLabels() {
        List<String> doctorSpecialities = new ArrayList<>();
        FindIterable iterable = this.mongoCollection.find();
        try (MongoCursor<Document> cursor = iterable.iterator()) {
            while (cursor.hasNext()) {
                DoctorSpeciality doctorSpeciality = GSON.fromJson(cursor.next().toJson(), DoctorSpeciality.class);
                doctorSpecialities.add(doctorSpeciality.getDoctorSpecialityLabel());
            }
        }
        return doctorSpecialities;
    }


    public DoctorSpeciality findById(String id) {
        return GSON.fromJson(GSON.toJson(this.mongoCollection.find(new Document("doctorSpecialityId", id)).first()), DoctorSpeciality.class);
    }

    public DoctorSpeciality findByName(String name) {
        return GSON.fromJson(GSON.toJson(this.mongoCollection.find(new Document("doctorSpecialityLabel", name)).first()), DoctorSpeciality.class);
    }

}
