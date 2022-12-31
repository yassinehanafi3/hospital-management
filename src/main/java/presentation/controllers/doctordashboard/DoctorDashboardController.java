package presentation.controllers.doctordashboard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import presentation.controllers.admindashboard.mediator.AdminDashboardMediator;
import presentation.controllers.doctordashboard.mediator.DoctorDashboardMediator;
import presentation.controllers.doctordashboard.menus.PationController;
import presentation.controllers.doctordashboard.menus.PationsController;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorDashboardController implements Initializable {
    @FXML
    public TabPane tabPane;




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
    public void loadPationMenu() {
        this.tabPane.getSelectionModel().select(3);
    }
    public static DoctorDashboardController getInstance() {
        return DoctorDashboardController.DoctorDashboardControllerHolder.INSTANCE;
    }

    private static class DoctorDashboardControllerHolder {
        private static final DoctorDashboardController INSTANCE = new DoctorDashboardController();
    }
    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }

}
