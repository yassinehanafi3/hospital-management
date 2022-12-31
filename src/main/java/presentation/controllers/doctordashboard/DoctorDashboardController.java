package presentation.controllers.doctordashboard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorDashboardController implements Initializable {
    @FXML
    private TabPane tabPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML protected void loadDashboard() {
        this.tabPane.getSelectionModel().select(0);
    }

    @FXML protected void loadAppointmentsMenu() {
        this.tabPane.getSelectionModel().select(1);
    }
    @FXML protected void loadPationsMenu() {
        this.tabPane.getSelectionModel().select(2);
    }

}
