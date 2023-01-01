package services;

import dao.PationDAO;
import entities.Appointment;
import entities.Doctor;
import entities.Pation;
import com.mongodb.BasicDBObject;
import entities.User;

import java.util.ArrayList;
import java.util.List;

import static presentation.shared.GlobalVariables.CURRENT_USER;

public class PationService {

    private PationDAO pationDao;
    private AppointmentService appointmentService = new AppointmentService();

    public PationService() {
        this.pationDao = new PationDAO();
    }

    public List<Pation> getAllPations() {
        /*if (CURRENT_USER.getClass().getSimpleName().equals("Doctor")) { // for now after we will be checking if ADMIN
            return this.pationDao.findAll();
        }*/
        return this.pationDao.findAll();
    }


    public List<Pation> getAllPationsByDoctor(User doctor) {
        List<Pation> pations = new ArrayList<>();
        for (Appointment appointment : this.appointmentService.getAllAppointmentsByDoctor(doctor)) {
            Pation pation = this.getPationByCni(appointment.getAppointmentPationCni());
            if (!pations.contains(pation)) pations.add(pation);
        }
        return pations;
    }


    /*public boolean pationValidation(Pation pat, String password) throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        DateFormat format = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss", Locale.ENGLISH);
        Date date = format.parse(dtf.format(now));
        boolean validPassword = Password.isValidPassword(password);
        if (pat.GetDateOfBirth() == null || pat.GetFirstName().isEmpty() || pat.GetLastName().isEmpty() || pat.GetDateOfBirth() == null || pat.GetUserName().isEmpty() || pat.GetCni().isEmpty() || pat.GetPhone().isEmpty() || pat.GetPassword().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sorry, please fill all blanks");
        } else {
            if (pat.GetDateOfBirth().after(date)) {
                JOptionPane.showMessageDialog(null, "Are you sure you are born in the future");
            } else {
                if (pat.GetPhone().length() > 10) {
                    JOptionPane.showMessageDialog(null, "Moroccan numbers only please");
                } else {
                    if (pat.GetPassword().isEmpty() == false && validPassword == true) {
                        if (null == collectionRepository.getCollectionUsingField( "UserName", pat.GetUserName(), "Pations")) {
                            if (null == collectionRepository.getCollectionUsingField( "Cni", pat.GetCni(), "Pations")) {
                                return true;
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
        }
        return false;
    }*/


    public Pation getPationByCni(String cni) {
        return this.pationDao.findByCni(cni);
    }

    public boolean deletePation(String cni) {
        return this.pationDao.deleteByCni(cni);
    }

    public Pation addPation(Pation pation) {
        if (this.pationDao.addPation(pation)) {
            return pation;
        }
        return null;
    }

    public Pation getPationByField(String field, String Value){
        return this.pationDao.findByAnyField(field, Value);
    }

    public boolean updatePation(BasicDBObject filter, BasicDBObject NewVal) {
        return this.pationDao.updatePation(filter, NewVal);
    }

    public boolean updatePation(String filterKey, String filterValue, String updateKey, String updateValue) {
        return this.pationDao.updatePation(filterKey, filterValue, updateKey, updateValue);
    }

}
