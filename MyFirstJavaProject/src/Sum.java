import java.util.Scanner;

public class Sum {
    public static void main(String[] args) throws Exception {
        int num1 = 0; // for input number
        int num2 = 0; // for input number
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number: ");
        num1 = scan.nextInt();
        System.out.print("Enter the number: ");
        num2 = scan.nextInt();
        System.out.println("sum is: "+ (num1 + num2));
        scan.close();
    }
}
