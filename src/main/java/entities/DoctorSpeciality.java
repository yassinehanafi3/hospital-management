package entities;

import java.io.Serializable;

public class DoctorSpeciality implements Serializable {

    private String doctorSpecialityId;
    private String doctorSpecialityLabel;


    public DoctorSpeciality() {}

    public DoctorSpeciality(String doctorSpecialityLabel) {
        this.doctorSpecialityLabel = doctorSpecialityLabel;
    }

    public String getDoctorSpecialityId() {
        return doctorSpecialityId;
    }

    public String getDoctorSpecialityLabel() {
        return doctorSpecialityLabel;
    }

    public void setDoctorSpecialityLabel(String doctorSpecialityLabel) {
        this.doctorSpecialityLabel = doctorSpecialityLabel;
    }
}
