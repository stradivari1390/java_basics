import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame();

        frame.setSize(600, 400);

        //frame.add(new Background("src/main/resources/images/background.png"));

        frame.add(new MainForm().getMainPanel());

        Image icon = new javax.swing.ImageIcon("src/main/resources/images/icon.jpg").getImage();
        frame.setIconImage(icon);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
