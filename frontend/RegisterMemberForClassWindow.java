package frontend;

import backend.Class;
import backend.Member;
import backend.MemberClassRegistration;
import backend.TrainerRole;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class RegisterMemberForClassWindow extends JFrame {
    private JTextField memberIdField;
    private JTextField classIdField;
    private JPanel RegisterMemberForClassP;
    private JLabel memberIdLabel;
    private JLabel classIdLabel;
    private JLabel registrationDateLabel;
    private JButton registerButton;
    private JPanel datePanel; // JPanel for the date picker
    private JButton backButton;
    private JDateChooser dateChooser; // JDateChooser instance
    private TrainerRole trainerRole;

    public RegisterMemberForClassWindow() {
        setTitle("Register Member For Class");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(RegisterMemberForClassP);
        setLocationRelativeTo(null);

        dateChooser = new JDateChooser();
        datePanel.setLayout(new BorderLayout());
        datePanel.add(dateChooser, BorderLayout.CENTER);

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
                Date selectedDate = dateChooser.getDate();
                LocalDate registrationDate = null;

                if (selectedDate != null) {
                    registrationDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                }

                if (classID.isEmpty() || memberID.isEmpty() || registrationDate == null) {
                    JOptionPane.showMessageDialog(RegisterMemberForClassP, "All fields must be filled, including the registration date.");
                    return;
                }

                ArrayList<Class> classes = trainerRole.getListOfClasses();
                boolean classExists = false;
                boolean memberExists = false;
                boolean registrationExists = false;

                for (Class c : classes) {
                    if (c.getSearchKey().equals(classID)) {
                        classExists = true;
                        break;
                    }
                }

                if (!classExists) {
                    JOptionPane.showMessageDialog(RegisterMemberForClassP, "Class with ID = " + classID + " does not exist.");
                    return;
                }

                ArrayList<Member> members = trainerRole.getListOfMembers();
                for (Member m : members) {
                    if (m.getSearchKey().equals(memberID)) {
                        memberExists = true;
                        break;
                    }
                }

                if (!memberExists) {
                    JOptionPane.showMessageDialog(RegisterMemberForClassP, "Member with ID = " + memberID + " does not exist.","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ArrayList<MemberClassRegistration> registrations = trainerRole.getListOfRegistrations();
                for (MemberClassRegistration r : registrations) {
                    if (r.getSearchKey().equals(memberID + classID)) {
                        registrationExists = true;
                        JOptionPane.showMessageDialog(RegisterMemberForClassP, "Member with ID = " + memberID + " is already registered for Class with ID = " + classID + ".","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }

                if (!registrationExists) {
                    for (Class c : classes) {
                        if (c.getSearchKey().equals(classID)) {
                            if (c.getAvailableSeats() < 1) {
                                JOptionPane.showMessageDialog(RegisterMemberForClassP, "This class has no available seats.","Error",JOptionPane.ERROR_MESSAGE);
                            } else {
                                try {
                                    System.out.println(c.getAvailableSeats());
                                    trainerRole.registerMemberForClass(memberID, classID, registrationDate);
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
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TrainerRoleWindow trainerRoleWindow = new TrainerRoleWindow();
                trainerRoleWindow.setVisible(true);
                dispose();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisterMemberForClassWindow::new);
    }
}
