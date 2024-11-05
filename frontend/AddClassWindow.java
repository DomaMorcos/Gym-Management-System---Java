package frontend;

import backend.Member;
import backend.TrainerRole;
import backend.Class;

import javax.swing.*;
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
    private TrainerRole trainerRole;

    public AddClassWindow() {
        setVisible(true);
        setTitle("Add Class");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(AddClassP);
        setLocationRelativeTo(null);

        try {
            this.trainerRole = new TrainerRole();
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
                    JOptionPane.showMessageDialog(AddClassP, "Fields must be filled.");
                    return;
                } else {

                    boolean classExists = false;
                    for (Class classVar : trainerRole.getListOfClasses()) {
                        if (classVar.getSearchKey().equals(classID)) {
                            JOptionPane.showMessageDialog(AddClassP, "Class with the ID = " + classID + " already exists!");
                            classExists = true;
                            break;
                        }
                    }
                    if (!classExists) {
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
    }
}
