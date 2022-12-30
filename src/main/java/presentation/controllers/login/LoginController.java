package presentation.controllers.login;

import config.Helpers;
import controllers.AuthController;
import entities.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import presentation.controllers.ScenesController;
import presentation.services.RedisService;
import services.UserService;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static presentation.shared.GlobalVariables.*;

public class LoginController implements Initializable {

    @FXML private TextField userName;
    @FXML private TextField userPassword;
    @FXML private ComboBox<String> userRole;
    @FXML private Button signAsButton;
    @FXML private Button loginButton;

    @FXML private Label orLabel;


    private List<String> userRoleOptions = new ArrayList<>();
    private AuthController authController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (CURRENT_USER == null) {
            signAsButton.setVisible(false);
            orLabel.setVisible(false);
        } else {
            signAsButton.setText("Sign in as " + CURRENT_USER.getFirstName() + " " + CURRENT_USER.getLastName());
        }
        authController = new AuthController();
        userRoleOptions.add("Admin");
        userRoleOptions.add("Pation");
        userRoleOptions.add("Doctor");
        userRole.setItems(FXCollections.observableList(userRoleOptions));
    }

    @FXML protected void onSignAsClicked() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Vous voullez continuer en tant que " + CURRENT_USER.getFirstName() + " " + CURRENT_USER.getLastName());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            this.redirectTo(CURRENT_USER.getClass().getSimpleName());
        }

    }

    @FXML
    protected void onLoginButtonClicked() throws NoSuchAlgorithmException {
        String role = userRole.getSelectionModel().getSelectedItem();
        System.out.println("onLoginButtonClicked landed successfully");
        if (Helpers.isConnectedToInternet()) {
            System.out.println("Connected to Internet");
            if (userName.getText().isEmpty() || userPassword.getText().isEmpty() || userRole.getSelectionModel().isEmpty()) {
                new Alert(Alert.AlertType.WARNING,"Sorry, please fill all blanks").show();
            } else {
                if (this.authController.authenticate(userName.getText(), userPassword.getText(), role)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Vous etes connecté avec succée");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        this.saveUser(userName.getText(), userRole.getSelectionModel().getSelectedItem());
                        redirectTo(role);
                    }
                }
            }
        } else {
            new Alert(Alert.AlertType.ERROR,"Please check your connection and try again!").show();
            System.exit(0);
        }
    }

    private void redirectTo(String userRole) {
        Stage currentStage = (Stage) this.loginButton.getScene().getWindow();
        System.out.println(userRole);
        switch (userRole) {
            case "Doctor" :
                try {
                    System.out.println("Good Job Landed on Doctor");
                    new ScenesController().loadDoctorDashboard(currentStage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "Pation" :
                try {
                    System.out.println("Good Job Landed on Pation");
                    new ScenesController().loadPationDashboard(currentStage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "Admin" :
                try {
                    System.out.println("Good Job Landed on Admin");
                    new ScenesController().loadAdminDashboard(currentStage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void saveUser(String userName, String userRole) {
        System.out.println("saving user ....");
        User currentUser = new UserService().findByUsernameAndRole(userName, userRole);
        if (currentUser != null) {
            new RedisService().saveCurrentUser(currentUser);
            setCurrentUser(currentUser);
        }
    }



}
