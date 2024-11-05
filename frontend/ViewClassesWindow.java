package frontend;

import backend.Member;
import backend.TrainerRole;
import backend.Class;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ViewClassesWindow extends JFrame {
    private JPanel ViewClassesP;
    private JTable classesTable;
    private JButton backButton;
    private JScrollPane tableScrollPane;
    private TrainerRole trainerRole;

    public ViewClassesWindow() {
        ViewClassesP = new JPanel(new BorderLayout());
        setContentPane(ViewClassesP);
        setTitle("View Classes");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            this.trainerRole = new TrainerRole();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        createTable();
        loadMembers();

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TrainerRoleWindow trainerRoleWindow = new TrainerRoleWindow();
                trainerRoleWindow.setVisible(true);
                dispose();
            }
        });
        ViewClassesP.add(backButton, BorderLayout.SOUTH);

        // Set the window to visible at the end
        setVisible(true);
    }

    private void createTable() {
        String[] columnNames = {"Class ID", "Class Name", "Trainer ID" , "Duration", "Max Participants"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        classesTable = new JTable(model);
        tableScrollPane = new JScrollPane(classesTable);
        ViewClassesP.add(tableScrollPane, BorderLayout.CENTER);
    }

    private void loadMembers() {
        DefaultTableModel model = (DefaultTableModel) classesTable.getModel();
        model.setRowCount(0); // Clear existing rows

        ArrayList<Class> classes = trainerRole.getListOfClasses();
        for (Class classVar : classes) {
            Object[] row = classVar.lineRepresentation().split(",");
            model.addRow(row);
        }
    }

}
