import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class RecursiveWriting extends RecursiveAction {

    private final FileWriter output;

    private String[] regionCodes;

    private final char[] letters = {'Y', 'K', 'E', 'H', 'X', 'B', 'A', 'P', 'O', 'C', 'M', 'T'};

    private final DecimalFormat decimalFormat = new DecimalFormat("000");

    private static final int THRESHOLD = 3;

    public RecursiveWriting(String[] regionCodes, FileWriter output) {
        this.regionCodes = regionCodes;
        this.output = output;
    }

    @Override
    protected void compute() {
        if (regionCodes.length > THRESHOLD) {
            ForkJoinTask.invokeAll(createSubtasks());
        } else {
            try {
                processing(regionCodes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private List<RecursiveWriting> createSubtasks() {
        List<RecursiveWriting> subtasks = new ArrayList<>();

        String[] partOne = new String[regionCodes.length / 2];
        System.arraycopy(regionCodes, 0, partOne, 0, partOne.length);

        String[] partTwo = new String[regionCodes.length - partOne.length];
        System.arraycopy(regionCodes, partOne.length, partTwo, 0, partTwo.length);

        subtasks.add(new RecursiveWriting(partOne, output));
        subtasks.add(new RecursiveWriting(partTwo, output));

        return subtasks;
    }

    private void processing(String[] regionCodes) throws IOException {
        StringBuilder builder = new StringBuilder();
        String result;
        for (String regionCode : regionCodes) {
            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            builder.append(firstLetter)
                                    .append(decimalFormat.format(number))
                                    .append(secondLetter)
                                    .append(thirdLetter)
                                    .append(regionCode)
                                    .append('\n');
                        }
                    }
                }
            }
        }
        result = builder.toString();
        synchronized (output) {
            output.write(result);
            output.flush();
        }
    }
}