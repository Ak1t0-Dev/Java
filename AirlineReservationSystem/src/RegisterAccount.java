import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class RegisterAccount {

    public static void regsiterAccount(List<String> account) throws IOException, NoSuchAlgorithmException {
        // read the csv and convert to List<String[]>
        List<String> linesCsvuserAccount = ManipulateCsv.readAllLinesCsv(ConstData.USER_ACCOUNT_FILE);
        List<String[]> allLines = ManipulateCsv.splitAllLines(linesCsvuserAccount);

        // Encrypt password
        String hashedPassword = Encrypt.userInfoEncrypt(account.get(5));
        // Encrypt hashed password and email
        String hashUserAccountPart = Encrypt.userInfoEncrypt(account.get(3) + hashedPassword);

        // email address and passport number check
        for (int i = 0; i < allLines.size(); i++) {
            // email address check
            if (allLines.get(i)[3].equals(account.get(3))) {
                System.out.println("The emailaddress is invalid");
                return;
            }
            // passport number check
            if (allLines.get(i)[4].equals(account.get(4))) {
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
        List<String[]> flightInfo = ManipulateCsv.readUserFlightInfo(account);
        UserLoginMenu.userLoginMenu(account, flightInfo);
    }
}
