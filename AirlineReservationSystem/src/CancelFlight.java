import java.util.List;
import java.util.Scanner;

public class CancelFlight {
    public static void cancelflight(List<String> userInformation, List<String[]> userFlightInfo) {
        Scanner sc = new Scanner(System.in);
        String UserDecisionYesNo = "";
        int UserDecision = 0;

        // もしuserinfoがある場合
        if (1 > userFlightInfo.size()) {
            System.out.println("Which seat will you cancel?");
            System.out.println("Select the number: ");
            for (int i = 1; i < userFlightInfo.size(); i++) {
                System.out.print(i + ": ");
                for (int j = 0; j < userFlightInfo.get(i).length; j++) {
                    if (j == userFlightInfo.get(i).length - 1) {
                        System.out.print(userFlightInfo.get(i)[j]);
                } else if (j == userFlightInfo.get(i).length - 1) {
                    System.out.print(userFlightInfo.get(i)[j] + " - ");
                } else {
                        System.out.print(userFlightInfo.get(i)[j] + " > ");
                    }
                }
            }

            UserDecision = sc.nextInt();

            System.out.println("Are you sure?(yes/no)");

            UserDecisionYesNo = sc.next();

            modifyFlightData(userFlightInfo.get(UserDecision));

        }
        // もしuserinfoがない場合
        else {

        }

    }

    public static void modifyFlightData(String[] strings) {
        
    }


}
