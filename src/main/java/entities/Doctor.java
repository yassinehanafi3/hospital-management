package entities;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Doctor extends User implements Serializable {

    private String doctorSpeciality;
    private boolean doctorStatus;
    private Date createdAt = new Date();


    public Doctor(){}

    public Doctor(ObjectId id, String firstName, String lastName, String cni, String userName, String userPassword, String phone, Date birthDate, String Speciality, String userRole) throws NoSuchAlgorithmException {
        super(id, firstName.toUpperCase(), lastName.toUpperCase(), cni.toUpperCase(), userName.toUpperCase(),Password.GetHash(userPassword), phone, birthDate, userRole);
        this.doctorSpeciality = Speciality.toUpperCase();
        this.doctorStatus = false;
    }


    public String getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public void setDoctorSpeciality(String doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
    }

    public boolean isDoctorStatus() {
        return doctorStatus;
    }

    public void setDoctorStatus(boolean doctorStatus) {
        this.doctorStatus = doctorStatus;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorSpeciality='" + doctorSpeciality + '\'' +
                ", doctorStatus=" + doctorStatus +
                ", createdAt=" + createdAt +
                "} " + super.toString();
    }
}
