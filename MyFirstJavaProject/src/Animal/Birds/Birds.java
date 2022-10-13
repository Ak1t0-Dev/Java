package Animal.Birds;
import Animal.Animal;

public class Birds extends Animal {

    private String features;
    private String ability;

    // Default Constructor
    public Birds() {
    }

    // Parameterized Constructor
    public Birds(int height, int weight, String animalType, String bloodType, String features, String ability) {
        super(height, weight, animalType, bloodType);
        this.features = "Animal with feathers";
        this.ability = "Can fly";
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }




    
}
