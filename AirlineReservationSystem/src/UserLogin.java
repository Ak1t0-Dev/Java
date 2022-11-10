import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserLogin {

    public static void userLogin() {
        Scanner sc = new Scanner(System.in);
        String userEmail = "";
        String userPassword = "0";

        // show menu
        System.out.println();
        System.out.println("******************************");
        System.out.println("Welcome to CICCC Airlines.");
        System.out.println("******************************");
        System.out.println();
        System.out.println("-----------------------------");
        System.out.println("1: Enter your Email Address");
        userEmail = sc.next();
        // EmailAddress condtion
        System.out.println("2. Enter your password");
        userPassword = sc.next();
        // password condtion
        System.out.println("-----------------------------");
        loginUserCheck(userEmail, userPassword);
    }

    public static void loginUserCheck(String userEmail, String userPassword) {

        try {
            Path pathUserAccountFile = Paths.get(ConstData.userAccountFile);
            List<String> linesCsvuserAccount = Files.readAllLines(pathUserAccountFile,
                    Charset.forName(ConstData.UTF_8));
            List<String[]> nodes = new ArrayList<>();
            List<String> userInformation = new ArrayList<>();

            for (int i = 0; i < linesCsvuserAccount.size(); i++) {
                String[] array = linesCsvuserAccount.get(i).split(",");
                nodes.add(array);
            }
            
            // SHA3-256（SHA-3）
            MessageDigest sha3_256 = MessageDigest.getInstance("SHA3-256");
            // password;
            byte[] sha3_256_result_password = sha3_256.digest(userPassword.getBytes());
            userPassword = String.format("%040x", new BigInteger(1, sha3_256_result_password));
            // emailaddress + hashedpassword;
            String hashUserAccountPart = userEmail + userPassword;
            byte[] sha3_256_result_part = sha3_256.digest(hashUserAccountPart.getBytes());
            hashUserAccountPart = String.format("%040x", new BigInteger(1, sha3_256_result_part));
            
            // find user information from csv
            for (int i = 0; i < nodes.size(); i++) {
                if (nodes.get(i)[7].equals(hashUserAccountPart)) {
                    userInformation = Arrays.asList(nodes.get(i));
                    break;
                } else {
                    // 判定処理
                }
            }

            //get user flight information
            Path pathUserFlightInfo = Paths.get(ConstData.userPath + userInformation.get(7) + ".csv");
            List<String> linesCsvFlightInfo = Files.readAllLines(pathUserFlightInfo, Charset.forName(ConstData.UTF_8));
            List<String[]> flightInfo = new ArrayList<>();

            for (int i = 0; i < linesCsvFlightInfo.size(); i++) {
                String[] array = linesCsvFlightInfo.get(i).split(",");
                flightInfo.add(array);
            }

            UserLoginMenu.userLoginMenu(userInformation, flightInfo);

        } catch (IOException | NoSuchAlgorithmException e) {
            System.out.println("writing is failed");

        } 
    }
}
