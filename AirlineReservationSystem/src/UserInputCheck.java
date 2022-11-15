import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class UserInputCheck {

    // check if the date is existed (yyyymmdd)
    public static String dateCheck(String bornDate) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
            String bornDateChecked = dtf.format(LocalDate.parse(bornDate, dtf));
            return bornDateChecked;
        } catch (DateTimeParseException dtp) {
            return "";
        }
    }

    // check if email is valid
    public static boolean emailAddressCheck(String emailAddress) {
        boolean result = false;
        if (emailAddress != null) {
            Pattern pattern = Pattern.compile(
                    "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
            result = pattern.matcher(emailAddress).matches();
        }
        return result;
    }

    // check if password is valid
    // length 8-20, includes upper letter, lower letter, and symbol
    public static boolean passwordCheck(String password) {
        boolean result = false;
        if (password != null) {
            Pattern pattern = Pattern.compile(
                    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
            result = pattern.matcher(password).matches();
        }
        return result;
    }

    // check if it can change a value to Integer
    public static int changeStringtoInt(String userInput) {
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
