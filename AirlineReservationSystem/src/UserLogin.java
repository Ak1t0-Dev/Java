import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

public class UserLogin {

    public static void userLogin() throws IOException, NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        String userEmail = "";
        String userPassword = "0";
        int flag = 0;
        int flagInner = 0;
        while (flag == 0) {
            // show menu
            System.out.println();
            System.out.println(ConstData.BORDER_LINE_STAR);
            System.out.println("Welcome to CICCC Airlines.");
            System.out.println(ConstData.BORDER_LINE_STAR);
            System.out.println();

            while (flagInner == 0) {
                System.out.println(ConstData.BORDER_LINE);
                System.out.println("1: Enter your email Address");
                userEmail = sc.next();
                Boolean check = UserInputCheck.emailAddressCheck(userEmail);
                if (check) {
                    flagInner = 1;
                } else {
                    System.out.println("email address is invalid");
                }
            }

            flagInner = 0;

            System.out.println("2. Enter your password");
            userPassword = sc.next();
            List<String> userInfo = ManipulateCsv.readLoginUser(userEmail, userPassword);

            if (userInfo.size() == 0) {
                System.out.println("email address or password is wrong.");
            } else {
                List<String[]> flightInfo = ManipulateCsv.readUserFlightInfo(userInfo);
                UserLoginMenu.userLoginMenu(userInfo, flightInfo);
                flag = 1;
            }

        }
    }

}
