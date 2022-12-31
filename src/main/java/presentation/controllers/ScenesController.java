package presentation.controllers;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import presentation.components.MyScene;

import java.io.IOException;

public class ScenesController {

    private MyScene scene;

    public void loadMainScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/main.fxml"));
        scene = new MyScene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void loadAdminDashboard(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/admindashboard/admindashboard.fxml"));
        scene = new MyScene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void loadPationDashboard(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/pationdashboard/pationdashboard.fxml"));
        scene = new MyScene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void loadDoctorDashboard(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/dashboards/doctordashboard/doctordashboard.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/main.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/admindashboard/admindashboard.fxml"));
        scene = new MyScene(fxmlLoader.load(), 1224, 768);
        stage.setScene(scene);
        stage.show();
    }

}
