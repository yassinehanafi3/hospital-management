package presentation.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

import static presentation.shared.GlobalVariables.*;

public class MyScene extends Scene {

    public MyScene(Parent parent) {
        super(parent, LOGIN_FRAME_WIDTH, LOGIN_FRAME_HEIGHT);
    }

    public MyScene(Parent parent, double v, double v1) {
        super(parent, v, v1);
    }

    public void setFXML(String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        MyScene scene = new MyScene(fxmlLoader.load());
    }


}
