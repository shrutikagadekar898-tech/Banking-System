package ui;

import dao.AccountDAO;
import model.Account;
import javax.swing.table.JTableHeader;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewAccountsFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ViewAccountsFrame() {

        setTitle("View Accounts");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JButton backBtn = new JButton("← Back");

        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> {
            dispose();
            new BankDashboardFrame();
        });
        model = new DefaultTableModel();
        model.setColumnIdentifiers(
                new String[]{"Account ID","Username","Bank Name","Balance"});

        table = new JTable(model);
        add(new JScrollPane(table));
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setSelectionBackground(new Color(184, 207, 229));
        table.setGridColor(Color.LIGHT_GRAY);
        table.getColumnModel().getColumn(0).setPreferredWidth(100); // User ID
        table.getColumnModel().getColumn(1).setPreferredWidth(250); // Username
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Role
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Status

        table.setRowHeight(35);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        setSize(1200,700);

        setLocationRelativeTo(null);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
        header.setBackground(new Color(25,118,210));
        header.setForeground(Color.WHITE);
        loadAccounts();
setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private void loadAccounts() {

        AccountDAO dao = new AccountDAO();
        List<Account> list = dao.getAllAccountsWithDetails();

        model.setRowCount(0);

        for(Account a : list) {
            model.addRow(new Object[]{
                    a.getAccountId(),
                    a.getUsername(),
                    a.getBankName(),
                    a.getBalance()
            });
        }
    }
}
