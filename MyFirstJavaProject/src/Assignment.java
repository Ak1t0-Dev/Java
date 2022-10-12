import java.util.Scanner;

public class Assignment {
    public static void main(String[] args) throws Exception {
        int num = 0; // for input number
        int printNum = 0;// for multiply result
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("input a number to multiply with 10: ");
                num = scan.nextInt();
                printNum = num * 10;
                System.out.println("result: " + printNum);
                scan.close();
                break;
            } catch (Exception e) {
                System.out.println("you need to input an integer!!");
                scan.next();
            }
        }
    }
}
