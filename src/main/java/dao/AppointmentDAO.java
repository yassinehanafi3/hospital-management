package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import config.ConnectionMongoDB;
import entities.Appointment;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import entities.Doctor;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static validations.Validators.isNumeric;

public class AppointmentDAO {

    private final MongoCollection mongoCollection = ConnectionMongoDB.getMongoCollection("Appointments");

    private final Gson gson = new GsonBuilder().setDateFormat(DateFormat.FULL).create();


    public List<Appointment> findAllByDoctorAndAboveCurrentDate(Doctor doctor) {
        Bson andComparison = and(new Document("appointmentDoctorCni", doctor.getCni()),new Document("appointmentDate", new Document("$gt", new Date())));
        List<Appointment> appointmentList = new ArrayList<>();
        FindIterable iterable = this.mongoCollection.find(andComparison);
        try (MongoCursor<Document> cursor = iterable.iterator()) {
            while (cursor.hasNext()) {
                Appointment appointment = gson.fromJson(gson.toJson(cursor.next()), Appointment.class);
                System.out.println(appointment);
                appointmentList.add(appointment);
            }
        }
        return appointmentList;
    }


    public List<Appointment> findAllByDoctorAndDate(Doctor doctor, Date date) {
        Bson andComparison = and(new Document("appointmentDoctorCni", doctor.getCni()),new Document("appointmentDate", new Document("$eq", date)));
        List<Appointment> appointmentList = new ArrayList<>();
        FindIterable iterable = this.mongoCollection.find(andComparison);
        try (MongoCursor<Document> cursor = iterable.iterator()) {
            while (cursor.hasNext()) {
                Appointment appointment = gson.fromJson(gson.toJson(cursor.next()), Appointment.class);
                System.out.println(appointment);
                appointmentList.add(appointment);
            }
        }
        return appointmentList;
    }


    public List<Appointment> findAllByDoctor(Doctor doctor) {
        List<Appointment> appointmentList = new ArrayList<>();
        FindIterable iterable = this.mongoCollection.find(new Document("appointmentDoctorCni", doctor.getCni()));
        try (MongoCursor<Document> cursor = iterable.iterator()) {
            while (cursor.hasNext()) {
                Appointment appointment = gson.fromJson(gson.toJson(cursor.next()), Appointment.class);
                System.out.println(appointment);
                appointmentList.add(appointment);
            }
        }
        return appointmentList;
    }


    public List<Appointment> findAll() {
        List<Appointment> appointmentList = new ArrayList<>();
        FindIterable iterable = this.mongoCollection.find();
        try (MongoCursor<Document> cursor = iterable.iterator()) {
            while (cursor.hasNext()) {
                Appointment appointment = gson.fromJson(gson.toJson(cursor.next()), Appointment.class);
                System.out.println(appointment);
                appointmentList.add(appointment);
            }
        }
        return appointmentList;
    }

    public Appointment findByDate(Date appointmentDate) {
        return gson.fromJson(gson.toJson(this.mongoCollection.find(new Document("appointmentDate", appointmentDate)).first()), Appointment.class);
    }

    private Document CreateAppointment(Appointment appointment) {
        Document doc = new Document();
        doc.append("appointmentId", appointment.getAppointmentId());
        doc.append("appointmentDoctorCni", appointment.getAppointmentDoctorCni());
        doc.append("appointmentPationCni", appointment.getAppointmentPationCni());
        doc.append("appointmentNote", appointment.getAppointmentNote());
        doc.append("appointmentDate", appointment.getAppointmentDate());
        return doc;
    }

    public boolean addRDV(Appointment appointment) {
        mongoCollection.insertOne(this.CreateAppointment(appointment));
        return true;
    }


    public Appointment getRdvUsingField(String field, String Value) {
        BasicDBObject theQuery = new BasicDBObject();
        if (isNumeric(Value)) theQuery.put(field, Integer.parseInt(Value));
        else theQuery.put(field, Value);

        return gson.fromJson(gson.toJson(this.mongoCollection.find(theQuery).first()), Appointment.class);
    }

    public boolean updateAppointment(BasicDBObject filter, BasicDBObject NewVal) {
        return this.mongoCollection.updateOne(filter, NewVal).wasAcknowledged();
    }

    public boolean updateAppointment(String filterKey, String filterValue, String updateKey, String updateValue) {
        BasicDBObject filter = new BasicDBObject(filterKey, filterValue);
        BasicDBObject NewVal = new BasicDBObject(updateKey, updateValue);
        return this.mongoCollection.updateOne(filter, NewVal).wasAcknowledged();
    }

    public boolean deleteAppointmentById(long id) {
        return this.mongoCollection.deleteOne(new Document("appointmentId", id)).wasAcknowledged();
    }

    public boolean deleteAppointmentByDate(Date date) {
        return this.mongoCollection.deleteOne(new Document("appointmentDate", date)).wasAcknowledged();
    }


    /*public boolean validationRDV(Rdv rdv) throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        DateFormat format = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss", Locale.ENGLISH);
        Date date = format.parse(dtf.format(now));
        System.out.println(date);
        System.out.println(rdv.GetDate());

        if (rdv.GetUserName().isEmpty() == true || rdv.GetCni().isEmpty() == true || rdv.GetDate() == null || rdv.GetDoctor().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Sorry, please fill all blanks");
        } else {
            if (rdv.GetDate().before(date)) {
                JOptionPane.showMessageDialog(null, "Sorry, please insert a valid date");
            } else {
                if (this.findByDate(rdv.GetDate()) != null) {
                    JOptionPane.showMessageDialog(null, "Sorry, this date is already full");
                } else {
                    if (collectionRepository.getCollectionUsingField( "UserName", rdv.GetUserName(), "Pations") != null) {
                        if (rdv.GetCni().compareTo(collectionRepository.getFieldValueUsingUsername(rdv.GetUserName(), "Cni", "Pations")) == 0) {
                            return true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Cni erreur");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "User not found");
                        return false;
                    }
                }
            }
        }

        return false;
    }
*/

}
