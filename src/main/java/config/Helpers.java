package config;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;

public class Helpers<T> {


    public static Gson GSON;

    static  {
        GSON = new Gson();
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

    public static LocalDate DateToLocalDate(Date date) {
        if (date != null) {
            Instant instant = date.toInstant();
            return instant.atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return null;
    }

    public static Date LocalDateToDate(LocalDate date) {
        if (date != null) {
            Instant instant = Instant.from(date.atStartOfDay(ZoneId.systemDefault()));
            return Date.from(instant);
        }
        return null;
    }

    public static Date toMongoDbDate(Date date) {
        try {
            DateFormat format = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss", Locale.ENGLISH);
            date = format.parse(date.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;

    }


}
