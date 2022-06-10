public class Arithmetic {
    private int a;
    private int b;

    public Arithmetic(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int sum() {
        return a + b;
    }

    public int multiplication() {
        return a * b;
    }

    public int largestNum() {
        return Math.max(a, b);
    }

    public int smallestNum() {
        return Math.min(a, b);
    }
}
