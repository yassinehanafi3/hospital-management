package presentation.controllers.doctordashboard.menus;

import entities.AhmedAppointments;
import entities.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.AppointmentService;
import services.PationService;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {
    @FXML
    private TextField search;
    @FXML
    private TableView<AhmedAppointments> tAppointment;
    @FXML
    private TableColumn<AhmedAppointments,String> fName;
    @FXML
    private TableColumn<AhmedAppointments,String> lName;
    @FXML
    private TableColumn<AhmedAppointments,String> email;
    @FXML
    private TableColumn<AhmedAppointments,String> phone;
    @FXML
    private TableColumn<AhmedAppointments, String> date;
    @FXML
    private TableColumn<AhmedAppointments, String> hour;

    ObservableList<AhmedAppointments> appointments = FXCollections.observableArrayList();
    private AppointmentService appointmentService = new AppointmentService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       lName.setCellValueFactory(new PropertyValueFactory<>("nom"));
        fName.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        phone.setCellValueFactory(new PropertyValueFactory<>("tele"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        date.setCellValueFactory(new PropertyValueFactory<>("day"));
        hour.setCellValueFactory(new PropertyValueFactory<>("hour"));
        loadAppointments();
        tAppointment.setItems(appointments);

    }
    public void loadAppointments(){
        appointments.clear();
        PationService service = new PationService();
        for (Appointment appointment:appointmentService.getAllAppointments()
             ) {
            AhmedAppointments ahmed = new AhmedAppointments(appointment.getAppointmentDate(),service.getPationByCni(appointment.getAppointmentPationCni()),appointment);
            appointments.add(ahmed);
        }
    }
    public void deleteAppointment(){
        Appointment appointment = new Appointment();
        if ( tAppointment.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Veuillez sélectionner un rendez-vous", ButtonType.OK);
            alert.show();
        }else{
            appointment = tAppointment.getSelectionModel().getSelectedItem().getAppointment();
            appointmentService.deleteAppointmentById(appointment.getAppointmentId());
            loadAppointments();
        }
    }
}
