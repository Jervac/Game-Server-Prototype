package Visionire.Utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author nickbabenko
 */
public class Hash {

    public static String md5(String input) {
        String response = null;
        
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes());

            byte[] hash = digest.digest();
            response = hash.toString();
        }
        
        catch(NoSuchAlgorithmException ex) {
            Log.out(Log.ERROR, "No such algorithm - MD5", Hash.class);
        }

        return response;

    }

}