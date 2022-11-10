import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MakeFlightData {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final String PARENT_PATH = "src/csv/flightData/";
    private static final String AIRLINEDATA_PATH = "src/csv/airlineData.csv";
    private static final int SEATS = 140;
    private static final int HOURS = 24;
    private static final String LINE_FEED_CODE = "\r\n";
    /*** 区切り文字（カンマ） */
    private static final String DELIMITER_COMMA = ",";
    private String pathCreate = "";
    private String fileCreate = "";
    private String pathFile = "";
    private String today = "";
    private String year = "";
    private String month = "";
    private String day = "";
    private String hour = "";
    private String todayAdded = "";
    private String yearAdded = "";
    private String monthAdded = "";
    private String dayAdded = "";
    private String hourAdded = "";

    public void makeflightdata() {
        try {
            today = sdf.format(calendar.getTime());
            year = today.substring(0, 4);
            month = today.substring(4, 6);
            day = today.toString().substring(6, 8);
            hour = today.toString().substring(8, 10);

            // add three month
            calendar.add(Calendar.MONTH, 3);

            List<String> linesCsvpathAirlineData = ManipulateCsv.readAllLineCsv(AIRLINEDATA_PATH);

            //  例外拾う
            if (linesCsvpathAirlineData == null) {
                System.out.println("IOException");
                return;
            } 

            for (int i = 0; i < 100; i++) {
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                todayAdded = sdf.format(calendar.getTime());
                yearAdded = todayAdded.substring(0, 4);
                monthAdded = todayAdded.substring(4, 6);
                dayAdded = todayAdded.toString().substring(6, 8);
                hourAdded = todayAdded.toString().substring(8, 10);
                pathCreate = PARENT_PATH + "/" + yearAdded + "/" + monthAdded + "/" + dayAdded + "/";

                Path folderPath = Paths.get(pathCreate);
                    
                if (Files.exists(folderPath)) {
                    break;
                } else {
                    Files.createDirectories(folderPath);
                    for (int k = 1; k < linesCsvpathAirlineData.size(); k++) {
                        pathFile = folderPath + "/" + linesCsvpathAirlineData.get(k) + ".csv";
                        Path filePath = Paths.get(pathFile);

                        if (Files.exists(filePath)) {
                            break;
                        } else {
                            FileOutputStream stream = new FileOutputStream(pathFile);
                            OutputStreamWriter writer = new OutputStreamWriter(stream, ConstData.UTF_8);
                            // write down to csv data
                            for (int l = 0; l < HOURS; l++) {
                                for (int m = 0; m < SEATS; m++) {
                                    writer.write("0");
                                    if (!(m == SEATS - 1)) {
                                        writer.write(DELIMITER_COMMA);
                                    }
                                }
                                writer.write(LINE_FEED_CODE);
                            }

                            writer.close();
                            stream.close();
                        }
                    }

                }

            }

        } catch (Exception e) {
            System.out.println("error");
        }
    }
}