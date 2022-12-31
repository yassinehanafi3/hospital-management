package presentation.controllers.doctordashboard.menus;

import entities.Appointment;
import entities.Pation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import presentation.controllers.doctordashboard.DoctorDashboardController;
import services.AppointmentService;

import java.net.URL;
import java.util.ResourceBundle;


public class PationController implements Initializable {
    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label cni;
    @FXML
    private DatePicker day;
    @FXML
    private TextField hour;
    @FXML
    private ListView<Appointment> sessions;
    @FXML
    private ListView<Appointment> apps;
    private Pation pation;
    ObservableList<Appointment> appointments1 = FXCollections.observableArrayList();
    ObservableList<Appointment> appointments2 = FXCollections.observableArrayList();
    AppointmentService service = new AppointmentService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pation = PationsController.getInstance().getPatioInstance();
        firstName.setText(pation.getFirstName());
        lastName.setText(pation.getLastName());
        phone.setText(pation.getPhone());
        email.setText(pation.getEmail());
        cni.setText(pation.getCni());
        loadAppointments();
        sessions.setItems(appointments1);
        apps.setItems(appointments2);
    }
    public void loadAppointments(){
        for (Appointment appointment: service.getAllAppointments()
             ) {
            if (appointment.getAppointmentNote().length()>0){
                appointments1.add(appointment);
            }else {
                appointments2.add(appointment);
            }
        }
    }
}
