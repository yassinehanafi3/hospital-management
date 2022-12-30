package services;

import dao.DoctorSpecialityDAO;
import entities.DoctorSpeciality;

import java.util.List;

public class DoctorSpecialityService {

    private DoctorSpecialityDAO doctorSpecialityDAO = new DoctorSpecialityDAO();

    public DoctorSpeciality getDoctorSpecialityById(String id){
        return doctorSpecialityDAO.findById(id);
    }

    public DoctorSpeciality getDoctorSpecialityByName(String name){
        return doctorSpecialityDAO.findByName(name);
    }

    public List<DoctorSpeciality> getListDoctorSpecialities() {
        return this.doctorSpecialityDAO.findAll();
    }

    public List<String> getListDoctorSpecialitiesLabels() {
        return this.doctorSpecialityDAO.findAllAsLabels();
    }

}
