import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UserLoginMenu {

    public static void userLoginMenu(List<String> userInformation, List<String[]> userFlightInfo) throws IOException {

        Scanner sc = new Scanner(System.in);
        int userDecsion = 0;
        int flag = 0;

        while (flag == 0) {
            System.out.println();
            System.out.println(ConstData.BORDER_LINE_STAR);
            System.out.println("Welcome! " + userInformation.get(0) + " " + userInformation.get(1));
            System.out.println(ConstData.BORDER_LINE_STAR);
            System.out.println();
            System.out.println("Select the number from below.");
            System.out.println(ConstData.BORDER_LINE);
            System.out.println("1. buy a ticket");
            System.out.println("2. cancel flight");
            System.out.println("3. see your information");
            System.out.println("4. exit");
            System.out.println(ConstData.BORDER_LINE);

            userDecsion = UserInputCheck.changeStringtoInt(sc.next());
            switch (userDecsion) {
                case 1:
                    // 1. buy a ticket
                    BuyTicket.buyingTicket(userInformation, userFlightInfo);
                    break;
                case 2:
                    // 2. cancel flight
                    CancelFlight.cancelflight(userInformation, userFlightInfo);
                    break;
                case 3:
                    // 3. see your information
                    ShowInformation.showInformation(userInformation, userFlightInfo);
                    break;
                case 4:
                    // 4. exit
                    flag = 1;
                    break;
                default:
                    System.out.println("Enter the valid number");
                    break;
            }
        }
    }
}
