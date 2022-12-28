package presentation.controllers.admindashboard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import services.DoctorService;
import services.PationService;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML private Label numberDoctors;
    @FXML private Label numberPations;
    @FXML private Label numberAppointments;

    private DoctorService doctorService = new DoctorService();
    private PationService pationService = new PationService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*this.numberDoctors.setText(String.valueOf(doctorService.getAllDoctors().size()));
        this.numberPations.setText(String.valueOf(pationService.getAllPations().size()));*/
        this.numberDoctors.setText("100");
        this.numberPations.setText("10");
        this.numberAppointments.setText("1250");
    }
}
