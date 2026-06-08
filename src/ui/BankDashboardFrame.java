package ui;

import javax.swing.*;
import java.awt.*;

public class BankDashboardFrame extends JFrame {

    JPanel contentPanel;

    public BankDashboardFrame(){

        setTitle("Bank Dashboard");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(8,1,10,10));
        sidebar.setBackground(new Color(0,51,102));
        sidebar.setPreferredSize(new Dimension(200,600));

        JButton manageBanks = createButton("Manage Banks");
        JButton viewAccounts = createButton("View Accounts");
        JButton loanRequests = createButton("Loan Requests");
        JButton transactions = createButton("Transactions");
        JButton logout = createButton("Logout");

        sidebar.add(new JLabel("  BANK MENU"));
        sidebar.add(manageBanks);
        sidebar.add(viewAccounts);
        sidebar.add(loanRequests);
        sidebar.add(transactions);
        sidebar.add(logout);

        add(sidebar,BorderLayout.WEST);

        // Content Panel
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        JLabel welcome = new JLabel("Welcome Bank Dashboard",JLabel.CENTER);
        welcome.setFont(new Font("Segoe UI",Font.BOLD,28));

        contentPanel.add(welcome,BorderLayout.CENTER);

        add(contentPanel,BorderLayout.CENTER);

        // Button actions
        manageBanks.addActionListener(e -> {
            new ManageBanksFrame().setVisible(true);
        });

        viewAccounts.addActionListener(e -> {
            new ViewAccountsFrame().setVisible(true);
        });

        loanRequests.addActionListener(e -> {
            new LoanManagementFrame().setVisible(true);
        });

        transactions.addActionListener(e -> {
            new TransactionHistoryFrame().setVisible(true);
        });

        logout.addActionListener(e -> {
            dispose();
            new MainFrame().setVisible(true);
        });
        setExtendedState(JFrame.MAXIMIZED_BOTH);
setVisible(true);
    }

    // Stylish button
    private JButton createButton(String text){

        JButton btn = new JButton(text);

        btn.setFont(new Font("Segoe UI",Font.BOLD,15));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(0,102,204));
        btn.setFocusPainted(false);

        btn.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(30,144,255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(0,102,204));
            }

        });
        return btn;
    }
}