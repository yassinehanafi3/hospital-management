package entities;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Pation extends User implements Serializable {
    private Date createdAt = new Date();
    private  String email;

    public Pation() {}


    public Pation(String firstName, String lastName, String cni, String userName, String userPassword, String phone, String email,Date birthDate, String userRole) throws NoSuchAlgorithmException {
        super(firstName.toUpperCase(), lastName.toUpperCase(), cni.toUpperCase(), userName.toUpperCase(),Password.GetHash(userPassword),phone, birthDate, userRole);
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Pation{" +
                "createdAt=" + createdAt +
                "} " + super.toString();
    }
}
