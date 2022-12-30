package presentation.controllers.admindashboard.menus;

import entities.Appointment;
import entities.Doctor;
import entities.Pation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import services.AppointmentService;
import services.DoctorService;
import services.PationService;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML private Label numberDoctors;
    @FXML private Label numberPations;
    @FXML private Label numberAppointments;

    @FXML private PieChart pieChart;
    @FXML private LineChart<Date, Integer> lineChart;

    @FXML private ListView<String> newlyAddedDoctors;
    @FXML private ListView<String> newlyAddedPations;


    private DoctorService doctorService = new DoctorService();
    private PationService pationService = new PationService();
    private AppointmentService appointmentService = new AppointmentService();
    private List<Doctor> doctorList = new ArrayList<>();
    private List<Pation> pationList = new ArrayList<>();
    private List<Appointment> appointmentsList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.reloadData();

    }

    private void setStatistics() {
        this.numberDoctors.setText(String.valueOf(doctorList.size()));
        this.numberPations.setText(String.valueOf(pationList.size()));
        this.numberAppointments.setText("1250");
    }

    private void setListViews() {
        ObservableList<String> newDoctors = FXCollections.observableArrayList();
        ObservableList<String> newPations = FXCollections.observableArrayList();
        for (Doctor doctor :doctorList) {
            newDoctors.add("Le docteur " + doctor.getFirstName() + " " + doctor.getLastName() + " a été ajoutée le " + doctor.getCreatedAt().getDay() + "/" + doctor.getCreatedAt().getMonth() + "/" + doctor.getCreatedAt().getYear());
        }
        for (Pation pation :pationList) {
            newPations.add("Le pation " + pation.getFirstName() + " " + pation.getLastName() + " a été ajoutée le " + pation.getCreatedAt());
        }
        newlyAddedDoctors.setItems(newDoctors);
        newlyAddedPations.setItems(newPations);
    }

    private void setPieChart() {
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Doctors", doctorList.size()),
                new PieChart.Data("Pations", pationList.size())
        );
        pieChart.setData(pieData);
    }


    public void reloadData() {
        this.doctorList = doctorService.getAllDoctors();
        this.pationList = pationService.getAllPations();
        this.appointmentsList = appointmentService.getAllAppointments();
        this.setStatistics();
        this.setPieChart();
        this.setListViews();
    }

}
