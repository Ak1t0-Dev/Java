import java.util.List;

public class ShowInformation {
    public static void showInformation(List<String> userInformation, List<String[]> userFlightInfo) {
        // show userInformation
        showUserInformation(userInformation);
        // show userFlightInformation
        showUserFlightInformation(userFlightInfo);
    }

    public static void showUserInformation(List<String> userInformation) {
        System.out.println(ConstData.BORDER_LINE_STAR);
        System.out.println("User account information");
        System.out.println(ConstData.BORDER_LINE_STAR);
        System.out.println(ConstData.BORDER_LINE);
        System.out.println(ConstData.FIRST_NAME + ": " + userInformation.get(0));
        System.out.println(ConstData.LAST_NAME + ": " + userInformation.get(1));
        System.out.println(ConstData.BORN_DATE + ": " + userInformation.get(2));
        System.out.println(ConstData.EMAIL_ADDRESS + ": " + userInformation.get(3));
        System.out.println(ConstData.PASSPORT_NUMBER + ": " + userInformation.get(4));
        System.out.println(ConstData.BORDER_LINE);
        System.out.println();
    }

    public static void showUserFlightInformation(List<String[]> userFlightInfo) {
        System.out.println(ConstData.BORDER_LINE_STAR);
        System.out.println("User flight information");
        System.out.println(ConstData.BORDER_LINE_STAR);
        if (2 > userFlightInfo.size()) {
            System.out.println("There is no flight reservation");
        } else {
            for (int i = 1; i < userFlightInfo.size(); i++) {
                System.out.println(ConstData.FLIGHT + " " + i + ".");
                System.out.println(ConstData.BORDER_LINE);
                System.out.println(ConstData.DEPARTURE + ": " + userFlightInfo.get(i)[0]);
                System.out.println(ConstData.ARRIVAL + ": " + userFlightInfo.get(i)[1]);
                System.out.println(ConstData.FLIGHT_NAME + ": " + userFlightInfo.get(i)[2]);
                System.out.println(ConstData.DATE + ": " + userFlightInfo.get(i)[3]);
                System.out.println(ConstData.TIME + ": " + userFlightInfo.get(i)[4] + ":00");
                System.out.println(ConstData.SEAT + ": " + userFlightInfo.get(i)[5] + " - " + userFlightInfo.get(i)[6]);
                System.out.println(ConstData.PAYMENT + ": " + userFlightInfo.get(i)[7]);
                System.out.println(ConstData.BORDER_LINE);
            }
        }
    }

    public static void selectUserFlight(int num, List<String[]> userFlightInfo) {
        System.out.println(ConstData.FLIGHT + " " + num + ".");
        System.out.println(ConstData.BORDER_LINE);
        System.out.println(ConstData.DEPARTURE + ": " + userFlightInfo.get(num)[0]);
        System.out.println(ConstData.ARRIVAL + ": " + userFlightInfo.get(num)[1]);
        System.out.println(ConstData.FLIGHT_NAME + ": " + userFlightInfo.get(num)[2]);
        System.out.println(ConstData.DATE + ": " + userFlightInfo.get(num)[3]);
        System.out.println(ConstData.TIME + ": " + userFlightInfo.get(num)[4] + ":00");
        System.out.println(ConstData.SEAT + ": " + userFlightInfo.get(num)[5] + " - " + userFlightInfo.get(num)[6]);
        System.out.println(ConstData.PAYMENT + ": " + userFlightInfo.get(num)[7]);
        System.out.println(ConstData.BORDER_LINE);
    }
}
