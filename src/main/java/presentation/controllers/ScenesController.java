package presentation.controllers;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import presentation.components.MyScene;

import java.io.IOException;

import static presentation.shared.GlobalVariables.*;

public class ScenesController {

    private MyScene scene;

    public void loadMainScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/main.fxml"));
        scene = new MyScene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void loadLoginScene(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/login/login.fxml"));
            scene = new MyScene(fxmlLoader.load(), LOGIN_FRAME_WIDTH, LOGIN_FRAME_HEIGHT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.show();
    }

    public void loadAdminDashboard(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/admindashboard/admindashboard.fxml"));
        scene = new MyScene(fxmlLoader.load(), DASHBOARD_FRAME_WIDTH, DASHBOARD_FRAME_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    public void loadPationDashboard(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/pationdashboard/pationdashboard.fxml"));
        scene = new MyScene(fxmlLoader.load(), DASHBOARD_FRAME_WIDTH, DASHBOARD_FRAME_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    public void loadDoctorDashboard(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/doctordashboard/doctordashboard.fxml"));
        scene = new MyScene(fxmlLoader.load(), DASHBOARD_FRAME_WIDTH, DASHBOARD_FRAME_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

}
