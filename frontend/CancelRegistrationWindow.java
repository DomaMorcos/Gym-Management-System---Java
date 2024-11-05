package frontend;

import backend.AdminRole;
import backend.TrainerRole;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CancelRegistrationWindow extends JFrame {
    private JPanel CancelRegistrationP;
    private JTextField memberIdField;
    private JTextField classIdField;
    private JButton cancelRegistrationButton;
    private JLabel memberIdLabel;
    private JLabel classIdLabel;
    private TrainerRole trainerRole;

    public CancelRegistrationWindow() {
        setVisible(true);
        setTitle("Remove Trainer");
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
                    JOptionPane.showMessageDialog(CancelRegistrationP, "Fields must be filled.");
                    return;

                } else {
                    if(trainerRole.getListOfClasses().contains(classId) && trainerRole.getListOfMembers().contains(memberId)) {
                        try {
                            trainerRole.cancelRegistration(memberId,classId);
                            JOptionPane.showMessageDialog(CancelRegistrationP, "The Member with ID = " + memberId + "has been unregistere from class = " + classId + ".");
                            TrainerRoleWindow trainerRoleWindow = new TrainerRoleWindow();
                            trainerRoleWindow.setVisible(true);
                            dispose();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else{
                            if(!trainerRole.getListOfClasses().contains(classId)) {
                                JOptionPane.showMessageDialog(CancelRegistrationP,"Class with ID = " + classId + "doesn't exist");
                            }
                            if(!trainerRole.getListOfMembers().contains(memberId)) {
                                JOptionPane.showMessageDialog(CancelRegistrationP,"Member with ID = " + memberId + "doesn't exist");
                            }
                    }
                }
                }

            });

    }
}
