package presentation.controllers.doctordashboard.menus;

import entities.Appointment;
import entities.Doctor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import services.AppointmentService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static presentation.shared.GlobalVariables.*;

public class CalendarController implements Initializable {


    @FXML private Pane EightNinePane;
    @FXML private Pane NineTenPane;
    @FXML private Pane TenElevenPane;
    @FXML private Pane ElevenTwelvePane;
    @FXML private Pane TwelveThirteenPane;
    @FXML private Pane ThirteenFourteenPane;
    @FXML private Pane FourteenFifteenPane;
    @FXML private Pane FifteenSixteenPane;

    @FXML private Button next, back;

    private AppointmentService appointmentService = new AppointmentService();

    private List<Appointment> doctorAppointments = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDoctorAppointments();
        for (Appointment app: this.doctorAppointments) {
            System.out.println(app);
        }
    }

    private void loadDoctorAppointments() {
        this.doctorAppointments.clear();
        this.doctorAppointments.addAll(this.appointmentService.getAllAppointmentsByDoctor((Doctor) CURRENT_USER));
    }

}
