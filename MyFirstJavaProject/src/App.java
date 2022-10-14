import Animal.Birds.Eagle.Eagle;
import Animal.Fish.Eel.Eel;
import Animal.Reptile.Crocodile.Crocodile;

public class App {
    public static void main(String[] args) throws Exception {
        // create from parameterlized constructor
        Crocodile crocodile = new Crocodile(150, 50, "Reptile", "AB", "none", "none",
                "none");
        Eel eel = new Eel(40, 20, "Fish", "A", "none", "none");
        Eagle eagle = new Eagle(80, 150, "Eagle", "O", "none", "none");
        crocodile.ShowInfo();
        eel.ShowInfo();
        eagle.ShowInfo();

        // create from default constructor
        Crocodile crocodileDefault = new Crocodile();
        Eel eelDefault = new Eel();
        Eagle eagleDefault = new Eagle();
        crocodileDefault.ShowInfo();
        eelDefault.ShowInfo();
        eagleDefault.ShowInfo();
    }
}