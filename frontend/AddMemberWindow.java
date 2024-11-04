package frontend;

import backend.TrainerRole;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddMemberWindow extends JFrame {
    private JPanel AddMemberP;
    private JLabel idLabel;
    private JTextField idField;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel membershipTypeLabel;
    private JTextField membershipTypeField;
    private JLabel phoneNumberLabel;
    private JTextField phoneNumberField;
    private JLabel statusLabel;
    private JTextField statusField;
    private JButton addButton;
    private TrainerRole trainerRole;

    public AddMemberWindow() {
        setVisible(true);
        setTitle("Add Member");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(AddMemberP);
        setLocationRelativeTo(null);

        try {
            trainerRole = new TrainerRole();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String membershipType = membershipTypeField.getText().trim();
                String phoneNumber = phoneNumberField.getText().trim();
                String status = statusField.getText().trim();

                if (id.isEmpty() || name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || membershipType.isEmpty() || status.isEmpty()) {
                    JOptionPane.showMessageDialog(AddMemberP, "Fields must be filled.");
                    return;
                }
                try {
                    trainerRole.addMember(id,name,membershipType,email,phoneNumber,status);
                    JOptionPane.showMessageDialog(AddMemberP, "The Member with Id = " + id + "has successfully added.");
                    TrainerRoleWindow trainerRole = new TrainerRoleWindow();
                    trainerRole.setVisible(true);
                    dispose();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}