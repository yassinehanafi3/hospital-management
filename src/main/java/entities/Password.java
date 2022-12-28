package entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Password implements Serializable {

    public static String GetHash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // Convert byte array into signum representation
        BigInteger no = new BigInteger(1, hash);

        // Convert message digest into hex value
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }

        return hashtext;
    }
    static boolean CheckEq(String pass1,String pass2){
        return Objects.equals(pass1,pass2);
    }

}
