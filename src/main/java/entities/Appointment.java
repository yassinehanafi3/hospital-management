package entities;

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {

    private long appointmentId;
    private Doctor appointmentDoctor;
    private Pation appointmentPation;

    private File file;
    private Date appointmentDate;

    public Appointment() {}

    public Appointment(long appointmentId, Doctor appointmentDoctor, Pation appointmentPation, Date appointmentDate) {
        this.appointmentId = appointmentId;
        this.appointmentDoctor = appointmentDoctor;
        this.appointmentPation = appointmentPation;
        this.appointmentDate = appointmentDate;
        this.file = new File();
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Doctor getAppointmentDoctor() {
        return appointmentDoctor;
    }

    public void setAppointmentDoctor(Doctor appointmentDoctor) {
        this.appointmentDoctor = appointmentDoctor;
    }

    public Pation getAppointmentPation() {
        return appointmentPation;
    }

    public void setAppointmentPation(Pation appointmentPation) {
        this.appointmentPation = appointmentPation;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
