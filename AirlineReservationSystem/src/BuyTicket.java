import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class BuyTicket {
    // ask domestic/international flight
    int userDecision = 0;
    String userDecisionDate = "";
    String today = "";
    String todayAdded = "";
    String yyyyMmDd = "";
    String yyyyMmDdAdded = "";

    public void buyingTicket(List<String> userInformation, List<String[]> userFlightInfo) {
        try {

            Scanner sc = new Scanner(System.in);
            // For Calendar
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            // FlightData
            Path pathCsvFlight = Paths.get(ConstData.PATH_CSV_FLIGHT);
            List<String> linesCsvFlight = Files.readAllLines(pathCsvFlight, Charset.forName(ConstData.UTF_8));
            List<FlightInformation> flightList = new ArrayList<FlightInformation>();
            // CountryData
            Path pathCsvCountry = Paths.get(ConstData.PATH_CSV_COUNTRY);
            List<String> linesCsvCountry = Files.readAllLines(pathCsvCountry, Charset.forName(ConstData.UTF_8));
            // CityData
            Path pathCsvCity = Paths.get(ConstData.PATH_CSV_CITY);
            List<String> linesCsvCity = Files.readAllLines(pathCsvCity, Charset.forName(ConstData.UTF_8));
            List<CityData> cityList = new ArrayList<CityData>();

            // List表示用
            int count = 0;
            HashMap<Integer, String> countMap = new HashMap<>();

            // user decision
            List<String> userDecisionList = new ArrayList<String>();
            HashMap<Integer, String> linesCsvFlightMap = new HashMap<>();
            HashMap<Integer, String> linesCsvCountryMap = new HashMap<>();
            HashMap<Integer, String> linesCsvCityMap = new HashMap<>();

            try (Stream<String> stream = Files.lines(pathCsvFlight)) {
                AtomicInteger i = new AtomicInteger();
                stream.forEach(s -> linesCsvFlightMap.put(i.getAndIncrement(), s));
            }

            try (Stream<String> stream = Files.lines(pathCsvCountry)) {
                AtomicInteger i = new AtomicInteger();
                stream.forEach(s -> linesCsvCountryMap.put(i.getAndIncrement(), s));
            }

            try (Stream<String> stream = Files.lines(pathCsvCity)) {
                AtomicInteger i = new AtomicInteger();
                stream.forEach(s -> linesCsvCityMap.put(i.getAndIncrement(), s));
            }

            // creating city data
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

            System.out.println("Enter the number");
            System.out.println("-----------------------------");
            System.out.println("1: domestic flight");
            System.out.println("2: international flight");
            userDecision = sc.nextInt();
            if (userDecision == 1 || userDecision == 2) {
                userDecisionList.add(String.valueOf(userDecision));
            } else {
                // message and try again
            }

            // Ask the country
            System.out.println("");
            System.out.println("Departure: Enter the number");
            System.out.println("-----------------------------");
            for (int i = 1; i < linesCsvCountry.size(); i++) {
                System.out.println(i + ": " + linesCsvCountry.get(i));
            }
            userDecision = sc.nextInt();
            // hitしない場合の処理
            if (0 < userDecision && userDecision < linesCsvCountry.size()) {
                // Asking the city
                System.out.println("");
                System.out.println("Departure: Enter the number");
                System.out.println("-----------------------------");

                userDecisionList.add(linesCsvCountry.get(userDecision));
                for (int i = 0; i < linesCsvCity.size() - 1; i++) {
                    if (linesCsvCountry.get(userDecision).equals(cityList.get(i).getCountryName())) {
                        count++;
                        System.out.println(count + ": " + cityList.get(i).getCityName());
                        countMap.put(count, cityList.get(i).getCityName());
                    }
                }
                count = 0; // intialized
                userDecision = sc.nextInt();
                // 判定処理入れる
                userDecisionList.add(countMap.get(userDecision));
                countMap.clear(); // intialized
            }

            // Asking the country
            System.out.println("");
            System.out.println("Arrival: Enter the number");
            System.out.println("-----------------------------");

            if (userDecisionList.get(0).equals("1")) { // 後で修正
                for (int i = 1; i < linesCsvCountry.size(); i++) {
                    if (userDecisionList.get(1).equals(linesCsvCountry.get(i))) {
                        count++;
                        System.out.println(count + ": " + linesCsvCountry.get(i));
                        countMap.put(count, linesCsvCountry.get(i));
                    }
                }
            }

            else if (userDecisionList.get(0).equals("2")) { // 後で修正
                for (int i = 1; i < linesCsvCountry.size(); i++) {
                    if (!userDecisionList.get(1).equals(linesCsvCountry.get(i))) {
                        count++;
                        System.out.println(count + ": " + linesCsvCountry.get(i));
                        countMap.put(count, linesCsvCountry.get(i));
                    }
                }
            }
            count = 0; // initialized
            userDecision = sc.nextInt();
            userDecisionList.add(countMap.get(userDecision));
            countMap.clear(); // intialized
            // hitしない場合の処理

            // Asking the city
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
            count = 0; // initialized
            userDecision = sc.nextInt();
            userDecisionList.add(countMap.get(userDecision));
            countMap.clear(); // intialized

            System.out.println(userDecisionList);
            // Show the Flight
            System.out.println("");
            System.out.println("Show your flight");
            System.out.println("-----------------------------");

            for (int i = 0; i < flightList.size(); i++) {
                if (userDecisionList.get(2).equals(flightList.get(i).getArrivalCity())
                        && userDecisionList.get(4).equals(flightList.get(i).getDepartureCity())) {

                    // add the flight information to List
                    userDecisionList.add(flightList.get(i).getFlight());
                    userDecisionList.add(flightList.get(i).getPayment());
                    System.out.println(flightList.get(i).getArrivalCity());
                    System.out.println(flightList.get(i).getDepartureCity());
                    System.out.println(flightList.get(i).getFlight());
                    System.out.println(flightList.get(i).getPayment());
                }
            }

            // Choose the date
            System.out.println("");
            System.out.println("Select the date between: ");
            today = sdf.format(calendar.getTime());
            calendar.add(Calendar.MONTH, 3);
            todayAdded = sdf.format(calendar.getTime());
            yyyyMmDd = today.substring(0, 8);
            yyyyMmDdAdded = todayAdded.substring(0, 8);
            System.out.println(yyyyMmDd +" - " + yyyyMmDdAdded);
            userDecisionDate = sc.next();

            String userDate = DateCheck(userDecisionDate);

            if (yyyyMmDd.compareTo(userDate) <= 0 && yyyyMmDdAdded.compareTo(userDate) >= 0) {
                System.out.println("Lets see: " + userDate);
            }
            
            // calender add 修正
            userDecisionList.add(userDate);
            ReadFlightSeatData.readFlightSeatData(userDecisionList,userInformation,userFlightInfo);

        } catch (Exception e) {
            System.out.println("error");
        }

    }

    public String DateCheck(String date) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
            String userDate = dtf.format(LocalDate.parse(date, dtf)); // ←LocalDate.parseでDateTimeParseExceptionがThrowされる
            return userDate;

            // ■ 問題点
        } catch (DateTimeParseException dtp) {
            return "You must enter the valid date: ";
        }
    }

}