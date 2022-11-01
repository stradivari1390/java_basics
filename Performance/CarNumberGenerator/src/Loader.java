import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Loader {

    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        FileWriter output = new FileWriter("res/numbers.txt");

        String[] regionCodes;
        regionCodes = Arrays.stream(RegionCode.values()).map(r -> r.code).toArray(String[]::new);

        ForkJoinPool commonPool = ForkJoinPool.commonPool();

        commonPool.invoke(new RecursiveWriting(regionCodes, output));

        System.out.println((System.currentTimeMillis() - start) + " ms");

        output.close();
    }
}