package presentation.controllers.admindashboard.mediator;

import presentation.controllers.admindashboard.menus.DashboardController;
import presentation.controllers.admindashboard.menus.DoctorController;

public class AdminDashboardMediator {

    private DashboardController dashboardController;
    private DoctorController doctorController;


    public void setDashboardController(DashboardController controller) {
        dashboardController = controller;
    }

    public void setDoctorController(DoctorController controller) {
        doctorController = controller;
    }

    public void updateDataOnDashboard() {
        dashboardController.reloadData();
    }

    public void updateDataOnDoctor() {
        doctorController.reloadData();
    }


    private AdminDashboardMediator() {}

    public static AdminDashboardMediator getInstance() {
        return AdminDashboardMediatorHolder.INSTANCE;
    }

    private static class AdminDashboardMediatorHolder {
        private static final AdminDashboardMediator INSTANCE = new AdminDashboardMediator();
    }
}
