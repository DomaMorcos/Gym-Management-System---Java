package frontend;

import backend.AdminRole;
import backend.TrainerRole;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AdminRoleWindow extends JFrame {
    private JButton removeTrainerButton;
    private JButton logoutButton;
    private JButton addTrainerButton;
    private JButton viewTrainersButton;
    private JPanel AdminRoleWindow;
    private AdminRole adminRole;

    public AdminRoleWindow() {

        setTitle("Admin Role");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(AdminRoleWindow);
        setLocationRelativeTo(null);
        setVisible(true);

        try {
           this.adminRole = new AdminRole();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        addTrainerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTrainerWindow addTrainer = new AddTrainerWindow();
                addTrainer.setVisible(true);
                dispose();
            }
        });
        removeTrainerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveTrainerWindow removeTrainer = new RemoveTrainerWindow();
                removeTrainer.setVisible(true);
                dispose();
            }
        });
        viewTrainersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewTrainersWindow viewTrainers = new ViewTrainersWindow();
                viewTrainers.setVisible(true);
                dispose();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    adminRole.logout();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
                dispose();
            }
        });
    }
}
