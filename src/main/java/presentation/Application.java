package presentation;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import presentation.components.MyScene;
import presentation.components.MyStage;
import java.io.IOException;


public class Application extends javafx.application.Application {

    private Stage myStage;

    @Override
    public void start(Stage stage) throws IOException {
        stage = new MyStage();
        myStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/doctordashboard/doctordashboard.fxml"));
        MyScene scene = new MyScene(fxmlLoader.load(),1224,768);
        stage.setScene(scene);
        stage.show();
    }

    public Stage getMyStage() {
        return myStage;
    }

    public static void main(String[] args) {
        launch();
    }
}