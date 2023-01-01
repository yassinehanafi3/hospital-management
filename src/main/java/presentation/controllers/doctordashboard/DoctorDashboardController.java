package presentation.controllers.doctordashboard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import presentation.controllers.admindashboard.mediator.AdminDashboardMediator;
import presentation.controllers.doctordashboard.mediator.DoctorDashboardMediator;
import presentation.controllers.doctordashboard.menus.AppointmentsController;
import presentation.controllers.doctordashboard.menus.DashboardController;
import presentation.controllers.doctordashboard.menus.PationController;
import presentation.controllers.doctordashboard.menus.PationsController;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorDashboardController implements Initializable {
    @FXML
    public TabPane tabPane;


    @FXML private PationController pationController;
    @FXML private PationsController pationsController;
    @FXML private DashboardController dashboardController;
    @FXML private AppointmentsController appointmentsController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DoctorDashboardMediator.getInstance().setAppointments(appointmentsController);
        DoctorDashboardMediator.getInstance().setPation(pationController);
        DoctorDashboardMediator.getInstance().setPations(pationsController);
        DoctorDashboardMediator.getInstance().setTabPane(tabPane);
    }

    @FXML protected void loadAppointmentsMenu() {
        System.out.println("Clicked appointments menu");
        this.tabPane.getSelectionModel().select(0);
    }
    @FXML protected void loadPationsMenu() {
        System.out.println("Clicked pations menu");
        this.tabPane.getSelectionModel().select(1);
    }

    @FXML protected void loadAccountMenu() {
        System.out.println("Clicked account menu");
        this.tabPane.getSelectionModel().select(3);
    }

    public static DoctorDashboardController getInstance() {
        return DoctorDashboardController.DoctorDashboardControllerHolder.INSTANCE;
    }

    private static class DoctorDashboardControllerHolder {
        private static final DoctorDashboardController INSTANCE = new DoctorDashboardController();
    }
    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }


}
