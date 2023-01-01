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

import static presentation.shared.GlobalVariables.CURRENT_USER;


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
    @FXML private TextArea note;
    @FXML
    private ListView<Appointment> sessions;
    @FXML
    private ListView<Appointment> apps;
    private Pation pation = new Pation();
    ObservableList<Appointment> appointments1 = FXCollections.observableArrayList();
    ObservableList<Appointment> appointments2 = FXCollections.observableArrayList();
    AppointmentService service = new AppointmentService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //pation = PationsController.getInstance().getPatioInstance();
        loadData();
    }
    public void loadAppointments(){
        appointments1.clear();
        appointments2.clear();
        for (Appointment appointment: service.getAllAppointmentsByDoctorAndPation(CURRENT_USER, pation)
             ) {
            if (appointment.getAppointmentNote().length()>0){
                appointments1.add(appointment);
            }else {
                appointments2.add(appointment);
            }
        }
    }

    public void loadData() {
        firstName.setText(pation.getFirstName());
        lastName.setText(pation.getLastName());
        phone.setText(pation.getPhone());
        email.setText(pation.getEmail());
        cni.setText(pation.getCni());
        loadAppointments();
        sessions.setItems(appointments1);
        apps.setItems(appointments2);

    }

    public void setPation(Pation pation) {
        this.pation = pation;
        System.out.println(pation);
        loadData();
    }

    @FXML protected void addNote() {
        String Note = note.getText().trim();
        Appointment appointment = this.apps.getSelectionModel().getSelectedItem();
        System.out.println(Note);
        System.out.println(appointment.getAppointmentId());
        if (appointment == null) {
            new Alert(Alert.AlertType.WARNING,"Veuillez selectionner un rendez-vous d'abord !", ButtonType.OK).show();
        }else {
            if (Note.length()==0){
                new Alert(Alert.AlertType.WARNING,"Veuillez remplir note", ButtonType.OK).show();
            }else {
                if (service.updateRDVById(appointment.getAppointmentId(),"appointmentNote", Note)) {
                    new Alert(Alert.AlertType.CONFIRMATION,"Note ajoutée avec succée", ButtonType.OK).show();
                    loadAppointments();
                }

            }
        }
    }

}
