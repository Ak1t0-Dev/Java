public class StringOperation {
    
    // padding the numbers with space to right
    public static String padRight(int n, String s) {
        return String.format("%-" + n + "s", s);
    }

    // change alphabet to number
    public static int changeToNumber(String alphabet) {
        if (alphabet.matches("[a-zA-Z]")) {
            char[] arrayAlphabet = alphabet.toCharArray();
            char charAlphabet = arrayAlphabet[0];
            charAlphabet = Character.toUpperCase(charAlphabet);
            int number = (int) charAlphabet - (int) 'A';
            return number;
        } else {
            return -1;
        }
    }

    // change to UpperCase
    public static String changeToUpper(String alphabet) {
        char[] arrayAlphabet = alphabet.toCharArray();
        char charAlphabet = arrayAlphabet[0];
        return String.valueOf(Character.toUpperCase(charAlphabet));
    }
}
