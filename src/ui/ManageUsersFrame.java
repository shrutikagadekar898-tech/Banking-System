package ui;

import dao.UserDAO;
import model.User;
import javax.swing.table.JTableHeader;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManageUsersFrame extends JFrame {

    JTable table;
    DefaultTableModel model;
    String username;
    JTextField usernameField;
    JTextField passwordField;
    JTextField roleField;
    JTextField statusField;

    UserDAO userDAO = new UserDAO();

    public ManageUsersFrame(){

        setTitle("Manage Users");
        setSize(800,500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JButton backBtn = new JButton("← Back");

        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> {
            dispose();
            new AdminDashboardFrame(username);
        });
        // TABLE
        model = new DefaultTableModel();
        model.addColumn("User ID");
        model.addColumn("Username");
        model.addColumn("Role");
        model.addColumn("Status");

        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI",Font.PLAIN,14));
        table.getColumnModel().getColumn(0).setPreferredWidth(100); // User ID
        table.getColumnModel().getColumn(1).setPreferredWidth(250); // Username
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Role
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Status

        table.setRowHeight(35);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        setSize(1200,700);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI",Font.BOLD,14));
        header.setBackground(new Color(25,118,210));
        header.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane,BorderLayout.CENTER);

        // FORM PANEL
        JPanel formPanel = new JPanel(new GridLayout(2,4,10,10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        usernameField = new JTextField();
        passwordField = new JTextField();
        roleField = new JTextField();
        statusField = new JTextField();

        formPanel.add(new JLabel("Username"));
        formPanel.add(new JLabel("Role"));
        formPanel.add(new JLabel("Status"));
        formPanel.add(new JLabel(""));

        formPanel.add(usernameField);
        formPanel.add(roleField);
        formPanel.add(statusField);
        formPanel.add(new JLabel(""));

        // BUTTON PANEL
        JButton addBtn = new JButton("Add User");
        JButton updateBtn = new JButton("Update User");
        JButton deleteBtn = new JButton("Delete User");
        JButton refreshBtn = new JButton("Refresh");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(refreshBtn);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(formPanel,BorderLayout.CENTER);
        bottomPanel.add(buttonPanel,BorderLayout.SOUTH);

        add(bottomPanel,BorderLayout.SOUTH);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // LOAD DATA
        loadUsers();

        // TABLE SELECT
        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if(row != -1){

                usernameField.setText(model.getValueAt(row,1).toString());
                roleField.setText(model.getValueAt(row,2).toString());
                statusField.setText(model.getValueAt(row,3).toString());

            }

        });

        // ADD USER
        addBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = roleField.getText();
            String status = statusField.getText();
            boolean result = userDAO.addUser(username,password,role,status);
            if(result) {
                JOptionPane.showMessageDialog(this, "User Added Successfully");
            loadUsers();
            }else {
                JOptionPane.showMessageDialog(this, "Error adding user or username already exists");
            }
        });

        // UPDATE USER
        updateBtn.addActionListener(e -> {

                    int row = table.getSelectedRow();

                    if (row == -1){

                    //int id = Integer.parseInt(model.getValueAt(row,0).toString());
                    JOptionPane.showMessageDialog(this, "Selecte user first");
                    return;
                }
                int userId = Integer.parseInt(model.getValueAt(row,0).toString());
                    String username = usernameField.getText();
                    String role = roleField.getText();
                    String status = statusField.getText();
                    UserDAO dao = new UserDAO();
                    dao.updateUser(userId,username,role,status);

            loadUsers();

        });

        // DELETE USER
        deleteBtn.addActionListener(e -> {

            int row = table.getSelectedRow();

            if(row == -1) return;

            int id = Integer.parseInt(model.getValueAt(row,0).toString());

            userDAO.deleteUser(id);

            loadUsers();

        });
setVisible(true);
        // REFRESH
        refreshBtn.addActionListener(e -> loadUsers());

    }

    private void loadUsers(){

        model.setRowCount(0);

        List<User> users = userDAO.getAllUsers();

        for(User u : users){

            model.addRow(new Object[]{
                    u.getUserId(),
                    u.getUsername(),
                    u.getRole(),
                    u.getStatus()
            });

        }

    }

}