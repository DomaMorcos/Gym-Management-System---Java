package frontend;

import backend.AdminRole;
import backend.Trainer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ViewTrainersWindow extends JFrame {
    private JPanel viewTrainersPanel;
    private JTable trainersTable;
    private JScrollPane tableScrollPane;
    private JButton backButton;
    private AdminRole adminRole;

    public ViewTrainersWindow() {
        // Initialize the panel and layout first
        viewTrainersPanel = new JPanel(new BorderLayout());
        setContentPane(viewTrainersPanel);
        setTitle("View Members");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize the AdminRole
        try {
            this.adminRole = new AdminRole();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Create and add table
        createTable();
        loadTrainers();

        // Initialize backButton and add to the panel
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminRoleWindow adminRoleWindow = new AdminRoleWindow();
                adminRoleWindow.setVisible(true);
                dispose();
            }
        });
        viewTrainersPanel.add(backButton, BorderLayout.SOUTH);

        // Set the window to visible at the end
        setVisible(true);
    }

    public void createTable() {
        String[] columns = {"ID", "Name", "Email", "Speciality", "Phone Number"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        trainersTable = new JTable(model);
        tableScrollPane = new JScrollPane(trainersTable);
        viewTrainersPanel.add(tableScrollPane, BorderLayout.CENTER);
    }

    private void loadTrainers() {
        DefaultTableModel model = (DefaultTableModel) trainersTable.getModel();
        model.setRowCount(0); // Clear existing rows

        ArrayList<Trainer> trainers = adminRole.getListOfTrainers();
        for (Trainer trainer : trainers) {
            Object[] row = trainer.lineRepresentation().split(",");
            model.addRow(row);
        }
    }
}
