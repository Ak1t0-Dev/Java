import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Menu {
    public static void showMenu() throws IOException, NoSuchAlgorithmException {
        int flag = 0;
        while (flag == 0) {
            Scanner sc = new Scanner(System.in);
            int userDecsion = 0;
            System.out.println();
            System.out.println(ConstData.BORDER_LINE_STAR);
            System.out.println("Welcome to CICCC Airlines.");
            System.out.println(ConstData.BORDER_LINE_STAR);
            System.out.println();
            System.out.println("Select the number from below.");
            System.out.println(ConstData.BORDER_LINE);
            System.out.println("1: login");
            System.out.println("2: creat a new account");
            System.out.println("3: exit");
            System.out.println(ConstData.BORDER_LINE);

            userDecsion = UserInputCheck.changeStringtoInt(sc.next());

            switch (userDecsion) {
                case 1:
                    // 1: login
                    UserLogin.userLogin();
                    flag = 1;
                    break;
                case 2:
                    // 2. creat a new account
                    CreateNewAccount.createNewAccount();
                    flag = 1;
                    break;
                case 3:
                    flag = 1;
                    break;
                default:
                    System.out.println("Enter a valid number");
                    break;
            }
        }
    }
}
