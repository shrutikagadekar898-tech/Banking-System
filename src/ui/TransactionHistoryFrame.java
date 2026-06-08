package ui;

import util.DBConnection;
import javax.swing.table.JTableHeader;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TransactionHistoryFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public TransactionHistoryFrame(){

        setTitle("Transaction History");
        setSize(700,400);
        setLocationRelativeTo(null);
        JButton backBtn = new JButton("← Back");

        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> {
            dispose();
            new BankDashboardFrame();
        });
        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("Transaction ID");
        model.addColumn("Account ID");
        model.addColumn("Type");
        model.addColumn("Amount");
        model.addColumn("Date");

        add(new JScrollPane(table),BorderLayout.CENTER);
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
        header.setBackground(new Color(32,136,203));
        header.setForeground(Color.WHITE);
        loadTransactions();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private void loadTransactions(){

        String sql = "SELECT * FROM transactions";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){

                model.addRow(new Object[]{
                        rs.getInt("txn_id"),
                        rs.getInt("account_id"),
                        rs.getString("type"),
                        rs.getDouble("amount"),
                        rs.getTimestamp("txn_date")
                });

            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}