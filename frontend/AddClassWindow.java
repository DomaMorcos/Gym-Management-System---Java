package frontend;

import backend.*;
import backend.Class;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddClassWindow extends JFrame {
    private JPanel AddClassP;
    private JTextField classIdField;
    private JTextField classNameField;
    private JLabel classIdLabel;
    private JLabel classNameLabel;
    private JTextField trainerIdField;
    private JLabel trainerIdLabel;
    private JTextField durationField;
    private JLabel durationLabel;
    private JTextField maxParticipantsField;
    private JLabel maxParticipantsLabel;
    private JButton addButton;
    private JButton backButton;
    private TrainerRole trainerRole;
    private AdminRole adminRole;

    public AddClassWindow() {

        setTitle("Add Class");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(AddClassP);
        setLocationRelativeTo(null);
        setVisible(true);

        try {
            this.trainerRole = new TrainerRole();
            this.adminRole = new AdminRole();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String classID = classIdField.getText().trim();
                String className = classNameField.getText().trim();
                String trainerID = trainerIdField.getText().trim();
                String duration = durationField.getText().trim();
                String maxParticipants = maxParticipantsField.getText().trim();

                if (classID.isEmpty() || className.isEmpty() || trainerID.isEmpty() || duration.isEmpty() || maxParticipants.isEmpty()) {
                    JOptionPane.showMessageDialog(AddClassP, "Fields must be filled." ,"Error",JOptionPane.ERROR_MESSAGE);
                    return;
                } else {

                    boolean classExists = false;
                    boolean trainerExists = false;
                    for (Class classVar : trainerRole.getListOfClasses()) {
                        if (classVar.getSearchKey().equals(classID)) {
                            JOptionPane.showMessageDialog(AddClassP, "Class with the ID = " + classID + " already exists!","Error",JOptionPane.ERROR_MESSAGE);
                            classExists = true;
                            break;
                        }
                    }
                    for (Trainer trainer : adminRole.getListOfTrainers()){
                        if(trainer.getSearchKey().equals(trainerID)){
                            trainerExists = true;
                        }
                    }
                    if(!trainerExists){
                        JOptionPane.showMessageDialog(AddClassP, "Trainer with the ID = " + trainerID + " does not exist!","Error",JOptionPane.ERROR_MESSAGE);

                    }
                    if (!classExists && trainerExists) {
                        try {
                            int durationINT = Integer.parseInt(duration);
                            int maxParticipantsINT = Integer.parseInt(maxParticipants);
                            trainerRole.addClass(classID, className, trainerID, durationINT, maxParticipantsINT);
                            JOptionPane.showMessageDialog(AddClassP, "The Class with ID: " + classID + " has been added successfully!");
                            TrainerRoleWindow trainerRoleWindow = new TrainerRoleWindow();
                            trainerRoleWindow.setVisible(true);
                            dispose();
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
