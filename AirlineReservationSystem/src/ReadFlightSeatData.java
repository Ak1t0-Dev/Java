import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class ReadFlightSeatData {

    public static List<String> readFlightSeatData(List<String> userDecisionList, List<String> userInformation,
            List<String[]> userFlightInfo) {
        int seatsFirstclassColumn = 20;
        int seatsFirstclassRow = 7;
        int seatsAll = seatsFirstclassColumn * seatsFirstclassRow;
        char firstLetter = 'A';
        int paddingNumber = 4;
        int reserveSeatRow = 0;
        String reserveSeatColumn = "";
        int userDecision = 0;
        int userDecisionForTime = 0;
        String userArrivalCountry = "";
        String userFlight = "";
        String userFlightDate = "";
        String userFlightYear = "";
        String userFlightMonth = "";
        String userFlightDay = "";
        String userPathCombine = "";
        Scanner sc = new Scanner(System.in);
        // フラグ
        int flag = 0;

        try {
            userArrivalCountry = userDecisionList.get(1);
            userFlight = userDecisionList.get(5);
            userFlightDate = userDecisionList.get(7);
            userFlightYear = userFlightDate.substring(0, 4);
            userFlightMonth = userFlightDate.substring(4, 6);
            userFlightDay = userFlightDate.substring(6, 8);
            userPathCombine = "src/csv/flightData/" + userFlightYear + "/" + userFlightMonth + "/" + userFlightDay + "/"
                    + userFlight + ".csv";

            List<String> linesCsvFlightSeat = ManipulateCsv.readAllLineCsv(userPathCombine);

            // 例外拾う
            if (linesCsvFlightSeat == null) {
                System.out.println("IOException");
                return null;
            }

            List<String[]> nodes = new ArrayList<>();
            List<Integer> sumFlightSeat = new ArrayList<>();
            List<int[]> sumFlightSeatMemory = new ArrayList<>();

            for (int i = 0; i < linesCsvFlightSeat.size(); i++) {
                String[] array = linesCsvFlightSeat.get(i).split(",");
                nodes.add(array);
            }

            // 修正
            for (int i = 0; i < nodes.size(); i++) {
                int[] sumFlightSeatTemp = Stream.of(nodes.get(i)).mapToInt(Integer::parseInt).toArray();
                sumFlightSeatMemory.add(sumFlightSeatTemp);
                int sum = 0;
                for (int j = 0; j < sumFlightSeatTemp.length; j++) {
                    sum += sumFlightSeatTemp[j];
                }
                sumFlightSeat.add(sum);
            }

            // 修正する べた書き パディング
            for (int i = 0; i < sumFlightSeat.size(); i++) {
                if (sumFlightSeat.get(i) == seatsAll) {
                    System.out.println((i + 1) + ":  " + i + ":00 " + "×");
                } else {
                    System.out.println((i + 1) + ":  " + i + ":00 " + "○");
                }
            }

            // 例外処理
            while (flag == 0) {
                System.out.println("Choose the flight time: ");
                userDecisionForTime = sc.nextInt();
                if (userDecisionForTime > 0 || userDecisionForTime < 25) {
                    if (sumFlightSeat.get(userDecisionForTime - 1) != seatsAll) {
                        // userDecsionAdded
                        userDecisionList.add(String.valueOf(userDecisionForTime));
                        flag = 1;

                    } else {
                        System.out.println("the flight has no seat");
                    }
                } else {
                    System.out.println("the number is invalid");
                }
            }

            flag = 0; // initialized

            int[] arrayConvert = sumFlightSeatMemory.get(userDecisionForTime - 1);
            int[][] arraySeat = new int[seatsFirstclassColumn][seatsFirstclassRow];

            // put elements in 2 dimension array
            for (int i = 0; i < seatsFirstclassColumn; i++) {
                for (int j = 0; j < seatsFirstclassRow; j++) {
                    arraySeat[i][j] = arrayConvert[i * seatsFirstclassRow + j];
                }
            }

            for (int i = 0; i < paddingNumber; i++) {
                System.out.print(" ");
            }

            for (int i = 0; i < seatsFirstclassRow; i++) {
                System.out.print(firstLetter);
                firstLetter++;
            }

            System.out.println();

            for (int i = 0; i < seatsFirstclassColumn; i++) {
                System.out.print(padRight(String.valueOf(i + 1), paddingNumber));
                for (int j = 0; j < seatsFirstclassRow; j++) {
                    if (arraySeat[i][j] == 0) {
                        System.out.print("○");
                    } else {
                        System.out.print("×");
                    }
                }
                System.out.println();
            }

            // modify the seat
            System.out.println("Which seat would you like to reserve?");
            System.out.println("Enter the number of row: ");
            reserveSeatRow = sc.nextInt();
            System.out.println("Enter the alphabet of column: ");
            reserveSeatColumn = sc.next();
            int column = changeToNumber(reserveSeatColumn);

            // modify the seat
            if (arraySeat[reserveSeatRow - 1][column] == 1) {
                System.out.println("The seat has occupied.");
            } else {
                System.out.println("Do you want to reserve the seat?");
                System.out.println("1: yes");
                System.out.println("2: no");
                userDecision = sc.nextInt();

                if (userDecision == 1) {
                    // userDecsionAdded
                    userDecisionList.add(String.valueOf(reserveSeatRow - 1));
                    // userDecsionAdded
                    userDecisionList.add(String.valueOf(column));
                    arraySeat[reserveSeatRow - 1][changeToNumber(reserveSeatColumn)] = 1;

                    // userflightの情報を追加
                    int columnIndex = Integer.parseInt(userDecisionList.get(9)) + 1;
                    String[] str = { userDecisionList.get(2), userDecisionList.get(4), userDecisionList.get(5),
                            String.valueOf(userDecisionForTime - 1), String.valueOf(columnIndex),
                            String.valueOf(reserveSeatColumn), userDecisionList.get(6) };
                    userFlightInfo.add(str);

                    // 最終的な処理
                    arrayConvert[(reserveSeatRow - 1) * seatsFirstclassRow
                            + changeToNumber(reserveSeatColumn)] = arraySeat[reserveSeatRow - 1][changeToNumber(
                                    reserveSeatColumn)];
                    String[] strArray = Arrays.stream(arrayConvert).mapToObj(String::valueOf).toArray(String[]::new);
                    nodes.set(userDecisionForTime - 1, strArray);
                    ManipulateCsv.writeFlightSeatCsv(userPathCombine, nodes);
                    ManipulateCsv.writeUserFlightCsv(userInformation.get(7), userFlightInfo);
                    System.out.println("Your seat has reserved.");

                } else {
                    return userDecisionList;
                }
                // 仮置き
            }
            // confirm your seat

        } catch (Exception e) {
            // 仮置き
            return userDecisionList;
        } finally {
            // sc.close();
        }
        // 仮置き
        return userDecisionList;
    }

    // padding the numbers with space to right
    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    // change alphabet to number
    public static int changeToNumber(String alphabet) {
        if (alphabet.matches("[a-zA-Z]")) { // 存在しない列を指定した場合の制御
            char[] arrayAlphabet = alphabet.toCharArray();
            char charAlphabet = (arrayAlphabet[0]);
            charAlphabet = Character.toUpperCase(charAlphabet);
            int number = (int) charAlphabet - (int) 'A';
            return number;
        }

        else {
            return -1;
        }
    }

}
