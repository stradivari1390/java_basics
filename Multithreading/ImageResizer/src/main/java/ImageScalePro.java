import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageScalePro extends Thread {
    private File[] files;
    private int width;
    private String dstFolder;
    private long start;

    public ImageScalePro(File[] files, int width, String dstFolder, long start) {
        this.files = files;
        this.width = width;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                if (width > 0) {
                    Scalr.Mode mode = Scalr.Mode.AUTOMATIC;
                    BufferedImage resultImage = null;
                    if (image.getWidth() < width) {
                        resultImage = image;
                    } else {
                        int height = (int) Math.round(
                                image.getHeight() / (image.getWidth() / (double) width)
                        );
                        resultImage = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, mode, width, height);
                    }
                    File newFile = new File(dstFolder + "/" + file.getName());
                    ImageIO.write(resultImage, "jpg", newFile);
                }
            }
            System.out.println("processed within " + (System.currentTimeMillis() - start) + "ms");
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}