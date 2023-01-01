package entities;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Admin extends User {

    public Admin(String firstName, String lastName, String cni, String userName, String userPassword, String phone, Date birthDate, String userRole, String email) throws NoSuchAlgorithmException {
        super(firstName, lastName, cni, userName, userPassword, phone, birthDate, userRole , email);
    }


}
