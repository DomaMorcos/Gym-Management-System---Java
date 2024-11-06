package frontend;

import constants.LoginCredentials;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginWindow extends JFrame {

    private JPanel AdminLogin;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JFormattedTextField usernameField;
    private JButton backButton;

    public AdminLoginWindow() {
        setVisible(true);
        setTitle("Admin Login");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(AdminLogin);
        setLocationRelativeTo(null);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username=usernameField.getText().trim();
                String password=new String(passwordField1.getPassword());
                if(username.isEmpty() || password.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Please fill all the required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(username.equals(LoginCredentials.ADMIN_USERNAME) && password.equals(LoginCredentials.ADMIN_PASSWORD)){
                    AdminRoleWindow adminRole=new AdminRoleWindow();
                    adminRole.setVisible(true);
                    dispose();
                } else JOptionPane.showMessageDialog(AdminLogin, "Invalid Username or Password","Error",JOptionPane.ERROR_MESSAGE);
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
