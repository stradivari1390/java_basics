import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class MainForm {

    private JPanel mainPanel;

    private JTextField lastnameTextField;

    private JTextField nameTextField;

    private JTextField middleNameTextField;

    private JButton collapseButton;

    private JLabel lastnameLabel;

    private JLabel nameLabel;

    private JLabel middleNameLabel;

    public MainForm() {
        collapseButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if(collapseButton.getText().equals("collapse") &&
                        lastnameTextField.getText().matches("^[A-Z][a-z]+$") &&
                        nameTextField.getText().matches("^[A-Z][a-z]+$") &&
                        (middleNameTextField.getText().matches("^[A-Z][a-z]+$") ||
                                middleNameTextField.getText().isEmpty())
                ) {
                    String fullName = lastnameTextField.getText() + " " +
                            nameTextField.getText() + " " +
                            middleNameTextField.getText();
                    nameLabel.setVisible(false);
                    middleNameLabel.setVisible(false);
                    nameTextField.setVisible(false);
                    middleNameTextField.setVisible(false);
                    lastnameLabel.setText("Full name:");
                    lastnameTextField.setText(fullName.trim());
                    collapseButton.setText("expand");
                } else if (collapseButton.getText().equals("expand") &&
                        (lastnameTextField.getText().matches("^[A-Z][a-z]+ [A-Z][a-z]+ [A-Z][a-z]+$") ||
                                lastnameTextField.getText().matches("^[A-Z][a-z]+ [A-Z][a-z]+$"))
                        ) {
                    nameLabel.setVisible(true);
                    middleNameLabel.setVisible(true);
                    nameTextField.setVisible(true);
                    middleNameTextField.setVisible(true);
                    String[] fullName = lastnameTextField.getText().split(" ");
                    switch (fullName.length) {
                        case 2 -> {
                            lastnameTextField.setText(fullName[0]);
                            nameTextField.setText(fullName[1]);
                        }
                        case 3 -> {
                            lastnameTextField.setText(fullName[0]);
                            nameTextField.setText(fullName[1]);
                            middleNameTextField.setText(fullName[2]);
                        }
                    }
                    lastnameLabel.setText("Lastname:");
                    collapseButton.setText("collapse");
                } else {
                    try {
                        JOptionPane.showMessageDialog(new Background("src/main/resources/images/background.png"),
                                "Wrong insertion", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
