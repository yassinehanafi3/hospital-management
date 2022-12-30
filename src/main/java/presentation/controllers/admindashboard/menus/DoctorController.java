package presentation.controllers.admindashboard.menus;

import entities.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.DoctorService;
import services.DoctorSpecialityService;

import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import static config.Helpers.DateToLocalDate;
import static config.Helpers.LocalDateToDate;

public class DoctorController implements Initializable {

    @FXML private TextField cni;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField userName;
    @FXML private DatePicker birthDate;
    @FXML private ComboBox<String> doctorSpeciality;
    @FXML private TextField searchDoctor;


    @FXML private TableView<Doctor> doctorTable;
    @FXML private TableColumn<Doctor, String> cniColumn;
    @FXML private TableColumn<Doctor, String> nomColumn;
    @FXML private TableColumn<Doctor, String> prenomColumn;
    @FXML private TableColumn<Doctor, String> usernameColumn;
    @FXML private TableColumn<Doctor, String> specialityColumn;
    @FXML private TableColumn<Doctor, Date> dobColumn;

    private ObservableList<Doctor> doctors = FXCollections.observableArrayList();
    private ObservableList<String> specialities = FXCollections.observableArrayList();
    private DoctorService doctorService = new DoctorService();
    private DoctorSpecialityService doctorSpecialityService = new DoctorSpecialityService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reloadData();
    }

    public void reloadData() {
        loadSpecialities();
        cniColumn.setCellValueFactory(new PropertyValueFactory<>("cni"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        specialityColumn.setCellValueFactory(new PropertyValueFactory<>("doctorSpecialityId"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        doctorTable.getSelectionModel().selectedIndexProperty().addListener((o, oldValue, newValue) -> {
            if (newValue.intValue() >= 0) this.setFormText(doctorTable.getSelectionModel().getSelectedItem());
        });

        loadDoctors();

        doctorTable.setItems(this.doctors);
    }

    private void loadSpecialities() {
        this.specialities.clear();
        this.specialities.addAll(this.doctorSpecialityService.getListDoctorSpecialitiesLabels());
        this.doctorSpeciality.setItems(specialities);
    }

    private void loadDoctors() {
        for (Doctor doctor : this.doctorService.getAllDoctors()) {
            System.out.println(doctor);
        }
        this.doctors.clear();
        this.doctors.addAll(this.doctorService.getAllDoctors());
    }

    private void loadDoctorsContains(String text) {
        this.doctors.clear();
        this.doctors.addAll(this.doctorService.findContains(text));
    }

    private void setFormText(Doctor doctor) {
        if (doctor != null ) {
            this.cni.setText(doctor.getCni());
            this.firstName.setText(doctor.getFirstName());
            this.lastName.setText(doctor.getLastName());
            this.userName.setText(doctor.getUserName());
            this.birthDate.setValue(DateToLocalDate(doctor.getBirthDate()));
            this.doctorSpeciality.getSelectionModel().select(this.doctorSpecialityService.getDoctorSpecialityById(doctor.getDoctorSpecialityId()).getDoctorSpecialityLabel());
        }
    }

    @FXML protected void onAddDoctor() {
        Doctor doctor = this.ConvertFormToDoctor(new Doctor());
        if (doctor != null) {
            if (this.doctorService.addDoctor(doctor) != null) {
                new Alert(Alert.AlertType.CONFIRMATION, "Docteur ajoutée avec succée", ButtonType.OK).show();
                loadDoctors();
            }
        }
    }

    @FXML protected void onModifyDoctor() {
        Doctor doctor = this.doctorTable.getSelectionModel().getSelectedItem();
        if (doctor == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez selectionner d'abord la ligne à modifier !!");
            alert.show();
        }
        else {
            doctor = ConvertFormToDoctor(doctor);
            if (doctor != null) {
                if (this.doctorService.update(doctor)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Docteur modifée avec succée", ButtonType.OK);
                    alert.show();
                    loadDoctors();
                    //this.clearForm();
                }
            }
        }
    }
    @FXML protected void onDeleteDoctor() {
        Doctor doctor = this.doctorTable.getSelectionModel().getSelectedItem();
        if (doctor == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez selectionner d'abord la ligne à modifier !!");
            alert.show();
        } else {
            if (this.doctorService.deleteDoctorUsingCni(doctor.getCni())) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Docteur supprimée avec succée", ButtonType.OK);
                alert.show();
                loadDoctors();
            }
        }
    }

    @FXML protected void onSearchDoctor() {
        if (!searchDoctor.getText().isEmpty()) {
            System.out.println(searchDoctor.getText());
            loadDoctorsContains(searchDoctor.getText());
        } else {
            loadDoctors();
        }
    }


    private Doctor ConvertFormToDoctor(Doctor doctor) {
        String doctorCni = cni.getText();
        String doctorFirstName = firstName.getText();
        String doctorLastName = lastName.getText();
        String doctorUserName = userName.getText();
        String doctorSpeciality = this.doctorSpeciality.getSelectionModel().getSelectedItem();
        Date doctorDateBirth = LocalDateToDate(this.birthDate.getValue());

        if (!doctorCni.isEmpty() && !doctorFirstName.isEmpty() && !doctorLastName.isEmpty() && !doctorUserName.isEmpty() && !doctorSpeciality.isEmpty() && doctorDateBirth != null) {
            doctor.setCni(doctorCni);
            doctor.setFirstName(doctorFirstName);
            doctor.setLastName(doctorLastName);
            doctor.setUserName(doctorUserName);
            doctor.setDoctorSpecialityId(this.doctorSpecialityService.getDoctorSpecialityByName(doctorSpeciality).getDoctorSpecialityId());
            doctor.setBirthDate(doctorDateBirth);
            return doctor;
        }
        new Alert(Alert.AlertType.WARNING, "Veuillez remplir tout le formulaire !!", ButtonType.OK).show();
        return null;
    }

}
