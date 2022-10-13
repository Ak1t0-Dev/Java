import Animal.Reptile.Crocodile.Crocodile;
import Animal.Fish.Eel.Eel;
import Animal.Birds.Eagle.Eagle;

public class App {
    public static void main(String[] args) throws Exception {
        Crocodile crocodile = new Crocodile(150, 50, "Reptile", "AB", "", "",
                "");
        Eel eel = new Eel(40, 20, "Fish", "A", "", "");
        Eagle eagle = new Eagle(80, 150, "Eagle", "O", "", "");
        Eagle eagle1 = new Eagle();
        crocodile.ShowInfo();
        eel.ShowInfo();
        eagle.ShowInfo();
        eagle1.ShowInfo();
    }
}