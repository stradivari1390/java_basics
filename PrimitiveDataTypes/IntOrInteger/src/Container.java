public class Container {
    private Integer count;

    public void addCount(int value) {
        if (count == null) {
            count = 0;
        }
        count += value;
    }

    public int getCount() {
        return count;
    }
}
