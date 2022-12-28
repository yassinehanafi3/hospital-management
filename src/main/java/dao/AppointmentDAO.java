package dao;

import config.Connection;
import entities.Appointment;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Date;

import static validations.Validators.isNumeric;

public class AppointmentDAO {

    private final MongoCollection mongoCollection = Connection.getMongoCollection("Appointments");

    public Appointment findByDate(Date appointmentDate) {
        return (Appointment) this.mongoCollection.find(new Document("appointmentDate", appointmentDate)).first();
    }

    private Document CreateAppointment(Appointment appointment) {
        Document doc = new Document();
        doc.append("appointmentId", appointment.getAppointmentId());
        doc.append("appointmentDoctorCni", appointment.getAppointmentDoctor().getCni());
        doc.append("appointmentPationCni", appointment.getAppointmentPation().getCni());
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

        return (Appointment) this.mongoCollection.find(theQuery).first();
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
