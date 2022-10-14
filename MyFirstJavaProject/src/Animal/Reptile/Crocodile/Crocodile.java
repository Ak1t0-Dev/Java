package Animal.Reptile.Crocodile;

import Animal.Reptile.Reptile;

public class Crocodile extends Reptile {

    // Default Constructor
    public Crocodile() {
        this.setEggType("Hard-shelled eggs");
    }

    // Parameterized Constructor
    public Crocodile(int height, int weight, String animalType, String bloodType, String skinType, String bone,
            String eggType) {
        super(height, weight, animalType, bloodType, skinType, bone, eggType);
    }

 
    public void ShowInfo() {
        System.out.println("Height: " + getHeight());
        System.out.println("Weight: " + getWeight());
        System.out.println("AnimalType: " + getAnimalType());
        System.out.println("BloodType: " + getBloodType());
        System.out.println("SkinType: " + getSkinType());
        System.out.println("Bone: " + getBone());
        System.out.println("EggType: " + getEggType());
    }

}