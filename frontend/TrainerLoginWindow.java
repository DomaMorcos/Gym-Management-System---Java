package frontend;

import constants.LoginCredentials;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainerLoginWindow extends JFrame {

    private JPanel TrainerLoginP;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    public TrainerLoginWindow () {
        setVisible(true);
        setTitle("Trainer Login");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(TrainerLoginP);
        setLocationRelativeTo(null);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals(LoginCredentials.TRAINER_USERNAME) && password.equals(LoginCredentials.TRAINER_PASSWORD)) {
                    TrainerRoleWindow trainerRole = new TrainerRoleWindow();
                    trainerRole.setVisible(true);
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(TrainerLoginP,"Wrong username or password!");
            }
        });
    }
}
