package frontend;

import com.sun.tools.javac.Main;
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
    private JButton backButton;

    public TrainerLoginWindow () {
        setVisible(true);
        setTitle("Trainer Login");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(TrainerLoginP);
        setLocationRelativeTo(null);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());

                if(username.isEmpty() || password.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Please fill all the required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (username.equals(LoginCredentials.TRAINER_USERNAME) && password.equals(LoginCredentials.TRAINER_PASSWORD)) {
                    TrainerRoleWindow trainerRole = new TrainerRoleWindow();
                    trainerRole.setVisible(true);
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(TrainerLoginP,"Invalid username or password!","Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
                dispose();
            }
        });
    }
}
