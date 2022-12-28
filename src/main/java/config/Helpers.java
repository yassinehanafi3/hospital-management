package config;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Helpers<T> {


    private static Gson gson;

    static  {
        gson = new Gson();
    }

    public static boolean isConnectedToInternet() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch(MalformedURLException e) {
            throw new RuntimeException(e);
        } catch(IOException e) {
            return false;
        }
    }


}
