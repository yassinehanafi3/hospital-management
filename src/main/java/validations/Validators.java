package validations;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import static entities.Password.GetHash;

public class Validators {


    public static boolean isNumeric(String value) {
        boolean numeric = true;
        try {
            Double num = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            numeric = false;
        }

        return numeric;
    }

    public static boolean isEmail(String email) {
        return email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }

    public static boolean isMatch(String first, String second) {
        return first.equals(second);
    }

    public static boolean isValidDateFuture(Date date) {
        if (!date.after(new Date())) {
            return false;
        }
        return true;
    }

    public static boolean isValidDatePast(Date date) {
        if (!date.before(new Date())) {
            return false;
        }
        return true;
    }

    public static boolean isValidDateAboveLegal(Date date) {
        Date dateNow = new Date();
        if (dateNow.getYear() - date.getYear() < 18) {
            return false;
        }
        return true;
    }


    public static boolean isValidPassword(String password)
    {
        boolean isValid = true;
        if (password.length() > 20 || password.length() < 8)
        {
            isValid = false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars ))
        {
            isValid = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars ))
        {
            isValid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers ))
        {
            isValid = false;
        }
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!password.matches(specialChars))
        {
            isValid = false;
        }
        return isValid;
    }


    public static boolean isPasswordsMatch(String rawPassword,String hashedPassword) throws NoSuchAlgorithmException {
        System.out.println(hashedPassword);
        System.out.println(GetHash(rawPassword));
        return hashedPassword.equals(GetHash(rawPassword));
    }




}
