import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

// • Create an ArrayList of Integers
// • Fill each of the 10 slots with a random value from 1-50.
// • Display those values on the screen, and then prompthe user for an integer.
// • Search through the ArrayList, and if the item is present, print a message that the number is in the list.
// • If the value is not in the ArrayList, print a message that the number is not in the list
public class ArrayListQuestionOne {

    public static void main(String[] args) throws Exception {
        int userInput = 0;
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();

        // adding 10 random nounbers(0 - 50) to the list
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(51));
        }

        try {
            System.out.println("numbers inside of an array");
            System.out.println(list);
            System.out.println("enter a number: ");
            userInput = sc.nextInt();

            // judging an input number is in the list
            if (list.contains(userInput)) {
                System.out.println(userInput + " is in the list");
            } else {
                System.out.println(userInput + " is not in the list");
            }
        } catch (InputMismatchException e) {
            System.out.println("you should enter an integer");
        } catch (Exception e) {
            System.out.println("sorry, something is wrong");
        } finally {
            sc.close();
        }
    }

}
