package presentation.controllers.doctordashboard.mediator;

import presentation.controllers.doctordashboard.menus.*;

public class DoctorDashboardMediator {
    private AppointmentsController appointments;
    private PationController pation;
    private PationsController pations;



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

    private static class DoctorDashboardMediatorHolder {
        private static final DoctorDashboardMediator INSTANCE = new DoctorDashboardMediator();
    }
}
