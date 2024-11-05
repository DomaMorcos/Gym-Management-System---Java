package frontend;

import backend.AdminRole;
import backend.Class;
import backend.Trainer;
import backend.TrainerRole;
import backend.Member;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class RegisterMemberForClassWindow extends JFrame {
    private JTextField memberIdField;
    private JTextField classIdField;
    private JPanel RegisterMemberForClassP;
    private JLabel memberIdLabel;
    private JLabel classIdLabel;
    private JLabel registrationDateLabel;
    private JButton registerButton;
    private TrainerRole trainerRole;


    public RegisterMemberForClassWindow() {
        setVisible(true);
        setTitle("Add Class");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(RegisterMemberForClassP);
        setLocationRelativeTo(null);

        try {
            this.trainerRole = new TrainerRole();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String memberID = memberIdField.getText().trim();
            String classID = classIdField.getText().trim();
            LocalDate date = LocalDate.now();

            if (classID.isEmpty() || memberID.isEmpty() ) {
                JOptionPane.showMessageDialog(RegisterMemberForClassP, "Fields must be filled.");
                return;
            } else {
                ArrayList<Class> classes = trainerRole.getListOfClasses();
                boolean classExists = false;
                boolean memberExists = false;
                for (Class c : classes) {
                    if(c.getSearchKey().equals(classID)) {
                        classExists = true;
                    }
                }
                ArrayList<Member> members = trainerRole.getListOfMembers();
                for (Member m : members)
                {
                    if(m.getSearchKey().equals(memberID)) {
                        memberExists = true;
                    }
                }
                if(!classExists) {
                    JOptionPane.showMessageDialog(RegisterMemberForClassP, "Class with ID = " + classID + " does not exist.");
                }
                if(!memberExists) {
                    JOptionPane.showMessageDialog(RegisterMemberForClassP, "Member with ID = " + memberID + " does not exist.");
                }
                else {
                    for (Class c : classes) {
                        if (c.getSearchKey().equals(classID)) {
                            if (c.getAvailableSeats() < 1)
                                JOptionPane.showMessageDialog(RegisterMemberForClassP, "This class has no available seats.");
                            else {
                                try {
                                    trainerRole.registerMemberForClass(memberID, classID, date);
                                    JOptionPane.showMessageDialog(RegisterMemberForClassP, "Member with ID = " + memberID + " successfully registered to class = " + classID);
                                    c.setAvailableSeats(c.getAvailableSeats() - 1);
                                    TrainerRoleWindow trainerRoleWindow = new TrainerRoleWindow();
                                    trainerRoleWindow.setVisible(true);
                                    dispose();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                    }
                }
            }

            }
        });
    }

}
