package controllers;

import entities.*;
import services.AdminService;
import services.DoctorService;
import services.PationService;

import java.security.NoSuchAlgorithmException;


public class AuthController {
    private DoctorService doctorService;
    private PationService pationService;
    private AdminService adminService;

    public boolean authenticate(String userName, String userPassword, String collection) throws NoSuchAlgorithmException {
        if (collection.equals("Doctor")) {
            return this.doctorLogin(userName, userPassword);
        }
        else if (collection.equals("Pation")) {
            return this.pationLogin(userName, userPassword);
        } else if (collection.equals("Admin")) {
            return this.adminLogin(userName, userPassword);
        }
        return false;
    }

    private boolean doctorLogin(String userName, String userPassword) throws NoSuchAlgorithmException {
        doctorService = new DoctorService();
        Doctor doctor = doctorService.getDoctorByField("userName", userName);
        if (doctor == null) {
            return false;
        } else {
            String hashedPassword = Password.GetHash(userPassword);
            return doctor.getUserPassword().equals(hashedPassword);
        }
    }

    private boolean pationLogin(String userName, String userPassword) throws NoSuchAlgorithmException {
        pationService = new PationService();
        Pation pation = pationService.getPationByField("userName", userName);
        if (pation == null) {
            return false;
        } else {
            String hashedPassword = Password.GetHash(userPassword);
            return pation.getUserPassword().equals(hashedPassword);
        }
    }

    private boolean adminLogin(String userName, String userPassword) throws NoSuchAlgorithmException {
        adminService = new AdminService();
        Admin admin = adminService.getAdminByField("userName", userName);
        if (admin == null) {
            return false;
        } else {
            String hashedPassword = Password.GetHash(userPassword);
            return admin.getUserPassword().equals(hashedPassword);
        }
    }

}
