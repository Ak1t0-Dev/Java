import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateNewAccount {

    private static String firstName;
    private static String lastName;
    private static String emailAddress;
    private static String passWord;
    private static String passportNumber;
    private static String bornDate;
    private static String registerAccount;

    public static void createNewAccount() throws IOException, NoSuchAlgorithmException {
        int flag = 0;
        int flagInner;
        Scanner sc = new Scanner(System.in);
        List<String> userAccount = new ArrayList<>();

            while (flag == 0) {
                // initialized
                flagInner = 0;

                // user's first name
                while (flagInner == 0) {
                    System.out.println("Enter your first name(1-20): ");
                    firstName = sc.next();
                    // name condition limit 20
                    if (firstName.length() > 0 && firstName.length() <= 20) {
                        flagInner = 1;
                    } else {
                        System.out.println("first name has 20 characters limit");
                    }
                }

                flagInner = 0;

                // user's last name
                while (flagInner == 0) {
                    System.out.println("Enter your last name(1-20): ");
                    lastName = sc.next();
                    // name condition limit 20
                    if (lastName.length() > 0 && lastName.length() <= 20) {
                        flagInner = 1;
                    } else {
                        System.out.println("last name has 20 characters limit");
                    }
                }

                flagInner = 0;

                // user's born date
                while (flagInner == 0) {
                    System.out.println("Enter your born date(yyyymmdd)");
                    bornDate = sc.next();
                    // name condition limit 20
                    if (bornDate.length() != 8) {
                        System.out.println("Born date should be 8 limit");
                    } else {
                        String check = UserInputCheck.dateCheck(bornDate);
                        if (check == "") {
                            System.out.println("Born date is invalid");
                        } else {
                            flagInner = 1;
                        }
                    }
                }

                flagInner = 0;

                // user's email address
                while (flagInner == 0) {
                    System.out.println("Enter your email address: ");
                    emailAddress = sc.next();
                    boolean check = UserInputCheck.emailAddressCheck(emailAddress);
                    if (check) {
                        flagInner = 1;
                    } else {
                        System.out.println("email address is invalid");
                    }
                }

                flagInner = 0;

                // user's passport number
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

                // user's password
                while (flagInner == 0) {
                    System.out.println("Enter your new password(8 - 20): ");
                    System.out.println("(including upperletter, lowerletter, number and symbol)");
                    passWord = sc.next();
                    boolean check = UserInputCheck.passwordCheck(passWord);
                    if (check) {
                        flagInner = 1;
                    } else {
                        System.out.println("password length should be between 8 and 20");
                        System.out.println("password should be include upperletter, lowerletter, number and symbol");
                    }
                }

                flagInner = 0;

                // show user's input
                System.out.println("====================================");
                System.out.println("first name: " + firstName);
                System.out.println("last name: " + lastName);
                System.out.println("born date: " + bornDate);
                System.out.println("email address: " + emailAddress);
                System.out.println("passport number: " + passportNumber);
                System.out.println("password: " + passWord);
                System.out.println("====================================");

                while (flagInner == 0) {
                    System.out.println("Do you want to create your account based on these information?");
                    System.out.println("1: Yes");
                    System.out.println("2: No (reenter the information)");
                    System.out.println("3: Exit");
                    registerAccount = sc.next();

                    // if confirmAccount is yes
                    switch (registerAccount) {
                        // create an user account
                        case "1":
                            userAccount.add(firstName);
                            userAccount.add(lastName);
                            userAccount.add(bornDate);
                            userAccount.add(emailAddress);
                            userAccount.add(passportNumber);
                            userAccount.add(passWord);
                            RegisterAccount.regsiterAccount(userAccount);
                            flagInner = 1;
                            flag = 1;
                            break;

                        // reenter user information
                        case "2":
                            flagInner = 1;
                            break;

                        // exit
                        case "3":
                            flagInner = 1;
                            flag = 1;
                            break;

                        default:
                            System.out.println("Please Enter the valid number");
                            break;

                    }
                }
            }
    }
}
