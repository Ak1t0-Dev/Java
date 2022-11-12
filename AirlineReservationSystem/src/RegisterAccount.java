import java.util.ArrayList;
import java.util.List;

public class RegisterAccount {

    public static void regsiterAccount(List<String> account) {

        List<String[]> nodes = new ArrayList<>();

        try {
            List<String> linesCsvuserAccount = ManipulateCsv.readAllLineCsv(ConstData.userAccountFile);

            //  exception
            if (linesCsvuserAccount == null) {
                System.out.println("reading data is failed");
                return;
            } 

            for (int i = 0; i < linesCsvuserAccount.size(); i++) {
                String[] array = linesCsvuserAccount.get(i).split(",");
                nodes.add(array);
            }

            // Encrypt password
            String hashedPassword = Encrypt.userInfoEncrypt(account.get(4));
            if (hashedPassword == "") {
                System.out.println("Encrypt is failed");
                return;
            }

            // Encrypt hashed password and email
            String hashUserAccountPart = Encrypt.userInfoEncrypt(account.get(3) + hashedPassword);
            if (hashUserAccountPart == "") {
                System.out.println("Encrypt is failed");
                return;
            }
            
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

        } catch (Exception e) {
            System.out.println("Sorry, something is wrong");
        }
    }
}
