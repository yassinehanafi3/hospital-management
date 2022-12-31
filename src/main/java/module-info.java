module com.gestionhopital_v3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires gson;
    requires lettuce;
    requires java.sql;


    exports entities;
    opens entities to gson;
    exports presentation.controllers.admindashboard;
    opens presentation.controllers.admindashboard to javafx.fxml;
    exports presentation;
    opens presentation to javafx.fxml;
    exports presentation.controllers;
    opens presentation.controllers to javafx.fxml;
    exports presentation.controllers.admindashboard.menus;
    opens presentation.controllers.admindashboard.menus to javafx.fxml;
    exports presentation.controllers.login;
    opens presentation.controllers.login to javafx.fxml;

}