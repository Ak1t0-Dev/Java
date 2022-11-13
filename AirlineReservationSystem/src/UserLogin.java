import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UserLogin {

    public static void userLogin() throws IOException {
        Scanner sc = new Scanner(System.in);
        String userEmail = "";
        String userPassword = "0";
        int flag = 0;
        int flagInner = 0;
        while (flag == 0) {
            // show menu
            System.out.println();
            System.out.println("******************************");
            System.out.println("Welcome to CICCC Airlines.");
            System.out.println("******************************");
            System.out.println();

            while (flagInner == 0) {
                System.out.println("-----------------------------");
                System.out.println("1: Enter your Email Address");
                userEmail = sc.next();
                Boolean check = UserInputCheck.emailAddressCheck(userEmail);
                if (check) {
                    flagInner = 1;
                } else {
                    System.out.println("email address is invalid");
                }
            }

            System.out.println("2. Enter your password");
            userPassword = sc.next();
            List<String> userInfo = ManipulateCsv.readLoginUser(userEmail, userPassword); 
            List<String[]> flightInfo = ManipulateCsv.readUserFlightInfo(userInfo);

            UserLoginMenu.userLoginMenu(userInfo, flightInfo);

        }
    }

}
