package presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import presentation.services.RedisService;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML private TabPane tabPane;

    private RedisService redisService = new RedisService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML protected void loadDashboard() {
        this.tabPane.getSelectionModel().select(0);
    }

    @FXML protected void loadDoctorsMenu() {
        this.tabPane.getSelectionModel().select(1);
    }
    @FXML protected void loadPationsMenu() {
        this.tabPane.getSelectionModel().select(2);
    }

    @FXML protected void loadAppointmentsMenu() {
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
