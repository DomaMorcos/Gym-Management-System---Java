package frontend;

import backend.AdminRole;
import backend.Trainer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class RemoveTrainerWindow extends JFrame {
    private JPanel RemoveTrainerWindow;
    private JFormattedTextField idField;
    private JButton removeButton;
    private JButton backButton;
    private AdminRole adminRole;

    public RemoveTrainerWindow() {
        setVisible(true);
        setTitle("Remove Trainer");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(RemoveTrainerWindow);
        setLocationRelativeTo(null);

        try {
            this.adminRole = new AdminRole();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText().trim();
                if(id.isEmpty()){
                    JOptionPane.showMessageDialog(RemoveTrainerWindow,"Please fill the required fields!","Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    try {
                        int flag = 0;
                        for (Trainer trainer : adminRole.getListOfTrainers()) {
                            if (trainer.getSearchKey().equals(id)) {
                                flag = 1;
                                adminRole.removeTrainer(id);
                                JOptionPane.showMessageDialog(RemoveTrainerWindow, "Trainer with the ID = " + id + " has been deleted!");
                                AdminRoleWindow adminRoleWindow = new AdminRoleWindow();
                                adminRoleWindow.setVisible(true);
                                dispose();
                                break;
                            }
                        }
                        if(flag == 0) {
                            JOptionPane.showMessageDialog(RemoveTrainerWindow, "Trainer with the ID = " + id + " does not exist!","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminRoleWindow adminRoleWindow = new AdminRoleWindow();
                adminRoleWindow.setVisible(true);
                dispose();
            }
        });
    }
}
