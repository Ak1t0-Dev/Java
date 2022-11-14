import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class ReadFlightSeatData {

    public static List<String[]> readFlightSeatData(List<String> userDecisionList, List<String> userInformation,
            List<String[]> userFlightInfo) throws IOException {
        char firstLetter = 'A';
        int reserveSeatRow = 0;
        String reserveSeatColumn = "";
        int userDecision = 0;
        int userDecisionForTime = 0;
        int columnOfSeat = 0;
        Scanner sc = new Scanner(System.in);
        int flag = 0;
        int flagInner = 0;
        String flightPath = createPath(userDecisionList);
        List<String> linesCsvFlightSeat = ManipulateCsv.readAllLineCsv(flightPath);
        List<String[]> nodes = new ArrayList<>();
        List<Integer> sumFlightSeat = new ArrayList<>();
        List<int[]> sumFlightSeatMemory = new ArrayList<>();

        for (int i = 0; i < linesCsvFlightSeat.size(); i++) {
            String[] array = linesCsvFlightSeat.get(i).split(",");
            nodes.add(array);
        }

        // change String[] to int[] and set to List
        for (int i = 0; i < nodes.size(); i++) {
            int[] sumFlightSeatTemp = Stream.of(nodes.get(i)).mapToInt(Integer::parseInt).toArray();
            sumFlightSeatMemory.add(sumFlightSeatTemp);
            int sum = 0;
            // get the sum if the seats are available or not
            for (int j = 0; j < sumFlightSeatTemp.length; j++) {
                sum += sumFlightSeatTemp[j];
            }
            sumFlightSeat.add(sum);
        }

        // print the flight time and flight avaiable
        for (int i = 0; i < sumFlightSeat.size(); i++) {
            if (sumFlightSeat.get(i) == ConstData.SEATS) {
                System.out
                        .println(padRight(ConstData.PADDING_NUMBER, (i + 1) + ":  " + i + ":00 " + ConstData.OCCUPIED));
            } else {
                System.out.println(padRight(ConstData.PADDING_NUMBER, (i + 1) + ":  " + i + ":00 " + ConstData.VACANT));
            }
        }

        while (flag == 0) {
            flagInner = 0; // initialized for if user want to choose the flight time again
            while (flagInner == 0) {
                System.out.println("Choose the flight time: ");
                userDecisionForTime = UserInputCheck.changeStringtoInt(sc.next());
                if (userDecisionForTime > 0 && userDecisionForTime < 25) {
                    // if the seat is vacant or not
                    if (sumFlightSeat.get(userDecisionForTime - 1) != ConstData.SEATS) {
                        flagInner = 1;
                    } else {
                        System.out.println("the flight has no seat");
                    }
                } else {
                    System.out.println("the number is invalid");
                }
            }

            flagInner = 0;

            int[] arrayConvert = sumFlightSeatMemory.get(userDecisionForTime - 1);
            int[][] arraySeat = new int[ConstData.SEATS_ROW][ConstData.SEATS_COLUMN];

            // put elements in 2 dimension array
            for (int i = 0; i < ConstData.SEATS_ROW; i++) {
                for (int j = 0; j < ConstData.SEATS_COLUMN; j++) {
                    arraySeat[i][j] = arrayConvert[i * ConstData.SEATS_COLUMN + j];
                }
            }

            for (int i = 0; i < ConstData.PADDING_NUMBER; i++) {
                System.out.print(" ");
            }

            for (int i = 0; i < ConstData.SEATS_COLUMN; i++) {
                System.out.print(firstLetter);
                firstLetter++;
            }

            System.out.println();

            for (int i = 0; i < ConstData.SEATS_ROW; i++) {
                System.out.print(padRight(ConstData.PADDING_NUMBER, String.valueOf(i + 1)));
                for (int j = 0; j < ConstData.SEATS_COLUMN; j++) {
                    if (arraySeat[i][j] == 0) {
                        System.out.print("○");
                    } else {
                        System.out.print("×");
                    }
                }
                System.out.println();
            }

            System.out.println("Which seat would you like to reserve?");
            // ask the number of row
            while (flagInner == 0) {
                System.out.println("Enter the number of row: ");
                reserveSeatRow = UserInputCheck.changeStringtoInt(sc.next());
                if (reserveSeatRow > 0 && reserveSeatRow < ConstData.SEATS_ROW) {
                    flagInner = 1;
                } else {
                    System.out.println("The number of row is invalid.");
                }
            }

            flagInner = 0;

            // aski the alphabet of column
            while (flagInner == 0) {
                System.out.println("Enter the alphabet of column: ");
                reserveSeatColumn = sc.next();
                columnOfSeat = changeToNumber(reserveSeatColumn);
                if (columnOfSeat >= 0 && columnOfSeat <= ConstData.SEATS_COLUMN) {
                    flagInner = 1;
                } else {
                    System.out.println("The alphabet of column is invalid.");
                }
            }

            flagInner = 0;

            if (arraySeat[reserveSeatRow - 1][columnOfSeat] == 1) {
                System.out.println("Sorry, the seat has occupied.");
            }

            while (flagInner == 0) {
                System.out.println("Do you want to reserve the seat?");
                System.out.println("1: yes");
                System.out.println("2: no, start from choosing the flight time again");
                System.out.println("3: exit");
                userDecision = UserInputCheck.changeStringtoInt(sc.next());

                switch (userDecision) {
                    case 1:
                        // add the flight time, thw seat row and column
                        userDecisionList.add(String.valueOf(userDecisionForTime));
                        userDecisionList.add(String.valueOf(reserveSeatRow - 1));
                        userDecisionList.add(String.valueOf(columnOfSeat));
                        String columnAlphabet = changeToUpper(reserveSeatColumn);
                        // change thw seat status
                        arraySeat[reserveSeatRow - 1][columnOfSeat] = 1;
                        int rowIndex = Integer.parseInt(userDecisionList.get(9)) + 1;
                        // put reserved flight information to array
                        String[] reservedFlightInformation = { userDecisionList.get(2), userDecisionList.get(4),
                                userDecisionList.get(5),
                                String.valueOf(userDecisionForTime - 1), String.valueOf(rowIndex),
                                columnAlphabet, userDecisionList.get(6) };
                        userFlightInfo.add(reservedFlightInformation);

                        //
                        arrayConvert[(reserveSeatRow - 1) * ConstData.SEATS_COLUMN
                                + columnOfSeat] = arraySeat[reserveSeatRow - 1][columnOfSeat];
                        String[] strArray = Arrays.stream(arrayConvert).mapToObj(String::valueOf)
                                .toArray(String[]::new);
                        nodes.set(userDecisionForTime - 1, strArray);
                        ManipulateCsv.writeFlightSeatCsv(flightPath, nodes);
                        ManipulateCsv.writeUserFlightCsv(userInformation.get(7), userFlightInfo);
                        System.out.println("Your seat has reserved.");
                        flagInner = 1;
                        flag = 1;
                        break;

                    case 2:
                        flagInner = 1;
                        break;

                    case 3:
                        flagInner = 1;
                        flag = 1;
                        break;

                    default:
                        System.out.println("Enter the valid number");
                }
            }
        }
        return userFlightInfo;
    }

    // padding the numbers with space to right
    public static String padRight(int n, String s) {
        return String.format("%-" + n + "s", s);
    }

    // change alphabet to number
    public static int changeToNumber(String alphabet) {
        if (alphabet.matches("[a-zA-Z]")) {
            char[] arrayAlphabet = alphabet.toCharArray();
            char charAlphabet = arrayAlphabet[0];
            charAlphabet = Character.toUpperCase(charAlphabet);
            int number = (int) charAlphabet - (int) 'A';
            return number;
        } else {
            return -1;
        }
    }

    // change to UpperCase
    public static String changeToUpper(String alphabet) {
        char[] arrayAlphabet = alphabet.toCharArray();
        char charAlphabet = arrayAlphabet[0];
        return String.valueOf(Character.toUpperCase(charAlphabet));
    }

    // create a path for searching the flight csv data
    public static String createPath(List<String> userDecisionList) {
        String userFlight = userDecisionList.get(5);
        String userFlightDate = userDecisionList.get(7);
        String userFlightYear = userFlightDate.substring(0, 4);
        String userFlightMonth = userFlightDate.substring(4, 6);
        String userFlightDay = userFlightDate.substring(6, 8);
        String flightPath = ConstData.PARENT_PATH + userFlightYear + "/" + userFlightMonth + "/" + userFlightDay + "/"
                + userFlight + ".csv";
        return flightPath;

    }
}
