package Animal.Fish;
import Animal.Animal;

public class Fish extends Animal {
    private String habitat;
    private String features;

    // Default Constructor
    public Fish() {
    }

    // Parameterized Constructor
    public Fish(int height, int weight, String animalType, String bloodType, String habitat, String features) {
        super(height, weight, animalType, bloodType);
        this.habitat = "Live in water";
        this.features = "Has gills";
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }
}
