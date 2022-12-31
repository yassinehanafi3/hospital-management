package entities;

import java.util.Date;

public class Admin extends User {

    public Admin(String firstName, String lastName, String cni, String userName, String userPassword, String phone, Date birthDate, String userRole) {
        super(firstName, lastName, cni, userName, userPassword, phone, birthDate, userRole);
    }


}
