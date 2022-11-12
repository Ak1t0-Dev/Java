import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManipulateCsv {

    public static List<String> readAllLineCsv(String path) {
        try {
            Path pathCsv = Paths.get(path);
            List<String> linesCsv = Files.readAllLines(pathCsv);
            return linesCsv;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createUserCsv(List<String> account) {
        try {
            Path originalFile = Paths.get("src/csv/userAccountData/user/userSample/userSample.csv");
            Path targetFile = Paths.get(ConstData.userPath + account.get(7) + ".csv");
            Files.copy(originalFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("writing to csv is failed");
        }
    }

    public static void writeUserAccountCsv(List<String> account) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ConstData.userAccountFile, true));
            // write userAccount in file
            for (int j = 0; j < account.size(); j++) {
                bw.write(account.get(j));
                if (!(j == account.size() - 1)) {
                    bw.write(ConstData.DELIMITER_COMMA);
                }
            }
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("writing to csv is failed");
        }
    }

    public static void writeUserFlightCsv(String userHash, List<String[]> userFlightInfo) {

        String userCsvFile = ConstData.userPath + userHash + ".csv";

        try {
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

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("writing to csv is failed");
        }

    }

    public static void writeFlightSeatCsv(String flightSeatCsv, List<String[]> nodes) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(flightSeatCsv));

            // write down to csv data
            for (int i = 0; i < nodes.size(); i++) {
                for (int j = 0; j < nodes.get(i).length; j++) {
                    bw.write(nodes.get(i)[j]);
                    if (!(j == nodes.get(i).length - 1)) {
                        bw.write(ConstData.DELIMITER_COMMA);
                    }
                }
                bw.newLine();
            }

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("writing to csv is failed");
        }
    }

    public static void readLoginUser(String userEmail, String userPassword) {

        try {
            Path pathUserAccountFile = Paths.get(ConstData.userAccountFile);
            List<String> linesCsvuserAccount = Files.readAllLines(pathUserAccountFile);
            List<String[]> allUserInfo = new ArrayList<>();
            List<String> userInfo = new ArrayList<>();

            for (int i = 0; i < linesCsvuserAccount.size(); i++) {
                String[] array = linesCsvuserAccount.get(i).split(",");
                allUserInfo.add(array);
            }

            // Encrypt password
            String hashedPassword = Encrypt.userInfoEncrypt(userPassword);
            if (hashedPassword == "") {
                System.out.println("Encrypt is failed");
                return;
            }

            // Encrypt hashed password and email
            String hashUserAccountPart = Encrypt.userInfoEncrypt(userEmail + hashedPassword);
            if (hashUserAccountPart == "") {
                System.out.println("Encrypt is failed");
                return;
            }

            // find user information from csv
            for (int i = 0; i < allUserInfo.size(); i++) {
                if (allUserInfo.get(i)[6].equals(hashUserAccountPart)) {
                    userInfo = Arrays.asList(allUserInfo.get(i));
                    break;
                }
            }

            // get user flight information
            Path pathUserFlightInfo = Paths.get(ConstData.userPath + userInfo.get(7) + ".csv");
            List<String> linesCsvFlightInfo = Files.readAllLines(pathUserFlightInfo, Charset.forName(ConstData.UTF_8));
            List<String[]> flightInfo = new ArrayList<>();

            for (int i = 0; i < linesCsvFlightInfo.size(); i++) {
                String[] array = linesCsvFlightInfo.get(i).split(",");
                flightInfo.add(array);
            }

            UserLoginMenu.userLoginMenu(userInfo, flightInfo);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("writing is failed");
        }
    }

}
