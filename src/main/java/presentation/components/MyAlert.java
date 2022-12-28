package presentation.components;

import javafx.scene.control.Alert;

public class MyAlert extends Alert {


    public MyAlert(AlertType alertType) {
        super(alertType);
        //this.getOwner()
    }

    public MyAlert(AlertType alertType, String s) {
        super(alertType, s);
    }


}
