import java.util.Scanner;

public class Wedge {
    public static void main(String[] args) throws Exception {
        int num = 0; // for input number
        Scanner scan = new Scanner(System.in);
        System.out.print("You entered: ");
        num = scan.nextInt();
        for (int i = 0; i < num; i++) {
            for (int j = num - i; j > 0; j--) {
                System.out.print("@");
            }
            System.out.println("");
        }
        scan.close();
    }

}
