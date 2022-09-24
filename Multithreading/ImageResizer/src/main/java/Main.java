import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String srcFolder = "src/main/resources";
        String dstFolder = "out/photos";
        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        List<File> sortedFilesList = Stream.of(Objects.requireNonNull(srcDir.listFiles()))
                .sorted(Comparator.comparing(File::length)).toList();

        double half = sortedFilesList.size() / 2.0D;

        File[] files1 = new File[(int) Math.floor((int) Math.floor(half) / 2.0D)];
        File[] files2 = new File[(int) Math.floor((int) Math.ceil(half) / 2.0D)];
        File[] files3 = new File[(int) Math.ceil((int) Math.floor(half) / 2.0D)];
        File[] files4 = new File[(int) Math.ceil((int) Math.ceil(half) / 2.0D)];

        for (int i = 4; i <= sortedFilesList.size(); i += 4) {
            files4[i / 4 - 1] = sortedFilesList.get(i - 4);
            files3[i / 4 - 1] = sortedFilesList.get(i - 3);
            files2[i / 4 - 1] = sortedFilesList.get(i - 2);
            files1[i / 4 - 1] = sortedFilesList.get(i - 1);
        }

        switch (sortedFilesList.size() % 4) {
            case 0:
                break;
            case 1:
                files4[sortedFilesList.size() / 4] = sortedFilesList.get(sortedFilesList.size() - 1);
                break;
            case 2:
                files4[sortedFilesList.size() / 4] = sortedFilesList.get(sortedFilesList.size() - 2);
                files3[sortedFilesList.size() / 4] = sortedFilesList.get(sortedFilesList.size() - 1);
                break;
            case 3:
                files4[sortedFilesList.size() / 4] = sortedFilesList.get(sortedFilesList.size() - 3);
                files3[sortedFilesList.size() / 4] = sortedFilesList.get(sortedFilesList.size() - 2);
                files2[sortedFilesList.size() / 4] = sortedFilesList.get(sortedFilesList.size() - 1);
                break;
        }

        ImageScalePro imageScalePro1 = new ImageScalePro(files1, 200, dstFolder, start);
        imageScalePro1.start();
        ImageScalePro imageScalePro2 = new ImageScalePro(files2, 300, dstFolder, start);
        imageScalePro2.start();
        ImageScalePro imageScalePro3 = new ImageScalePro(files3, 200, dstFolder, start);
        imageScalePro3.start();
        ImageScalePro imageScalePro4 = new ImageScalePro(files4, 300, dstFolder, start);
        imageScalePro4.start();
    }
}
