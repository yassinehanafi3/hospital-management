package services;

import dao.DoctorDAO;
import dao.PationDAO;
import entities.User;

public class UserService {

    private PationDAO pationDAO = new PationDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();


    public User findByUsernameAndRole(String userName, String userRole) {
        return switch (userRole) {
            case "Doctor" -> this.doctorDAO.findByUsername(userName);
            case "Pation" -> this.pationDAO.findByUsername(userName);
            default -> null;
        };
    }

    /*public User findByCni(long cni) {

    }
*/
}
