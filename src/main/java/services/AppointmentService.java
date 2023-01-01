package services;

import dao.AppointmentDAO;
import entities.Appointment;
import com.mongodb.BasicDBObject;
import entities.Doctor;
import entities.Pation;
import entities.User;

import java.util.Date;
import java.util.List;

import static presentation.shared.GlobalVariables.CURRENT_USER;

public class AppointmentService {

    private AppointmentDAO appointmentDAO;

    public AppointmentService() {
        appointmentDAO = new AppointmentDAO();
    }

    public List<Appointment> getAllAppointments() {
        if (CURRENT_USER.getClass().getSimpleName().equals("Admin")) {
            return this.appointmentDAO.findAll();
        }
        return null;
    }

    public List<Appointment> getAllAppointmentsByDoctor(User doctor) {
        return this.appointmentDAO.findAllByDoctor(doctor);
    }

    public List<Appointment> getAllAppointmentsByDoctorAndPation(User doctor, Pation pation) {
        return this.appointmentDAO.findAllByDoctorAndPation(doctor, pation);
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

    public boolean updateRDV(String filterKey, String filterValue, String newValKey, String newValue) {
        return this.appointmentDAO.updateAppointment(filterKey, filterValue, newValKey, newValue);
    }
    public boolean updateRDVById(long id, String newValKey, String newValue) {
        return this.appointmentDAO.updateAppointment(id, newValKey, newValue);
    }
}
