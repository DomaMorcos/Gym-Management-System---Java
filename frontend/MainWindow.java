package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JPanel mainP;
    private JButton adminRoleButton;
    private JButton trainerRoleButton;
    private JLabel titleLabel;

    public MainWindow() {
        setVisible(true);
        setTitle("GYM System");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainP);
        setLocationRelativeTo(null);  // Center the window

        adminRoleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminLoginWindow adminLogin = new AdminLoginWindow();
                //adminLogin.setVisible(true);
                dispose();  // Close main window if needed
            }
        });

        trainerRoleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TrainerLoginWindow trainerLogin = new TrainerLoginWindow();
                trainerLogin.setVisible(true);
                dispose();  // Close main window if needed
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
        });

    }

}
