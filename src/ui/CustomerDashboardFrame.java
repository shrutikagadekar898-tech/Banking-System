package ui;

import dao.UserDAO;

import javax.swing.*;
import java.awt.*;

public class CustomerDashboardFrame extends JFrame {

    String username;
    int userId;

    public CustomerDashboardFrame(String username) {

        this.username = username;

        UserDAO dao = new UserDAO();
        userId = dao.getUserIdByUsername(username);

        setTitle("Customer Dashboard - " + username);
        setSize(700,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== TITLE =====
        JLabel title = new JLabel("Welcome, " + username + " 👋", JLabel.CENTER);
        title.setFont(new Font("Cambria", Font.BOLD, 32));
        title.setBorder(BorderFactory.createEmptyBorder(30,0,30,0));
        add(title, BorderLayout.NORTH);
setExtendedState(JFrame.MAXIMIZED_BOTH);
Color bg = new Color(245,247,250);
        // ===== CENTER PANEL =====
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,3,30,30));
        panel.setBorder(BorderFactory.createEmptyBorder(50,150,50,150));
        panel.setBackground(bg);
add(panel,BorderLayout.CENTER);
        // ===== BUTTONS =====
        JButton create = createCardButton("Create Account");
        JButton deposit = createCardButton("Deposit Money");
        JButton transfer = createCardButton("Transfer Money");
        JButton loan = createCardButton("Apply Loan");
        JButton history = createCardButton("Transaction History");
        JButton logout = createCardButton("Logout");

        panel.add(create);
        panel.add(deposit);
        panel.add(transfer);
        panel.add(loan);
        panel.add(history);
        panel.add(logout);

        add(panel, BorderLayout.CENTER);

        // ===== BUTTON ACTIONS =====
        create.addActionListener(e -> new CreateAccountFrame().setVisible(true));

        deposit.addActionListener(e -> new DepositFrame().setVisible(true));

        transfer.addActionListener(e -> new TransferFrame().setVisible(true));

        loan.addActionListener(e -> new CustomerLoanFrame(userId).setVisible(true));

        history.addActionListener(e -> new TransactionHistoryFrame().setVisible(true));

        logout.addActionListener(e -> {
            dispose();
            new MainFrame().setVisible(true);
        });

        setVisible(true);
    }

    // ===== MODERN BUTTON STYLE =====
    private JButton createCardButton(String text){

        JButton btn = new JButton(text);

        btn.setFont(new Font("Cambria", Font.BOLD, 18));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(25,118,210));
        btn.setForeground(Color.WHITE);
        btn.setPreferredSize(new Dimension(200,60));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createLineBorder(new Color(30,100,160),2));

        return btn;
    }
}