package presentation.controllers.doctordashboard.menus;

import entities.AhmedAppointments;
import entities.Appointment;
import entities.Pation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import presentation.controllers.doctordashboard.DoctorDashboardController;
import presentation.controllers.doctordashboard.mediator.DoctorDashboardMediator;
import services.PationService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PationsController implements Initializable {
    @FXML
    private TextField search;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField cni;
    @FXML
    private TableView<Pation> tPations;
    @FXML
    private TableColumn<Pation,String> cNom;
    @FXML
    private TableColumn<Pation,String> cPrenom;
    @FXML
    private TableColumn<Pation,String> cPhone;
    @FXML
    private TableColumn<Pation,String> cEmail;
    ObservableList<Pation> pations = FXCollections.observableArrayList();
    PationService pationService = new PationService();
    private Pation patioInstance = new Pation();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cNom.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        cPrenom.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        cPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        loadPations();
        tPations.setItems(pations);
    }
    public void loadPations(){
        pations.clear();
        pations.addAll(pationService.getAllPations());
    }
    public void addPation(){
        String firstName = prenom.getText();
        String lastName = nom.getText();
        String tele = phone.getText();
        String Email = email.getText();
        String Cni = cni.getText();
        if(firstName.length()==0 || lastName.length()==0 || tele.length()==0 || Email.length() ==0|| Cni.length() ==0){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Veuillez remplir tous les champs", ButtonType.OK);
            alert.show();
        }else {
            Pation pation = new Pation();
            pation.setEmail(Email);
            pation.setCni(Cni);
            pation.setPhone(tele);
            pation.setFirstName(firstName);
            pation.setLastName(lastName);
            pationService.addPation(pation);
            clearText();
            loadPations();
        }
    }
    public void deletePation(){
        Pation pation = new Pation();
        if ( tPations.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Veuillez sélectionner un patient", ButtonType.OK);
            alert.show();
        }else{
            pation = tPations.getSelectionModel().getSelectedItem();
            pationService.deletePation(pation.getCni());
            loadPations();
        }
    }
    public void searchPations(){
        List<Pation> pationList = new ArrayList<>();
        String Search = search.getText().trim();
        for (Pation pation:pationService.getAllPations() ) {
            String pool = pation.getFirstName()+" "+pation.getLastName();
            if (pool.toLowerCase().contains(Search.toLowerCase())){
                pationList.add(pation);
            }
        }
        pations.clear();
        pations.addAll(pationList);
    }
    public void clearText(){
        nom.setText("");
        prenom.setText("");
        email.setText("");
        phone.setText("");
        cni.setText("");
    }
    public void selectedPation(){
        if(patioInstance == null){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Veuillez sélectionner un patient", ButtonType.OK);
            alert.show();
        }else {
            this.patioInstance = tPations.getSelectionModel().getSelectedItem();

        }
    }
    public static PationsController getInstance() {
        return PationsController.PationsControllerHolder.INSTANCE;
    }

    private static class PationsControllerHolder {
        private static final PationsController INSTANCE = new PationsController();
    }

    public Pation getPatioInstance() {
        return patioInstance;
    }
}
