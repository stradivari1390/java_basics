public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount() + "\n");

        for (int i = 0; i <= 65535; i++) {
            char test = (char)i;
            for (RussianAlphabet letter : RussianAlphabet.values()) {
                if (letter.name().equals(String.valueOf(test))) {
                    System.out.println(i + " = " + (char) i);
                }
            }
        }
    }
}
