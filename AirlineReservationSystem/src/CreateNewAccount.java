import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CreateNewAccount {

    private static String firstName;
    private static String lastName;
    private static String emailAddress;
    private static String passWord;
    private static String passportNumber;
    private static String bornDate;
    private static String confirmAccount;
    private static String confirmAccountLowerCase;

    public static void createNewAccount() {
        int flag = 0;
        int flagInner = 0;
        while (flag == 0) {
            try {
                Scanner sc = new Scanner(System.in);
                List<String> userAccount = new ArrayList<>();

                while (flagInner == 0) {
                    System.out.println("Enter your first name(1-20): ");
                    firstName = sc.next();
                    // name condition limit 20
                    if (firstName.length() > 0 && firstName.length() <= 20) {
                        flagInner = 1;
                    } else {
                        System.out.println("first name has 20 character limit");
                    }
                }

                flagInner = 0;

                while (flagInner == 0) {
                    System.out.println("Enter your last name(1-20): ");
                    lastName = sc.next();
                    // name condition limit 20
                    if (lastName.length() > 0 && lastName.length() <= 20) {
                        flagInner = 1;
                    } else {
                        System.out.println("last name has 20 character limit");
                    }
                }

                flagInner = 0;

                while (flagInner == 0) {
                    System.out.println("Enter your born date(yyyymmdd)");
                    bornDate = sc.next();
                    // name condition limit 20
                    if (bornDate.length() != 8) {
                        System.out.println("Born date should be 8 limit");
                    } else {
                        String check = bornDateCheck(bornDate);
                        if (check == "") {
                            System.out.println("Born date is invalid");
                        } else {
                            flagInner = 1;
                        }
                    }
                }

                flagInner = 0;

                while (flagInner == 0) {
                    System.out.println("Enter your email address: ");
                    emailAddress = sc.next();
                    boolean check = emailAddressCheck(emailAddress);
                    if (check) {
                        flagInner = 1;
                    } else {
                        System.out.println("email address is invalid");
                    }
                }

                flagInner = 0;

                while (flagInner == 0) {
                    System.out.println("Enter your new passportNumber(9): ");
                    passportNumber = sc.next();
                    if (passportNumber.length() == 9) {
                        flagInner = 1;
                    } else {
                        System.out.println("passport number length should be 9");
                    }
                }

                flagInner = 0;

                while (flagInner == 0) {
                    System.out.println("Enter your new password(8 - 20): ");
                    passWord = sc.next();
                    if (passWord.length() >= 8 && passWord.length() <= 20) {
                        flagInner = 1;
                    } else {
                        System.out.println("password length should be between 8 and 20");
                    }
                }

                flagInner = 0;

                System.out.println("====================================");
                System.out.println("first name: " + firstName);
                System.out.println("last name: " + lastName);
                System.out.println("born date: " + bornDate);
                System.out.println("email address: " + emailAddress);
                System.out.println("passport number: " + passportNumber);
                System.out.println("password: " + passWord);
                System.out.println("====================================");
                while (flagInner == 0) {
                    System.out.println("Register your account?(yes/no)");

                    confirmAccount = sc.next();
                    confirmAccountLowerCase = confirmAccount.toLowerCase();

                    // if confirmAccount is yes
                    if (confirmAccountLowerCase.equals(ConstData.DECSION_YES)
                            || confirmAccountLowerCase.equals(ConstData.DECSION_Y)) {
                        userAccount.add(firstName);
                        userAccount.add(lastName);
                        userAccount.add(bornDate);
                        userAccount.add(emailAddress);
                        userAccount.add(passportNumber);
                        userAccount.add(passWord);
                        RegisterAccount.regsiterAccount(userAccount);
                        flagInner = 1;
                        flag = 1;

                    } else if (confirmAccountLowerCase.equals(ConstData.DECSION_NO)
                            || confirmAccountLowerCase.equals(ConstData.DECSION_N)) {
                        return;
                    } else {
                        System.out.println("Enter yes or no");
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Input is invalid");
                flagInner = 0;

            } catch (Exception e) {
                System.out.println("Sorry, something is wrong");
                flag = 1;

            }
        }
    }

    public static String bornDateCheck(String bornDate) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
            String bornDateChecked = dtf.format(LocalDate.parse(bornDate, dtf));
            return bornDateChecked;
        } catch (DateTimeParseException dtp) {
            return "";
        }
    }

    public static boolean emailAddressCheck(String value) {
        boolean result = false;
        if (value != null) {
            Pattern pattern = Pattern.compile(
                    "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
            result = pattern.matcher(value).matches();
        }
        return result;
    }
}
