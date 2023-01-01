package presentation.controllers.doctordashboard.mediator;

import entities.Pation;
import javafx.scene.control.TabPane;
import presentation.controllers.doctordashboard.menus.*;

public class DoctorDashboardMediator {
    private AppointmentsController appointments;
    private PationController pation;
    private PationsController pations;

    private TabPane tabPane;


    public void setAppointments(AppointmentsController appointments) {
        this.appointments = appointments;
    }

    public void setPation(PationController pation) {
        this.pation = pation;
    }

    public void setPations(PationsController pations) {
        this.pations = pations;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public void loadPation() {
        setSelectedPation();
        this.tabPane.getSelectionModel().select(2);
    }

    public void setSelectedPation() {
        this.pation.setPation(this.getSelectedPation());
    }

    public Pation getSelectedPation() {
        return this.pations.getSelectedPation();
    }

    public DoctorDashboardMediator() {}


    public static DoctorDashboardMediator getInstance() {
        return DoctorDashboardMediator.DoctorDashboardMediatorHolder.INSTANCE;
    }

    private static class DoctorDashboardMediatorHolder {
        private static final DoctorDashboardMediator INSTANCE = new DoctorDashboardMediator();
    }

}
