package frontend;

import backend.TrainerRole;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TrainerRoleWindow extends JFrame {
    private JPanel TrainerRoleP;
    private JButton addMemberButton;
    private JButton logoutButton;
    private JButton viewRegistrationsButton;
    private JButton cancelRegistrationButton;
    private JButton registerMemberForClassButton;
    private JButton viewClassesButton;
    private JButton addClassButton;
    private JButton viewMembersButton;
    private TrainerRole trainerRole;



    public TrainerRoleWindow() {
        setVisible(true);
        setTitle("Trainer Role");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(TrainerRoleP);
        setLocationRelativeTo(null);

        try {
            trainerRole = new TrainerRole();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddMemberWindow addMember = new AddMemberWindow();
                addMember.setVisible(true);
                dispose();
            }
        });
        viewMembersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewMembersWindow viewMembers = new ViewMembersWindow();
                //viewMembers.setVisible(true);
                dispose();
            }
        });
        addClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddClassWindow addClass = new AddClassWindow();
                //addClass.setVisible(true);
                dispose();
            }
        });
        viewClassesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewClassesWindow viewClasses = new ViewClassesWindow();
                //viewClasses.setVisible(true);
                dispose();
            }
        });
        registerMemberForClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterMemberForClassWindow registerMemberForClass = new RegisterMemberForClassWindow();
                //registerMemberForClass.setVisible(true);
                dispose();
            }
        });
        cancelRegistrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CancelRegistrationWindow cancelRegistration = new CancelRegistrationWindow();
                //cancelRegistration.setVisible(true);
                dispose();
            }
        });
        viewRegistrationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewRegistrationsWindow viewRegistrations = new ViewRegistrationsWindow();
                //viewRegistrations.setVisible(true);
                dispose();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    trainerRole.logout();
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
