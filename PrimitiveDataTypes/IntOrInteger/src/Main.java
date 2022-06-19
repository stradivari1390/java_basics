import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount() + "\n");
        RussianAlphabet[] letters = RussianAlphabet.values();
        String j = Arrays.toString(letters);
        for (int i = 0; i <= 65535; i++) {
            if (i != 32 && i != 44 && i != 91 && i != 93 &&
                    j.contains(String.valueOf((char) i))) {
                System.out.println(i + " = " + (char) i);
            }
        }
    }
}
