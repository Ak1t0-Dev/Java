import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class RegisterAccount {

    public static void regsiterAccount(List<String> account) {
        try {
            List<String> linesCsvuserAccount = ManipulateCsv.readAllLineCsv(ConstData.userAccountFile);

            //  例外拾う
            if (linesCsvuserAccount == null) {
                System.out.println("IOException");
                return;
            } 

            List<String[]> nodes = new ArrayList<>();

            for (int i = 0; i < linesCsvuserAccount.size(); i++) {
                String[] array = linesCsvuserAccount.get(i).split(",");
                nodes.add(array);
            }

            // SHA3-256（SHA-3）
            MessageDigest sha3_256 = MessageDigest.getInstance("SHA3-256");
            // concate firstname + lastname + borndate + emailaddress;
            String hashUserAccount = account.get(0) + account.get(1) + account.get(2) + account.get(3);
            byte[] sha3_256_result = sha3_256.digest(hashUserAccount.getBytes());
            hashUserAccount = String.format("%040x", new BigInteger(1, sha3_256_result));
            // password;
            String hashUserAccountPassword = account.get(4);
            byte[] sha3_256_result_password = sha3_256.digest(hashUserAccountPassword.getBytes());
            hashUserAccountPassword = String.format("%040x", new BigInteger(1, sha3_256_result_password));
            account.set(5, hashUserAccountPassword);
            // emailaddress + hashedpassword;
            String hashUserAccountPart = account.get(3) + hashUserAccountPassword;
            byte[] sha3_256_result_part = sha3_256.digest(hashUserAccountPart.getBytes());
            hashUserAccountPart = String.format("%040x", new BigInteger(1, sha3_256_result_part));
            
            for (int i = 0; i < nodes.size(); i++) {
                if (nodes.get(i)[6].equals(hashUserAccount)) {
                    System.out.println("There is a same user");
                    return;
                }
            }

            // adding hash value
            account.add(hashUserAccount);
            account.add(hashUserAccountPart);

            // writingUserAccount
            ManipulateCsv.writeUserAccountCsv(account);
            ManipulateCsv.createUserCsv(account);

            System.out.println("your account are registered");
            UserLoginMenu.userLoginMenu(account, nodes);

        } catch (Exception e) {
            System.out.println("Sorry, something is wrong");
        }
    }
}
