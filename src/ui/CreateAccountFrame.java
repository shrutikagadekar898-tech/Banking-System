package ui;

import dao.AccountDAO;
import model.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CreateAccountFrame extends JFrame {

    JTextField userIdField, bankIdField, balanceField;
    JButton createBtn, refreshBtn;
    JTable table;
    DefaultTableModel model;
    String username;
    public CreateAccountFrame() {

        setTitle("Create Account");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JButton backBtn = new JButton("← Back");

        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> {
            dispose();
            new CustomerDashboardFrame(username);
        });
        // ===== Top Panel =====

        JPanel topPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        topPanel.setBorder(BorderFactory.createTitledBorder("Create New Account"));

        userIdField = new JTextField();
        bankIdField = new JTextField();
        balanceField = new JTextField();

        createBtn = new JButton("Create Account");
        refreshBtn = new JButton("Refresh");

        topPanel.add(new JLabel("User ID"));
        topPanel.add(userIdField);

        topPanel.add(new JLabel("Bank ID"));
        topPanel.add(bankIdField);

        topPanel.add(new JLabel("Balance"));
        topPanel.add(balanceField);

        topPanel.add(createBtn);
        topPanel.add(refreshBtn);

        add(topPanel, BorderLayout.NORTH);

        // ===== Table =====

        model = new DefaultTableModel();

        model.addColumn("Account ID");
        model.addColumn("User ID");
        model.addColumn("Bank ID");
        model.addColumn("Balance");

        table = new JTable(model);
        table.setRowHeight(25);
        table.getColumnModel().getColumn(0).setPreferredWidth(100); // User ID
        table.getColumnModel().getColumn(1).setPreferredWidth(250); // Username
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Role
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Status

        table.setRowHeight(35);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        setSize(1200,700);
        setLocationRelativeTo(null);
        JScrollPane sp = new JScrollPane(table);

        add(sp, BorderLayout.CENTER);

        // ===== Load Data =====
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        loadAccounts();

        // ===== Create Button =====

        createBtn.addActionListener(e -> {

            try {

                int userId = Integer.parseInt(userIdField.getText());
                int bankId = Integer.parseInt(bankIdField.getText());
                double balance = Double.parseDouble(balanceField.getText());

                AccountDAO dao = new AccountDAO();

                boolean ok = dao.createAccount(userId, bankId, balance);

                if (ok) {

                    JOptionPane.showMessageDialog(this, "Account Created Successfully");

                    loadAccounts();

                    userIdField.setText("");
                    bankIdField.setText("");
                    balanceField.setText("");

                } else {

                    JOptionPane.showMessageDialog(this, "Error creating account");

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, "Invalid input");

            }

        });
       /* private void loadAccounts () {
            model.setRowCount(0);
            AccountDAO dao = new AccountDAO();
            List<Object[]> list = dao.getAllAccounts();
            for (Object[] row : list) {
                model.addRow(row);
            }
        }
        }*/
        // ===== Refresh =====

        refreshBtn.addActionListener(e -> loadAccounts());

    }
    private void loadAccounts () {
        model.setRowCount(0);
        AccountDAO dao = new AccountDAO();
        List<Account> list = dao.getAllAccounts();
        for (Account a: list) {
            model.addRow(new Object[]{
                    a.getAccountId(),
                    a.getUserId(),
                    a.getBankId(),
                    a.getBalance()
            } );
        }
        setVisible(true);
    }
}