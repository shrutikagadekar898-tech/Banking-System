package ui;

import javax.swing.*;
import java.awt.*;

public class AdminDashboardFrame extends JFrame {

    private String username;

    public AdminDashboardFrame(String username) {

        this.username = username;

        setTitle("Bank Management System - Admin Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();

        setVisible(true);
    }

    private void initUI() {

        setLayout(new BorderLayout());

        // Top Dashboard Cards
        add(new DashboardPanel(), BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 25, 25));
        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(40, 80, 40, 80));
        centerPanel.setBackground(new Color(240, 245, 250));

        JButton manageUsersBtn =
                createButton("Manage Users", new Color(33,150,243));

        JButton manageBanksBtn =
                createButton("Manage Banks", new Color(76,175,80));

        JButton viewAccountsBtn =
                createButton("View Accounts", new Color(255,152,0));

        JButton viewLoansBtn =
                createButton("View Loans", new Color(156,39,176));

        JButton logoutBtn =
                createButton("Logout", new Color(244,67,54));
        logoutBtn.setPreferredSize(new Dimension(180,50));
        centerPanel.add(manageUsersBtn);
        centerPanel.add(manageBanksBtn);
        centerPanel.add(viewAccountsBtn);
        centerPanel.add(viewLoansBtn);

        JPanel logoutPanel = new JPanel(new BorderLayout());
        logoutPanel.setBackground(new Color(240,245,250));
        logoutPanel.add(logoutBtn, BorderLayout.CENTER);
        //logoutBtn.setPreferredSize(180,50);
        add(centerPanel, BorderLayout.CENTER);
        add(logoutPanel, BorderLayout.SOUTH);

        // Events
        manageUsersBtn.addActionListener(
                e -> new ManageUsersFrame());

        manageBanksBtn.addActionListener(
                e -> new ManageBanksFrame());

        viewAccountsBtn.addActionListener(
                e -> new ViewAccountsFrame());

        viewLoansBtn.addActionListener(
                e -> new LoanViewFrame());
        //logoutBtn.setPreferredSize(180,50);
        logoutBtn.addActionListener(e -> {
            dispose();
            new MainFrame();
        });
    }

    private JButton createButton(String text, Color color) {

        JButton btn = new JButton(text);

        btn.setFont(
                new Font("Segoe UI", Font.BOLD, 20));

        btn.setBackground(color);
        btn.setForeground(Color.WHITE);

        btn.setFocusPainted(false);

        btn.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        btn.setPreferredSize(
                new Dimension(250,170));
        return btn;
    }
}