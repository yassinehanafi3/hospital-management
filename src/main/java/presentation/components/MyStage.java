package presentation.components;

import javafx.stage.Stage;

public class MyStage extends Stage {

    public MyStage () {
        this.setTitle("Al Mohammedia Hospital");
        this.setResizable(false);
        this.setOnCloseRequest(t -> System.exit(0));
    }

}
