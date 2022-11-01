import java.util.ArrayList;
import java.util.List;

public class Concatenation {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        StringBuilder builder = new StringBuilder();

        List<String> str = new ArrayList<>();

        String addText = "some text some text some text";

        for (int i = 0; i < 20_000_000; i++) {
            builder.append(addText);
            if(builder.capacity() > 6_000_000) {
                str.add(builder.toString());
                builder = new StringBuilder();
            }
        }
        str.add(builder.toString());

        System.out.println((System.currentTimeMillis() - start) + " ms");

        System.out.println(str.size());
        System.out.println(str.stream().map(String::length).reduce(Integer::sum));
    }
}
