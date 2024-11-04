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
    private AdminRole adminRole;

    public RemoveTrainerWindow() {
        setVisible(true);
        setTitle("Trainer Role");
        setSize(500, 300);
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
                        JOptionPane.showMessageDialog(RemoveTrainerWindow, "Trainer with the ID = " + id + " does not exist!");
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
