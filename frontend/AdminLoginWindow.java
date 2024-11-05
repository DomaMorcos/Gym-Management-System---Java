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

                if(username.equals(LoginCredentials.ADMIN_USERNAME) && password.equals(LoginCredentials.ADMIN_PASSWORD)){
                    AdminRoleWindow adminRole=new AdminRoleWindow();
                    adminRole.setVisible(true);
                    dispose();
                } else JOptionPane.showMessageDialog(AdminLogin, "Invalid Username or Password");
            }
        });
    }
}
