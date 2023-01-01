package services;

import dao.AdminDAO;
import dao.DoctorDAO;
import dao.PationDAO;
import entities.User;

public class UserService {

    private PationDAO pationDAO = new PationDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();
    private AdminDAO adminDAO = new AdminDAO();

    public User findByUsernameAndRole(String userName, String userRole) {
        System.out.println(userRole);
        return switch (userRole) {
            case "Doctor" -> this.doctorDAO.findByUsername(userName);
            case "Pation" -> this.pationDAO.findByUsername(userName);
            case "Admin" -> this.adminDAO.findByAnyField("userName", userName);
            default -> null;
        };
    }


    public boolean updateField(String filterField, String filterValue, String updateField, String updateValue, String userRole) {
        return switch (userRole) {
            case "Doctor" -> this.doctorDAO.updateDoctor(filterField, filterValue, updateField, updateValue);
            case "Pation" -> this.pationDAO.updatePation(filterField, filterValue, updateField, updateValue);
            case "Admin" -> this.adminDAO.updateField(filterField, filterValue, updateField, updateValue);
            default -> false;
        };
    }

    /*public User findByCni(long cni) {

    }
*/
}
