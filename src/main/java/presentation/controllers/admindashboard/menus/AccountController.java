package presentation.controllers.admindashboard.menus;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import services.AdminService;
import services.UserService;
import validations.Validators;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import static entities.Password.GetHash;
import static presentation.shared.GlobalVariables.*;

public class AccountController implements Initializable {

    @FXML private Label cni;
    @FXML private Label fName;
    @FXML private Label lName;
    @FXML private Label uName;
    @FXML private Label dateNaissance;
    @FXML private Label phone;

    @FXML private Label errorLabel;


    @FXML private PasswordField currentPassword;
    @FXML private PasswordField newPassword;
    @FXML private PasswordField repeatNewPassword;
    @FXML private TextField newUserName;
    private AdminService adminService = new AdminService();
    private UserService userService = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setCurrentUserInformations();
        repeatNewPassword.textProperty().addListener((o, oValue, nValue) -> {
            if (!Validators.isMatch(newPassword.getText(), nValue)) {
                errorLabel.setVisible(true);
                errorLabel.setText("Le mot de passe répété doit correspondre au nouveau mot de passe");
            }else {
                errorLabel.setVisible(false);
            }
        });
    }

    private void loadData() {
        clearForm();
        setCurrentUserInformations();

    }

    private void setCurrentUserInformations() {
        cni.setText(CURRENT_USER.getCni());
        fName.setText(CURRENT_USER.getFirstName());
        lName.setText(CURRENT_USER.getLastName());
        uName.setText(CURRENT_USER.getUserName());
        phone.setText(CURRENT_USER.getPhone());
        dateNaissance.setText(new SimpleDateFormat("dd/MM/YYYY").format(CURRENT_USER.getBirthDate()));
    }


    @FXML protected void onChangePassword() throws NoSuchAlgorithmException {
        String newpassword = newPassword.getText();
        String repeatedPassword = repeatNewPassword.getText();
        String currentPasswordText = currentPassword.getText();

        System.out.println(newpassword + repeatedPassword + currentPasswordText);

        if (!newpassword.isEmpty() && !repeatedPassword.isEmpty() && !currentPasswordText.isEmpty()) {
            if (Validators.isPasswordsMatch(currentPasswordText, CURRENT_USER.getUserPassword())) {
                if (!Validators.isValidPassword(newpassword) || !Validators.isValidPassword(repeatedPassword)) {
                    if (Validators.isMatch(newpassword, currentPasswordText)) {
                        errorLabel.setText("Le nouveau mot de passe doit etre différent de l'ancien");
                    }else {
                        if (Validators.isMatch(newpassword, repeatedPassword)) {
                            if (this.userService.updateField("cni", CURRENT_USER.getCni(), "userPassword", newpassword, CURRENT_USER.getClass().getSimpleName())) {
                                CURRENT_USER.setUserPassword(GetHash(newpassword));
                                new Alert(Alert.AlertType.CONFIRMATION, "Mot de passe modifié avec succée", ButtonType.OK).show();
                                loadData();
                            }
                        }else {
                            errorLabel.setText("Le mot de passe répété doit correspondre au nouveau mot de passe");
                        }
                    }
                } else {
                    errorLabel.setText("Le mot de passe doit contenir au moins un chiffre et une lettre majuscule et minuscule, et au moins 20 caractères ou plus");
                    //new Alert(Alert.AlertType.WARNING, "Le mot de passe doit contenir au moins un chiffre et une lettre majuscule et minuscule, et au moins 20 caractères ou plus").show();
                }
            }else {
                errorLabel.setText("Le mot de passe actuel entrée est faux");
            }
        } else {
            errorLabel.setText("Veuillez remplir tout les champs");
            //new Alert(Alert.AlertType.WARNING, "Veuillez remplir tout les champs").show();
        }


    }

    @FXML protected void onChangeUsername() {
        String username = newUserName.getText();
        System.out.println("Current username : " + CURRENT_USER.getUserName());
        System.out.println("New UserName : " + username);
        if (!username.isEmpty()) {
            if (!Validators.isMatch(CURRENT_USER.getUserName(), username)) {
                if (this.userService.updateField("cni", CURRENT_USER.getCni(), "userName", username, CURRENT_USER.getClass().getSimpleName())) {
                    CURRENT_USER.setUserName(username);
                    new Alert(Alert.AlertType.CONFIRMATION, "Nom d'utilisateur modifié avec succée", ButtonType.OK).show();
                    loadData();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Donnez un nom d'utilisateur pas comme l'ancien").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Veuillez remplir le nouveau nom d'utilisateur").show();
        }
    }

    private void clearForm() {
        this.newUserName.clear();
    }

}
