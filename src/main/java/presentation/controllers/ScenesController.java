package presentation.controllers;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import presentation.components.MyScene;

import java.io.IOException;

public class ScenesController {

    public void loadMainScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/main.fxml"));
        MyScene scene = new MyScene(fxmlLoader.load(), 500, 200);
        stage.setScene(scene);
        stage.show();
    }

    public void loadAdminDashboard(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/dashboards/admindashboard/admindashboard.fxml"));
        MyScene scene = new MyScene(fxmlLoader.load(), 500, 200);
        stage.setScene(scene);
        stage.show();
    }

    public void loadPationDashboard(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/dashboards/pationdashboard/pationdashboard.fxml"));
        MyScene scene = new MyScene(fxmlLoader.load(), 500, 200);
        stage.setScene(scene);
        stage.show();
    }

    public void loadDoctorDashboard(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/dashboards/doctordashboard/doctordashboard.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/main.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/dashboards/admindashboard/admindashboard.fxml"));
        MyScene scene = new MyScene(fxmlLoader.load(), 1224, 768);
        stage.setScene(scene);
        stage.show();
    }

}
