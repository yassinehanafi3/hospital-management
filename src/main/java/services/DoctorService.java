package services;

import dao.DoctorDAO;
import entities.Doctor;
import com.mongodb.BasicDBObject;

import java.util.List;

import static presentation.shared.GlobalVariables.*;


public class DoctorService {

    private DoctorDAO doctorDAO;

    public DoctorService() {
        doctorDAO = new DoctorDAO();
    }


    public List<Doctor> findContains(String text) {
        return doctorDAO.findContains(text);
    }

    public List<Doctor> getAllDoctors() {
        if (CURRENT_USER.getClass().getSimpleName().equals("Doctor")) { // for now after we will be checking if ADMIN
            return this.doctorDAO.findAll();
        }
        return null;
    }

    /*public boolean validationDoctor(Doctor doc, String password) throws ParseException,
            NoSuchAlgorithmException {
        return doctorService.validationDoctor(doc, password);
    }*/

    public Doctor getDoctorByCni(String cni) {
        return this.doctorDAO.findByCni(cni);
    }


    public Doctor getDoctorByField(String field, String Value) {
        return this.doctorDAO.findByAnyField(field, Value);
    }


    /*public String getFirstDoctorAvailable(String cni){
        return this.doctorService.getFirstAvailableDoctor(cni);
    }*/

    public Doctor addDoctor(Doctor doc){
        if (this.doctorDAO.addDoctor(doc)) {
            return doc;
        }
        return null;
    }

    public boolean updateDoctor(BasicDBObject filter, BasicDBObject NewVal) {
        return this.doctorDAO.updateDoctor(filter, NewVal);
    }

    public boolean update(Doctor doctor) {
        return this.doctorDAO.update(doctor);
    }

    public boolean updateDoctor(String filterKey, String filterValue, String updateKey, String updateValue) {
        return this.doctorDAO.updateDoctor(filterKey, filterValue, updateKey, updateValue);
    }

    public boolean deleteDoctorUsingCni(String Cni) {
        return this.doctorDAO.deleteByCni(Cni);
    }

    /*public boolean validationDoctor(Doctor doc, String password) throws ParseException,
            NoSuchAlgorithmException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        DateFormat format = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss", Locale.ENGLISH);
        Date date = format.parse(dtf.format(now));
        boolean validPassword = Password.isValidPassword(password);
        if (doc.GetSpeciality().equals("") || doc.GetDateOfBirth() == null || doc.GetFirstName().isEmpty() || doc.GetLastName().isEmpty() || doc.GetUserName().isEmpty() || doc.GetCni().isEmpty() || doc.GetPassword().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sorry, please fill all blanks");
        } else {
            if (doc.GetDateOfBirth().after(date)) {
                JOptionPane.showMessageDialog(null, "Are you sure you are born in the future");
            } else {
                if (password.isEmpty() == false && validPassword == true) {
                    if (null == collectionRepository.getCollectionUsingField( "UserName", doc.GetUserName(), "Doctors")) {
                        if (null == collectionRepository.getCollectionUsingField( "Cni", doc.GetCni(), "Doctors")) {
                            if (doc.GetDateOfBirth().after(date)) {
                                JOptionPane.showMessageDialog(null, "Are you sure you are born in the future");
                            } else {
                                return true;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Are you sure this is your CNI");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Sorry, username exists already");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Sorry, password must have atleast (one lowercase character,one uppercase character,one number,one special character)");
                }
            }
        }
        return false;
    }*/
}
