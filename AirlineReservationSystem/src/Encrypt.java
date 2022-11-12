import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
    public static String userInfoEncrypt(String beforeHash) {
        try {
            // SHA3-256（SHA-3）
            MessageDigest sha3_256 = MessageDigest.getInstance("SHA3-256");
            String hashed = beforeHash;
            byte[] sha3_256_result_password = sha3_256.digest(hashed.getBytes());
            hashed = String.format("%040x", new BigInteger(1, sha3_256_result_password));
            return hashed;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
