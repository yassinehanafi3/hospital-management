package entities;



import java.text.SimpleDateFormat;
import java.util.Date;


public class AhmedAppointments {
    private String nom;
    private  String prenom;
    private  String tele;
    private  String email;
    private String day;
    private String hour;
    private Appointment appointment = new Appointment();

    public AhmedAppointments(Date date, Pation pation,Appointment appointment) {
        this.day =new SimpleDateFormat("dd/MM/YYYY").format(date);
        this.hour =new SimpleDateFormat("HH:MM").format(date);
        this.nom= pation.getLastName();
        this.prenom=pation.getFirstName();
        this.email=pation.getEmail();
        this.tele= pation.getPhone();
        this.appointment = appointment;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}