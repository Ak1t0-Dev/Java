package Animal.Fish.Eel;
import Animal.Fish.Fish;

public class Eel extends Fish {

    // Parameterized Constructor
    public Eel() {
    }

    // Parameterized Constructor
    public Eel(int height, int weight, String animalType, String bloodType, String habitat, String features) {
        super(height, weight, animalType, bloodType, habitat, features);
        setFeatures("Release electric charge");
    }

    public void ShowInfo() {
        System.out.println("Height: " + getHeight());
        System.out.println("Weight: " + getWeight());
        System.out.println("AnimalType: " + getAnimalType());
        System.out.println("BloodType: " + getBloodType());
        System.out.println("Habitat: " + getHabitat());
        System.out.println("Features: " + getFeatures());
    }
    
}
