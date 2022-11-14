import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserLoginMenu {

    public static void userLoginMenu(List<String> userInformation, List<String[]> userFlightInfo) throws IOException {

        Scanner sc = new Scanner(System.in);
        int userDecsion = 0;
        boolean condition = true;
        // ユーザ入力
        while (condition == true) {
            System.out.println();
            System.out.println("******************************");
            System.out.println("Welcome! " + userInformation.get(0) + " " + userInformation.get(1));
            System.out.println("******************************");
            System.out.println();
            System.out.println("Select the number from below.");
            System.out.println("-----------------------------");
            System.out.println("1. buy a ticket");
            System.out.println("2. cancel flight");
            System.out.println("3. see your information");
            System.out.println("4. exit");
            System.out.println("-----------------------------");

            try {
                userDecsion = sc.nextInt();
                switch (userDecsion) {
                    case 1:
                        // 1. buy a ticket
                        BuyTicket buyticket = new BuyTicket();
                        buyticket.buyingTicket(userInformation, userFlightInfo);
                        break;
                    case 2:
                        // 2. cancel flight
                        // CancelFlight.cancelflight(userInformation, userFlightInfo);
                        System.out.println("Sorry, the cancel filght is temporarily down to maintenance");
                        break;
                    case 3:
                        // 3. exit
                        condition = false;
                        break;
                    case 4:
                        // 3. exit
                        condition = false;
                        break;
                    default:
                        System.out.println("the number " + userDecsion + " is unacceptable");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a number");
            }
        }
    }
}
