package entities;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Date;

public class Doctor extends User implements Serializable {

    private String doctorSpecialityId;
    private boolean doctorStatus;
    private Date createdAt = new Date(DateFormat.FULL);


    public Doctor(){}

    public Doctor(String firstName, String lastName, String cni, String userName, String userPassword, String phone, Date birthDate, String Speciality, String userRole) throws NoSuchAlgorithmException {
        super(firstName.toUpperCase(), lastName.toUpperCase(), cni.toUpperCase(), userName.toUpperCase(),Password.GetHash(userPassword), phone, birthDate, userRole);
        this.doctorSpecialityId = Speciality;
        this.doctorStatus = false;
    }


    public String getDoctorSpecialityId() {
        return doctorSpecialityId;
    }

    public void setDoctorSpecialityId(String doctorSpecialityId) {
        this.doctorSpecialityId = doctorSpecialityId;
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
                "doctorSpecialityId=" + doctorSpecialityId +
                ", doctorStatus=" + doctorStatus +
                ", createdAt=" + createdAt +
                "} " + super.toString();
    }
}
