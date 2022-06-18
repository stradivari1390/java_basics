import java.text.DecimalFormat;

public class Keyboard {
    static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private final KeyBoardType keyboardType;
    private final Backlight backlight;
    private final double weight;

    public Keyboard(KeyBoardType keyboardType, Backlight backlight, double weight) {
        this.keyboardType = keyboardType;
        this.backlight = backlight;
        this.weight = weight;
    }

    public KeyBoardType getKeyboardType() {
        return keyboardType;
    }

    public Backlight getBacklight() {
        return backlight;
    }

    public double getWeight() {
        return weight;
    }
    Keyboard setKeyboardType(KeyBoardType keyboardType) {
        return new Keyboard(keyboardType, backlight, weight);
    }
    Keyboard setBacklight(Backlight backlight) {
        return new Keyboard(keyboardType, backlight, weight);
    }
    Keyboard setWeight(double weight) {
        return new Keyboard(keyboardType, backlight, weight);
    }

    @Override
    public String toString() {
        return "\nKeyboard: " +
                "type - " + keyboardType +
                " | backlight - " + backlight +
                " | weight - " + decimalFormat.format(weight) + " gr";
    }
}