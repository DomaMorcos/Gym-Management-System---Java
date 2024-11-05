package frontend;

import backend.Class;
import backend.MemberClassRegistration;
import backend.TrainerRole;
import backend.MemberClassRegistration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ViewRegistrationsWindow extends JFrame {
    private JPanel ViewRegistrationsP;
    private JScrollPane tableScrollPane;
    private JTable registrationsTable;
    private JButton backButton;
    private TrainerRole trainerRole;

    public ViewRegistrationsWindow() {
        ViewRegistrationsP = new JPanel(new BorderLayout());
        setContentPane(ViewRegistrationsP);
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
        ViewRegistrationsP.add(backButton, BorderLayout.SOUTH);

        setVisible(true);
    }
    private void createTable() {
        String[] columnNames = {"Member ID","Class ID","Registration Date"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        registrationsTable = new JTable(model);
        tableScrollPane = new JScrollPane(registrationsTable);
        ViewRegistrationsP.add(tableScrollPane, BorderLayout.CENTER);
    }

    private void loadMembers() {
        DefaultTableModel model = (DefaultTableModel) registrationsTable.getModel();
        model.setRowCount(0); // Clear existing rows

        ArrayList<MemberClassRegistration> registrations = trainerRole.getListOfRegistrations();
        for (MemberClassRegistration r : registrations) {
            Object[] row = r.lineRepresentation().split(",");
            model.addRow(row);
        }
    }
}
