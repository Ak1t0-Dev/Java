package Animal;

public class Animal {
    private int Height;
    private int Weight;
    private String AnimalType;
    private String BloodType;

    // Default Constructor
    public Animal() {
        this.Height = 0;
        this.Weight = 0;
        this.AnimalType = "AnimalType";
        this.BloodType = "BloodType";
    }

    // Parameterized Constructor
    public Animal(int height, int weight, String animalType, String bloodType) {
        this.Height = height;
        this.Weight = weight;
        this.AnimalType = animalType;
        this.BloodType = bloodType;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public String getAnimalType() {
        return AnimalType;
    }

    public void setAnimalType(String animalType) {
        AnimalType = animalType;
    }

    public String getBloodType() {
        return BloodType;
    }

    public void setBloodType(String bloodType) {
        BloodType = bloodType;
    }
}
