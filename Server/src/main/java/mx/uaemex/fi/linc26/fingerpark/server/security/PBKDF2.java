package mx.uaemex.fi.linc26.fingerpark.server.security;

import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PBKDF2{

    private static final byte[] salt = new byte[]{ -110, 62, 67, -4, -79, -89, 124, 90, -94, -24, 51, -40, 17, 92, 72, 42 };

    public static String encodePassword(String password){
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return Base64.getEncoder().encodeToString(factory.generateSecret(spec).getEncoded());
        } catch (Exception e) {
            return password;
        }
    }

}
