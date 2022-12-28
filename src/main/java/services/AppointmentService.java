package services;

import dao.AppointmentDAO;
import entities.Appointment;
import com.mongodb.BasicDBObject;

import java.util.Date;

public class AppointmentService {

    private AppointmentDAO appointmentDAO;

    public AppointmentService() {
        appointmentDAO = new AppointmentDAO();
    }

    public Appointment findByDate(Date rdvDate) {

        return this.appointmentDAO.findByDate(rdvDate);
    }

    public Appointment addRDV(Appointment rdv) {
        if (this.appointmentDAO.addRDV(rdv)) return rdv;
        return null;
    }


    public void deleteAppointmentById(long data) {
        this.appointmentDAO.deleteAppointmentById(data);
    }

    /*public boolean validationRDV(Appointment rdv) throws ParseException {
        return this.appointmentDAO.validationRDV(rdv);
    }*/

    public Appointment getRdvUsingField(String field, String Value) {
        return this.appointmentDAO.getRdvUsingField(field, Value);
    }

    public boolean updateRDV(BasicDBObject filter, BasicDBObject NewVal) {
        return this.appointmentDAO.updateAppointment(filter, NewVal);
    }
}
