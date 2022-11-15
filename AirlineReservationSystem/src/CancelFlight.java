import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CancelFlight {
    public static void cancelflight(List<String> userInformation, List<String[]> userFlightInfo) throws IOException {
        Scanner sc = new Scanner(System.in);
        int userDecision = 0;
        int userFlightNumber = 0;
        int flag = 0;
        int flagInner = 0;
        ShowInformation.showUserFlightInformation(userFlightInfo);

        if (1 < userFlightInfo.size()) {
            while (flag == 0) {
                while (flagInner == 0) {
                    System.out.println("Which flight you want to cancel?");
                    System.out.println("Enter the flight number: ");
                    System.out.println(ConstData.BORDER_LINE);
                    userFlightNumber = UserInputCheck.changeStringtoInt(sc.next());
                    if (userFlightNumber > 0 && userFlightNumber < userFlightInfo.size()) {
                        flagInner = 1;
                    } else {
                        System.out.println("Enter a valid number");
                    }
                }

                flagInner = 0;

                ShowInformation.selectUserFlight(userFlightNumber, userFlightInfo);
                System.out.println("Are you sure you want to cancel this flight?");
                System.out.println("Enter the number: ");
                System.out.println(ConstData.BORDER_LINE);
                System.out.println("1: Yes");
                System.out.println("2: No");
                System.out.println("3: Exit");
                userDecision = UserInputCheck.changeStringtoInt(sc.next());

                switch (userDecision) {
                    case 1:
                        // 1: cancel flight
                        String flightPath = ManipulateCsv.getPath(userFlightInfo.get(userFlightNumber)[2],
                                userFlightInfo.get(userFlightNumber)[3]);
                        List<String> linesCsvFlightSeat = ManipulateCsv.readAllLinesCsv(flightPath);
                        List<String[]> allLines = ManipulateCsv.splitAllLines(linesCsvFlightSeat);
                        // get users cancel flight seats information 
                        int userFlightTime = UserInputCheck.changeStringtoInt(userFlightInfo.get(userFlightNumber)[4]);
                        int userFlightSeatRow = UserInputCheck.changeStringtoInt(userFlightInfo.get(userFlightNumber)[5]);
                        int userFlightSeatColumn = StringOperation.changeToNumber(userFlightInfo.get(userFlightNumber)[6]);
                        String[] userFlight = allLines.get(userFlightTime);
                        userFlight[(userFlightSeatRow - 1) * ConstData.SEATS_COLUMN + userFlightSeatColumn] = "0";
                        ManipulateCsv.writeFlightSeatCsv(flightPath, allLines);
                        userFlightInfo.remove(userFlightNumber);
                        ManipulateCsv.deleteUserFlightCsv(userInformation.get(6), userFlightInfo);
                        System.out.println("The flight reservation has been canceled.");
                        flagInner = 1;
                        flag = 1;
                        break;
                    case 2:
                        // 2. ask from the beginning
                        break;
                    case 3:
                        // 3. exit
                        flagInner = 1;
                        flag = 1;
                        break;
                    default:
                        System.out.println("Enter a valid number");
                        break;
                }
            }

        }

    }

}
