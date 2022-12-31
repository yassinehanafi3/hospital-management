package presentation.shared;

import entities.User;
import presentation.services.RedisService;


public class GlobalVariables {

    public static final int LOGIN_FRAME_HEIGHT;
    public static final int LOGIN_FRAME_WIDTH;
    public static final int DASHBOARD_FRAME_HEIGHT;
    public static final int DASHBOARD_FRAME_WIDTH;

    public static User CURRENT_USER = null;

    static {
        LOGIN_FRAME_WIDTH = 1024;
        LOGIN_FRAME_HEIGHT = 768;
        DASHBOARD_FRAME_HEIGHT = 768;
        DASHBOARD_FRAME_WIDTH = 1224;
        CURRENT_USER = new RedisService().getCurrentUser();;
    }

    public static void setCurrentUser(User currentUser) {
        CURRENT_USER = currentUser;
    }

    public static void removeCurrentUser() {CURRENT_USER = null;}
}

