import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class RegisterAccount {

    public static void regsiterAccount(List<String> account) throws IOException, NoSuchAlgorithmException {

        List<String[]> nodes = new ArrayList<>();

            List<String> linesCsvuserAccount = ManipulateCsv.readAllLineCsv(ConstData.USER_ACCOUNT_FILE);

            // 共通化可能？
            for (int i = 0; i < linesCsvuserAccount.size(); i++) {
                String[] array = linesCsvuserAccount.get(i).split(",");
                nodes.add(array);
            }

            // Encrypt password
            String hashedPassword = Encrypt.userInfoEncrypt(account.get(4));
            // Encrypt hashed password and email
            String hashUserAccountPart = Encrypt.userInfoEncrypt(account.get(3) + hashedPassword);

            // email address and passport number check
            for (int i = 0; i < nodes.size(); i++) {
                // email address check
                if (nodes.get(i)[3].equals(account.get(3))) {
                    System.out.println("The emailaddress is invalid");
                    return;
                }
                // passport number check
                if (nodes.get(i)[4].equals(account.get(4))) {
                    System.out.println("The passportnumber is invalid");
                    return;
                }
            }

            // adding hash value
            account.add(hashUserAccountPart);

            // writingUserAccount
            ManipulateCsv.writeUserAccountCsv(account);
            ManipulateCsv.createUserCsv(account);
            System.out.println("your account are registered");
            UserLoginMenu.userLoginMenu(account, nodes);
    }
}
