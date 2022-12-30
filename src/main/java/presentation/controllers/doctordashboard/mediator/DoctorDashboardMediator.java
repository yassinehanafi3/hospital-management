package presentation.controllers.doctordashboard.mediator;


public class DoctorDashboardMediator {


    



    private DoctorDashboardMediator() {}

    public static DoctorDashboardMediator getInstance() {
        return DoctorDashboardMediator.DoctorDashboardMediatorHolder.INSTANCE;
    }

    private static class DoctorDashboardMediatorHolder {
        private static final DoctorDashboardMediator INSTANCE = new DoctorDashboardMediator();
    }
}
