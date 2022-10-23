import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// • Make a record to store information about a car. It should contain fields for:
// - make (String)
// - model (String)
// - year (int)
// • Create an ArrayList of Car objects.
// • Sort the ArrayList of cars by year (Ascending) and print them out.

public class ArrayListQuestionThree {
    public static void main(String[] args) {
        ArrayList<Car> carList = new ArrayList<>();

        // adding Car objects to the ArrayList
        carList.add(new Car("Audi", "A3", 2022));
        carList.add(new Car("Lexus", "R3", 1993));
        carList.add(new Car("Mercedes-Benz", "Mercedes-AMG A-Class", 2021));
        carList.add(new Car("Lamborghini", "Huracan", 2022));
        carList.add(new Car("Toyota", "A3", 1991));

        // sorting the carList
        Collections.sort(carList, new CarComparator());

        for (int i = 0; i < carList.size(); i++) {
            System.out.println("Make: " + carList.get(i).getMake());
            System.out.println("Model: " + carList.get(i).getModel());
            System.out.println("Year: " + carList.get(i).getYear());
            System.out.println("--------------------------------------");
        }
    }
}

class Car {
    String make;
    String model;
    int year;

    public Car() {
    }

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}

// sorting the carList by year(ascending)
class CarComparator implements Comparator<Car> {

    @Override
    public int compare(Car c1, Car c2) {
        return c1.year - c2.year;
    }
}
