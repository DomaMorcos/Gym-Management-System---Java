package frontend;

import backend.*;
import backend.Class;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class CancelRegistrationWindow extends JFrame {
    private JPanel CancelRegistrationP;
    private JTextField memberIdField;
    private JTextField classIdField;
    private JButton cancelRegistrationButton;
    private JLabel memberIdLabel;
    private JLabel classIdLabel;
    private JButton backButton;
    private TrainerRole trainerRole;

    public CancelRegistrationWindow() {
        setVisible(true);
        setTitle("Cancel Registration");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(CancelRegistrationP);
        setLocationRelativeTo(null);

        try {
            this.trainerRole = new TrainerRole();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        cancelRegistrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String memberId = memberIdField.getText();
                String classId = classIdField.getText();
                if (classId.isEmpty() || memberId.isEmpty()) {
                    JOptionPane.showMessageDialog(CancelRegistrationP, "Fields must be filled.","Error",JOptionPane.ERROR_MESSAGE);
                    return;

                } else {
                    boolean registrationExists = false;
                    boolean memberExists = false;
                    boolean classExists = false;
                    ArrayList <Member> members = trainerRole.getListOfMembers();
                    ArrayList <MemberClassRegistration> registrations  = trainerRole.getListOfRegistrations();
                    ArrayList <Class> classes = trainerRole.getListOfClasses();
                    MemberClassRegistration registration = null;

                    for(Member member: members) {
                        if(member.getSearchKey().equals(memberId)) {
                            memberExists = true;
                        }
                    }
                    for(Class c: classes) {
                        if(c.getSearchKey().equals(classId)) {
                            classExists = true;
                        }
                    }
                    for(MemberClassRegistration mc: registrations) {
                        if(mc.getSearchKey().equals(memberId + classId)) {
                            registrationExists = true;
                            registration = mc;
                        }
                    }
                    if(!memberExists) {
                        JOptionPane.showMessageDialog(CancelRegistrationP, "Member with the ID = " + memberId + " does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if(!classExists) {
                        JOptionPane.showMessageDialog(CancelRegistrationP, "Class with the ID = " + classId + " does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if(memberExists && classExists && !registrationExists) {
                        JOptionPane.showMessageDialog(CancelRegistrationP, "Member with the ID = " + memberId + " is not registered to the class with the ID = " + classId, "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    if(registrationExists) {
                        try {
                            LocalDate dateRightNow = LocalDate.now();

                            long daysBetween = ChronoUnit.DAYS.between(registration.getRegistrationDate(), dateRightNow);
                            if ((  dateRightNow.isEqual(registration.getRegistrationDate())) || (dateRightNow.isAfter(registration.getRegistrationDate()) && daysBetween < 3) || dateRightNow.isBefore(registration.getRegistrationDate())) {
                            trainerRole.cancelRegistration(memberId,classId);
                            JOptionPane.showMessageDialog(CancelRegistrationP, "The Member with ID = " + memberId + " has been unregistered from class = " + classId + ".");
                            TrainerRoleWindow trainerRoleWindow = new TrainerRoleWindow();
                            trainerRoleWindow.setVisible(true);
                            dispose();}
                            else {
                                JOptionPane.showMessageDialog(CancelRegistrationP , "Cannot cancel registration because 3 or more days has passed .", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                }

            });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TrainerRoleWindow trainerRoleWindow = new TrainerRoleWindow();
                trainerRoleWindow.setVisible(true);
                dispose();
            }
        });

    }
}
