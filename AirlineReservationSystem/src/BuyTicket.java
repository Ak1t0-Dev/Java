import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class BuyTicket {
    int userDecision = 0;
    int flag = 0;
    int flagInner = 0;
    String userDecisionDate = "";
    String today = "";
    String todayAdded = "";
    String yyyyMmDd = "";
    String yyyyMmDdAdded = "";

    public void buyingTicket(List<String> userInformation, List<String[]> userFlightInfo) throws IOException {
        Scanner sc = new Scanner(System.in);
        // for Calendar
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        // read countryData csv
        List<String> linesCsvCountry = ManipulateCsv.readAllLineCsv(ConstData.PATH_CSV_COUNTRY);
        // read cityData csv
        List<String> linesCsvCity = ManipulateCsv.readAllLineCsv(ConstData.PATH_CSV_CITY);
        List<CityData> cityList = new ArrayList<CityData>();
        // read flightData csv
        List<String> linesCsvFlight = ManipulateCsv.readAllLineCsv(ConstData.PATH_CSV_FLIGHT);
        List<FlightInformation> flightList = new ArrayList<FlightInformation>();
        // list for user decision
        List<String> userDecisionList = new ArrayList<String>();

        // creating city data
        // split the csv data and give them as parameters to instances
        for (int i = 1; i < linesCsvCity.size(); i++) {
            String[] param = linesCsvCity.get(i).split(",");
            cityList.add(
                    new CityData(param[0], param[1]));
        }
        // creating flight data
        // split the csv data and give them as parameters to instances
        for (int i = 1; i < linesCsvFlight.size(); i++) {
            String[] param = linesCsvFlight.get(i).split(",");
            flightList.add(
                    new FlightInformation(param[0], param[1], param[2], param[3]));
        }

        // ask domestic / international flight
        while (flagInner == 0) {
            System.out.println("Enter the number");
            System.out.println("-----------------------------");
            System.out.println("1: " + ConstData.FLIGHT_DOMESTIC);
            System.out.println("2: " + ConstData.FLIGHT_INTERNATIONAL);
            userDecision = UserInputCheck.changeStringtoInt(sc.next());
            // check if the input is valid
            if (userDecision == 1) {
                userDecisionList.add(ConstData.FLIGHT_DOMESTIC);
            } else if (userDecision == 2) {
                userDecisionList.add(ConstData.FLIGHT_INTERNATIONAL);
            } else {
                System.out.println("Enter the valid number");
                flagInner = 1;
            }
        }

        flagInner = 0;

        // ask a departure country
        while (flagInner == 0) {
            System.out.println("");
            System.out.println("Departure: Enter the number");
            System.out.println("-----------------------------");
            for (int i = 1; i < linesCsvCountry.size(); i++) {
                System.out.println(i + ": " + linesCsvCountry.get(i));
            }
            userDecision = UserInputCheck.changeStringtoInt(sc.next());
            // check if the input is valid
            if (0 < userDecision && userDecision < linesCsvCountry.size()) {
                userDecisionList.add(linesCsvCountry.get(userDecision));
                flagInner = 1;
            } else {
                System.out.println("Enter the valid number");
            }
        }

        flagInner = 0;

        // ask a departure city
        while (flagInner == 0) {
            int count = 0;
            HashMap<Integer, String> countMap = new HashMap<>();
            System.out.println("");
            System.out.println("Departure: Enter the number");
            System.out.println("-----------------------------");
            for (int i = 0; i < linesCsvCity.size() - 1; i++) {
                if (linesCsvCountry.get(userDecision).equals(cityList.get(i).getCountryName())) {
                    count++;
                    System.out.println(count + ": " + cityList.get(i).getCityName());
                    countMap.put(count, cityList.get(i).getCityName());
                }
            }
            userDecision = UserInputCheck.changeStringtoInt(sc.next());
            // check if the input is valid
            if (0 < userDecision && userDecision <= count) {
                userDecisionList.add(countMap.get(userDecision));
                flagInner = 1;
            } else {
                System.out.println("Enter the valid number");
            }
        }

        flagInner = 0;

        // ask an arrival country
        while (flagInner == 0) {
            int count = 0;
            HashMap<Integer, String> countMap = new HashMap<>();
            System.out.println("");
            System.out.println("Arrival: Enter the number");
            System.out.println("-----------------------------");

            // if user chose a domestic/international flight, show only the flight
            if (userDecisionList.get(0).equals(ConstData.FLIGHT_DOMESTIC)) {
                for (int i = 1; i < linesCsvCountry.size(); i++) {
                    if (userDecisionList.get(1).equals(linesCsvCountry.get(i))) {
                        count++;
                        System.out.println(count + ": " + linesCsvCountry.get(i));
                        countMap.put(count, linesCsvCountry.get(i));
                    }
                }
            } else {
                for (int i = 1; i < linesCsvCountry.size(); i++) {
                    if (!userDecisionList.get(1).equals(linesCsvCountry.get(i))) {
                        count++;
                        System.out.println(count + ": " + linesCsvCountry.get(i));
                        countMap.put(count, linesCsvCountry.get(i));
                    }
                }
            }
            userDecision = UserInputCheck.changeStringtoInt(sc.next());
            // check if the input is valid
            if (0 < userDecision && userDecision <= count) {
                userDecisionList.add(countMap.get(userDecision));
                flagInner = 1;
            } else {
                System.out.println("Enter the valid number");
            }
        }

        flagInner = 0;

        // ask an arrival city
        while (flagInner == 0) {
            int count = 0;
            HashMap<Integer, String> countMap = new HashMap<>();
            System.out.println("");
            System.out.println("Arrival: Enter the number");
            System.out.println("-----------------------------");
            for (int i = 0; i < linesCsvCity.size() - 1; i++) {
                if (userDecisionList.get(3).equals(cityList.get(i).getCountryName())
                        && !userDecisionList.get(2).equals(cityList.get(i).getCityName())) {
                    count++;
                    System.out.println(count + ": " + cityList.get(i).getCityName());
                    countMap.put(count, cityList.get(i).getCityName());
                }
            }
            userDecision = UserInputCheck.changeStringtoInt(sc.next());
            // check if the input is valid
            if (0 < userDecision && userDecision <= count) {
                userDecisionList.add(countMap.get(userDecision));
                flagInner = 1;
            } else {
                System.out.println("Enter the valid number");
            }
        }

        flagInner = 0;

        // Show the flight
        System.out.println("");
        System.out.println("Your flight information");
        System.out.println("-----------------------------");
        for (int i = 0; i < flightList.size(); i++) {
            if (userDecisionList.get(2).equals(flightList.get(i).getArrivalCity())
                    && userDecisionList.get(4).equals(flightList.get(i).getDepartureCity())) {
                // add the flight information to List
                userDecisionList.add(flightList.get(i).getFlight());
                userDecisionList.add(flightList.get(i).getPayment());
                // show the flight information
                System.out.println(flightList.get(i).getArrivalCity());
                System.out.println(flightList.get(i).getDepartureCity());
                System.out.println(flightList.get(i).getFlight());
                System.out.println(flightList.get(i).getPayment());
            }
        }

        // Choose the date
        while (flagInner == 0) {
            System.out.print("Select the date between ");
            today = sdf.format(calendar.getTime());
            calendar.add(Calendar.MONTH, 3);
            todayAdded = sdf.format(calendar.getTime());
            yyyyMmDd = today.substring(0, 8);
            yyyyMmDdAdded = todayAdded.substring(0, 8);
            System.out.println(yyyyMmDd + " - " + yyyyMmDdAdded + " :");
            String userDate = UserInputCheck.dateCheck(sc.next());
            // DateTimeParseException condition
            if (yyyyMmDd.compareTo(userDate) > 0 && yyyyMmDdAdded.compareTo(userDate) < 0) {
                System.out.println("The entered date should be between " + yyyyMmDd + " - " + yyyyMmDdAdded);
            } else {
                userDecisionList.add(userDate);
                flagInner = 1;
            }

        }

        flagInner = 0;
        ReadFlightSeatData.readFlightSeatData(userDecisionList, userInformation, userFlightInfo);

    }
}