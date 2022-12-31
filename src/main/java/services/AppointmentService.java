package services;

import dao.AppointmentDAO;
import entities.Appointment;
import com.mongodb.BasicDBObject;
import entities.Doctor;

import java.util.Date;
import java.util.List;

import static presentation.shared.GlobalVariables.CURRENT_USER;

public class AppointmentService {

    private AppointmentDAO appointmentDAO;

    public AppointmentService() {
        appointmentDAO = new AppointmentDAO();
    }

    public List<Appointment> getAllAppointments() {
        /*if (CURRENT_USER.getClass().getSimpleName().equals("Doctor")) { // for now after we will be checking if ADMIN
            return this.appointmentDAO.findAll();
        }*/
        return this.appointmentDAO.findAll();
    }

    public List<Appointment> getAllAppointmentsByDoctor(Doctor doctor) {
        return this.appointmentDAO.findAllByDoctor(doctor);
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
