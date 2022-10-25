import java.util.ArrayList;
import java.util.Random;

// • Create an ArrayList of Integers
// • Fill the ArrayList with ten random numbers (1-50)
// • Copy each value from the ArrayList Into another ArrayList of the same capacity
// • Change the last value in the first (original) ArrayList to a -5
// • Display the contents of both ArrayLists
public class ArrayListQuestionTwo {

    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> listCopy = new ArrayList<>();
        Random random = new Random();

        // adding 10 random nounbers(0 - 50) to the list
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(51));
        }

        // giving all numbers from the list to the new list
        listCopy.addAll(list);
        listCopy.set(9, listCopy.get(9) - 5);
        System.out.println("the 1st Array: " + list);
        System.out.println("the 2nd Array: " + listCopy);

    }

}
