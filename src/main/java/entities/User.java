package entities;

import org.bson.types.ObjectId;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private ObjectId _id;
    private String firstName;
    private String lastName;
    private String cni;
    private String userName;
    private String userPassword;
    private String phone;
    private Date birthDate;
    private String userRole;

    public User() {}

    public User(ObjectId id, String firstName, String lastName, String cni, String userName, String userPassword, String phone, Date birthDate, String userRole) {
        this._id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cni = cni;
        this.userName = userName;
        this.userPassword = userPassword;
        this.phone = phone;
        this.birthDate = birthDate;
        this.userRole = userRole;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cni='" + cni + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate=" + birthDate +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
