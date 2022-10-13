package Animal.Birds.Eagle;

import Animal.Birds.Birds;

public class Eagle extends Birds{

    // Default Constructor
    public Eagle() {
    }

    // Parameterized Constructor
    public Eagle(int height, int weight, String animalType, String bloodType, String features, String ability) {
        super(height, weight, animalType, bloodType, features, ability);
    }
    
    public void ShowInfo() {
        System.out.println("Height: " + getHeight());
        System.out.println("Weight: " + getWeight());
        System.out.println("AnimalType: " + getAnimalType());
        System.out.println("BloodType: " + getBloodType());
        System.out.println("Features: " + getFeatures());
        System.out.println("Ability: " + getAbility());
    };

}
