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
        UserInputCheck.emailAddressCheck(userEmail);
        System.out.println("2. Enter your password");
        userPassword = sc.next();
        System.out.println("-----------------------------");

        ManipulateCsv.readLoginUser(userEmail, userPassword);
    }

}
