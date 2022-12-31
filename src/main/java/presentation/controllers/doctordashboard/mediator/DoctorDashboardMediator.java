package presentation.controllers.doctordashboard.mediator;

import presentation.controllers.doctordashboard.DoctorDashboardController;
import presentation.controllers.doctordashboard.menus.*;

public class DoctorDashboardMediator {
    private AppointmentsController appointments;
    private PationController pation;
    private PationsController pations;
    private DoctorDashboardController doctorDashboard;



    public void setAppointments(AppointmentsController appointments) {
        this.appointments = appointments;
    }

    public void setPation(PationController pation) {
        this.pation = pation;
    }

    public void setPations(PationsController pations) {
        this.pations = pations;
    }

    public DoctorDashboardMediator() {
    }
    public static DoctorDashboardMediator getInstance() {
        return DoctorDashboardMediator.DoctorDashboardMediatorHolder.INSTANCE;
    }

    public void setDoctorDashboard(DoctorDashboardController doctorDashboard) {
        this.doctorDashboard = doctorDashboard;
    }

    private static class DoctorDashboardMediatorHolder {
        private static final DoctorDashboardMediator INSTANCE = new DoctorDashboardMediator();
    }

    public DoctorDashboardController getDoctorDashboard() {
        return doctorDashboard;
    }
}
