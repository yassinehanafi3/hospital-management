package presentation.shared;

import entities.User;
import presentation.services.RedisService;


public class GlobalVariables {

    public static final int FRAME_HEIGHT;
    public static final int FRAME_WIDTH;

    public static User CURRENT_USER = null;

    static {
        FRAME_WIDTH = 1024;
        FRAME_HEIGHT = 768;
        CURRENT_USER = new RedisService().getCurrentUser();
    }

    public static void setCurrentUser(User currentUser) {
        CURRENT_USER = currentUser;
    }
}

