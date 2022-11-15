import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ManipulateCsv {

    public static List<String> readAllLinesCsv(String path) throws IOException {
        Path pathCsv = Paths.get(path);
        List<String> linesCsv = Files.readAllLines(pathCsv);
        return linesCsv;
    }

    public static List<String[]> splitAllLines(List<String> linesCsv) {
        List<String[]> allLines = new ArrayList<>();
        for (int i = 0; i < linesCsv.size(); i++) {
            String[] line = linesCsv.get(i).split(",");
            allLines.add(line);
        }
        return allLines;
    }

    public static void createUserCsv(List<String> account) throws IOException {
        Path originalFile = Paths.get(ConstData.PATH_CSV_USER_SAMPLE);
        Path targetFile = Paths.get(ConstData.USER_PATH + account.get(6) + ".csv");
        Files.copy(originalFile, targetFile);
    }

    public static void writeUserAccountCsv(List<String> account) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(ConstData.USER_ACCOUNT_FILE, true));
        // write userAccount in file
        for (int j = 0; j < account.size(); j++) {
            bw.write(account.get(j));
            if (!(j == account.size() - 1)) {
                bw.write(ConstData.DELIMITER_COMMA);
            }
        }
        bw.newLine();
        bw.close();
    }

    public static void writeUserFlightCsv(String userHash, List<String[]> userFlightInfo) throws IOException {
        String userCsvFile = ConstData.USER_PATH + userHash + ".csv";
        BufferedWriter bw = new BufferedWriter(new FileWriter(userCsvFile, true));

        // adding new flight information
        int lastIndex = userFlightInfo.size() - 1;
        for (int i = 0; i < userFlightInfo.get(lastIndex).length; i++) {
            bw.write(userFlightInfo.get(lastIndex)[i]);
            if (!(i == userFlightInfo.get(lastIndex).length - 1)) {
                bw.write(ConstData.DELIMITER_COMMA);
            }
        }
        bw.newLine();
        bw.close();

    }

    public static void deleteUserFlightCsv(String userHash, List<String[]> userFlightInfo) throws IOException {
        String userCsvFile = ConstData.USER_PATH + userHash + ".csv";
        BufferedWriter bw = new BufferedWriter(new FileWriter(userCsvFile));

        for (int i = 0; i < userFlightInfo.size(); i++) {
            for (int j = 0; j < userFlightInfo.get(i).length; j++) {
                bw.write(userFlightInfo.get(i)[j]);
                if (!(j == userFlightInfo.get(i).length - 1)) {
                    bw.write(ConstData.DELIMITER_COMMA);
                }
            }
            bw.newLine();
        }
        bw.close();

    }

    public static void writeFlightSeatCsv(String flightSeatCsv, List<String[]> seats) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(flightSeatCsv));
        // write down to csv data
        for (int i = 0; i < seats.size(); i++) {
            for (int j = 0; j < seats.get(i).length; j++) {
                bw.write(seats.get(i)[j]);
                if (!(j == seats.get(i).length - 1)) {
                    bw.write(ConstData.DELIMITER_COMMA);
                }
            }
            bw.newLine();
        }

        bw.close();
    }

    public static List<String> readLoginUser(String userEmail, String userPassword)
            throws IOException, NoSuchAlgorithmException {
        Path pathUSER_ACCOUNT_FILE = Paths.get(ConstData.USER_ACCOUNT_FILE);
        List<String> linesCsvuserAccount = Files.readAllLines(pathUSER_ACCOUNT_FILE);
        List<String[]> allUserInfo = new ArrayList<>();
        List<String> userInfo = new ArrayList<>();

        for (int i = 0; i < linesCsvuserAccount.size(); i++) {
            String[] array = linesCsvuserAccount.get(i).split(",");
            allUserInfo.add(array);
        }

        // Encrypt password
        String hashedPassword = Encrypt.userInfoEncrypt(userPassword);
        // Encrypt hashed password and email
        String hashUserAccountPart = Encrypt.userInfoEncrypt(userEmail + hashedPassword);

        // find user information from csv
        for (int i = 0; i < allUserInfo.size(); i++) {
            if (allUserInfo.get(i)[6].equals(hashUserAccountPart)) {
                userInfo = Arrays.asList(allUserInfo.get(i));
                break;
            }
        }

        return userInfo;
    }

    public static List<String[]> readUserFlightInfo(List<String> userInfo) throws IOException {
        // find the user by hashed information
        Path pathUserFlightInfo = Paths.get(ConstData.USER_PATH + userInfo.get(6) + ".csv");
        List<String> linesCsvFlightInfo = Files.readAllLines(pathUserFlightInfo);
        List<String[]> flightInfo = new ArrayList<>();

        for (int i = 0; i < linesCsvFlightInfo.size(); i++) {
            String[] array = linesCsvFlightInfo.get(i).split(",");
            flightInfo.add(array);
        }

        return flightInfo;

    }

    // create a path for searching the flight csv data
    public static String getPath(String userFlight, String userFlightDate) {
        String userFlightYear = userFlightDate.substring(0, 4);
        String userFlightMonth = userFlightDate.substring(4, 6);
        String userFlightDay = userFlightDate.substring(6, 8);
        String flightPath = ConstData.PARENT_PATH + userFlightYear + "/" + userFlightMonth + "/" + userFlightDay + "/"
                + userFlight + ".csv";
        return flightPath;
    }

    public static void createflightdata() throws IOException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        // add three month
        calendar.add(Calendar.MONTH, 3);

        List<String> linesCsvpathAirlineData = ManipulateCsv.readAllLinesCsv(ConstData.PATH_CSV_AIRLINEDATA);

        for (int i = 0; i < 100; i++) {
            String todayAdded = sdf.format(calendar.getTime());
            String yearAdded = todayAdded.substring(0, 4);
            String monthAdded = todayAdded.substring(4, 6);
            String dayAdded = todayAdded.toString().substring(6, 8);
            String pathCreate = ConstData.PARENT_PATH + "/" + yearAdded + "/" + monthAdded + "/" + dayAdded + "/";

            Path folderPath = Paths.get(pathCreate);

            if (Files.exists(folderPath)) {
                break;
            } else {
                Files.createDirectories(folderPath);
                for (int k = 1; k < linesCsvpathAirlineData.size(); k++) {
                    String pathFile = folderPath + "/" + linesCsvpathAirlineData.get(k) + ".csv";
                    Path filePath = Paths.get(pathFile);

                    if (Files.exists(filePath)) {
                        break;
                    } else {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile));

                        // write down to csv data
                        for (int l = 0; l < ConstData.HOURS; l++) {
                            for (int m = 0; m < ConstData.SEATS; m++) {
                                bw.write("0");
                                if (!(m == ConstData.SEATS - 1)) {
                                    bw.write(ConstData.DELIMITER_COMMA);
                                }
                            }
                            bw.write(ConstData.LINE_FEED_CODE);
                        }

                        bw.close();
                    }
                }
                calendar.add(Calendar.DAY_OF_MONTH, -1);
            }
        }
    }
}
