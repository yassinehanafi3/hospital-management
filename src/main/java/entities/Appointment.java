package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment implements Serializable {

    private long appointmentId;
    private String appointmentDoctorCni;
    private String appointmentPationCni;

    private String appointmentNote;
    private Date appointmentDate;

    public Appointment() {}

    public Appointment(long appointmentId, String appointmentDoctor, String appointmentPation, Date appointmentDate, String appointmentNote) {
        this.appointmentId = appointmentId;
        this.appointmentDoctorCni = appointmentDoctor;
        this.appointmentPationCni = appointmentPation;
        this.appointmentDate = appointmentDate;
        this.appointmentNote = appointmentNote;
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentDoctorCni() {
        return appointmentDoctorCni;
    }

    public void setAppointmentDoctorCni(String appointmentDoctorCni) {
        this.appointmentDoctorCni = appointmentDoctorCni;
    }

    public String getAppointmentPationCni() {
        return appointmentPationCni;
    }

    public void setAppointmentPationCni(String appointmentPationCni) {
        this.appointmentPationCni = appointmentPationCni;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentNote() {
        return appointmentNote;
    }

    public void setAppointmentNote(String appointmentNote) {
        this.appointmentNote = appointmentNote;
    }

    @Override
    public String toString() {
        return "Date : " + appointmentDate +  "\t\t\t" +
                "Doctor : " + appointmentDoctorCni + "\t\t\t" +
                "Note : " + appointmentNote;
    }
}
