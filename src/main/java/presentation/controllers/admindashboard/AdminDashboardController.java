package presentation.controllers.admindashboard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import presentation.controllers.admindashboard.menus.DashboardController;
import presentation.controllers.admindashboard.menus.DoctorController;
import presentation.controllers.admindashboard.mediator.AdminDashboardMediator;
import presentation.services.RedisService;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML private TabPane tabPane;

    private RedisService redisService = new RedisService();

    @FXML private DashboardController dashboardController;
    @FXML private DoctorController doctorController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AdminDashboardMediator.getInstance().setDashboardController(dashboardController);
        AdminDashboardMediator.getInstance().setDoctorController(doctorController);
    }

    @FXML protected void loadDashboard() {
        AdminDashboardMediator.getInstance().updateDataOnDashboard();
        this.tabPane.getSelectionModel().select(0);
    }

    @FXML protected void loadDoctorsMenu() {
        AdminDashboardMediator.getInstance().updateDataOnDoctor();
        this.tabPane.getSelectionModel().select(1);
    }
    @FXML protected void loadPationsMenu() {
        //AdminDashboardMediator.getInstance().updateDataOnPation();
        this.tabPane.getSelectionModel().select(2);
    }

    @FXML protected void loadAppointmentsMenu() {
        //AdminDashboardMediator.getInstance().updateDataOnAppointment();
        this.tabPane.getSelectionModel().select(3);
    }

    @FXML protected void disconnect() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Vous voullez vraiment vous déconnecter ?", ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            this.redisService.removeUser();
            System.exit(0);
        }
    }

}
