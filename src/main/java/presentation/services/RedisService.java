package presentation.services;

import config.ConnectRedis;
import entities.User;
import services.DoctorService;
import services.PationService;
import com.lambdaworks.redis.RedisConnection;

public class RedisService {

    private RedisConnection<String, String> connection = ConnectRedis.getConnection();
    private DoctorService doctorService;
    private PationService pationService;

    public RedisService() {
        doctorService = new DoctorService();
        pationService = new PationService();
    }


    public void saveCurrentUser(User user) {
        connection.set("gestionhopital:currentUser-cni", user.getCni());
        connection.set("gestionhopital:currentUser-username", user.getUserName());
        connection.set("gestionhopital:currentUser-role", user.getClass().getSimpleName());
    }

    public User getCurrentUser() {
        try {
            String cni = connection.get("gestionhopital:currentUser-cni");
            String role = connection.get("gestionhopital:currentUser-role");
            System.out.println("Role : " + role);
            User user = null;
            if (cni == null || role == null) {
                return user;
            }
            switch (role) {
                case "Doctor" -> user = doctorService.getDoctorByCni(cni);
                case "Pation" -> user = pationService.getPationByCni(cni);
            }
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public void removeUser() {
        connection.del("gestionhopital:currentUser-cni", "gestionhopital:currentUser-username", "gestionhopital:currentUser-role");
    }

}
