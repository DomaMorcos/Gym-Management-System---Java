package frontend;

import backend.AdminRole;
import backend.Trainer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddTrainerWindow extends JFrame {
    private JPanel AddTrainerWindow;
    private JTextField idField;
    private JButton addButton;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField specialityField;
    private JTextField phoneNumberField;
    private AdminRole adminRole;

    public AddTrainerWindow() {

        setTitle("Add Trainer");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(AddTrainerWindow);
        setLocationRelativeTo(null);
        setVisible(true);

        try {
            this.adminRole = new AdminRole();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String speciality = specialityField.getText().trim();
                String phoneNumber = phoneNumberField.getText().trim();

                if(id.isEmpty() || name.isEmpty() || email.isEmpty() || speciality.isEmpty() || phoneNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(AddTrainerWindow, "Please fill all the required fields!");

                }
                else {
                    boolean trainerExists = false;
                    for (Trainer trainer : adminRole.getListOfTrainers()) {
                        if (trainer.getSearchKey().equals(id)) {
                            JOptionPane.showMessageDialog(AddTrainerWindow, "Trainer with the ID = " + id + " already exists!");
                            trainerExists = true;
                            break;
                        }
                    }
                    if (!trainerExists) {
                        try {
                            adminRole.addTrainer(id, name, email, speciality, phoneNumber);
                            JOptionPane.showMessageDialog(AddTrainerWindow, "The Member with ID: " + id + " has been added successfully!");
                            AdminRoleWindow adminRoleWindow = new AdminRoleWindow();
                            adminRoleWindow.setVisible(true);
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
