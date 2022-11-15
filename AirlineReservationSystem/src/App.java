import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class App {
    public static void main(String[] args) throws Exception {
        try {
        ManipulateCsv.createflightdata();
        Menu.showMenu();
        } catch (IOException e) {
            System.out.print("reading/writing a file is failed.");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.out.print("Encrypting data is failed.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.print("Sorry, something is wrong.");
            e.printStackTrace();
        }
    }
}
