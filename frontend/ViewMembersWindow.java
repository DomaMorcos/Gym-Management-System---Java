package frontend;

import backend.Member;
import backend.TrainerRole;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ViewMembersWindow extends JFrame {
    private JPanel ViewMembersP;
    private JTable membersTable;
    private JScrollPane tableScrollPane;
    private JButton backButton;
    private TrainerRole trainerRole;

    public ViewMembersWindow() {
        setVisible(true);
        setTitle("View Members");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(ViewMembersP);
        setLocationRelativeTo(null);

        try {
            trainerRole = new TrainerRole();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        createTable();
        loadMembers();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TrainerRoleWindow trainerRoleWindow = new TrainerRoleWindow();
                trainerRoleWindow.setVisible(true);
                dispose();
            }
        });
    }

    private void createTable() {
        String[] columnNames = {"Member ID", "Name", "Membership Type", "Email", "Phone Number", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        membersTable = new JTable(model);
        tableScrollPane = new JScrollPane(membersTable);
        ViewMembersP.add(tableScrollPane, BorderLayout.CENTER);
    }

    private void loadMembers() {
        DefaultTableModel model = (DefaultTableModel) membersTable.getModel();
        model.setRowCount(0); // Clear existing rows

        ArrayList<Member> members = trainerRole.getListOfMembers();
        for (Member member : members) {
            Object[] row = member.lineRepresentation().split(",");
            model.addRow(row);
        }
    }
}