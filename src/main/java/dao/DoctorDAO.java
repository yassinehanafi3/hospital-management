package dao;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import config.Connection;
import entities.Doctor;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DoctorDAO {

    private final MongoCollection mongoCollection = Connection.getMongoCollection("Doctors");
    private final Gson gson = new Gson();


    public List<Doctor> findContains(String text) {
        List<Doctor> doctorList = new ArrayList<>();
        Bson filter = Filters.text(text); // using mongodb search text index
        FindIterable iterable = mongoCollection.find(filter);
        //FindIterable iterable = mongoCollection.find(new Document("$text", new Document("$search", text))); // using our own created search text index
        try (MongoCursor<Document> cursor = iterable.iterator()) {
            while (cursor.hasNext()) {
                Doctor doctor = gson.fromJson(cursor.next().toJson(), Doctor.class);
                doctorList.add(doctor);
            }
        }
        return doctorList;
    }

    public List<Doctor> findAll() {
        List<Doctor> doctorList = new ArrayList<>();
        FindIterable iterable = this.mongoCollection.find();
        try (MongoCursor<Document> cursor = iterable.iterator()) {
            while (cursor.hasNext()) {
                Doctor doctor = gson.fromJson(cursor.next().toJson(), Doctor.class);
                doctorList.add(doctor);
            }
        }
        return doctorList;
    }

    public Doctor findByCni(String cni) {
        return gson.fromJson(gson.toJson(this.mongoCollection.find(new Document("cni", cni)).first()), Doctor.class);
    }

    public Doctor findByUsername(String userName) {
        return gson.fromJson(gson.toJson(this.mongoCollection.find(new Document("userName", userName)).first()), Doctor.class);
    }

    public Doctor findByBirthDate(Date birthDate) {
        return gson.fromJson(gson.toJson(this.mongoCollection.find(new Document("birthDate", birthDate)).first()), Doctor.class);
    }

    public Doctor findByAnyField(String field, String value) {
        return gson.fromJson(gson.toJson(this.mongoCollection.find(new Document(field, value)).first()), Doctor.class);
    }

    private Document CreateDoctor(Doctor doctor) {
        Document doc = new Document();
        doc.append("cni", doctor.getCni());
        doc.append("firstName", doctor.getFirstName());
        doc.append("lastName", doctor.getLastName());
        doc.append("createdAt", doctor.getCreatedAt().toLocaleString());
        doc.append("birthDate",  doctor.getBirthDate().toLocaleString());
        doc.append("userName", doctor.getUserName());
        doc.append("userPassword", doctor.getUserPassword());
        doc.append("doctorSpeciality", doctor.getDoctorSpeciality());
        doc.append("doctorStatus", doctor.isDoctorStatus());
        return doc;
    }

    public boolean addDoctor(Doctor doctor) {
        this.mongoCollection.insertOne(this.CreateDoctor(doctor));
        return true;
    }

    public boolean updateDoctor(BasicDBObject filter, BasicDBObject NewVal) {
        return this.mongoCollection.updateOne(filter, NewVal).wasAcknowledged();
    }

    public boolean updateDoctor(String filterKey, String filterValue, String updateKey, String updateValue) {
        BasicDBObject filter = new BasicDBObject(filterKey, filterValue);
        BasicDBObject NewVal = new BasicDBObject(updateKey, updateValue);
        return this.mongoCollection.updateOne(filter, NewVal).wasAcknowledged();
    }

    public boolean update(Doctor doctor) {
        return this.mongoCollection.updateOne(new Document("cni", doctor.getCni()), Updates.combine(
                Updates.set("firstName", doctor.getFirstName()),
                Updates.set("lastName", doctor.getLastName()),
                Updates.set("userName", doctor.getUserName()),
                Updates.set("birthDate", doctor.getBirthDate().toLocaleString()),
                Updates.set("doctorSpeciality", doctor.getDoctorSpeciality())
        )).wasAcknowledged();
    }

    public boolean deleteByCni(String cni) {
        return this.mongoCollection.deleteOne(new Document("cni", cni)).wasAcknowledged();
    }


}
