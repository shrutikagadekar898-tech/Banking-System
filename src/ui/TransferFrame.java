package ui;

import javax.swing.*;
import java.awt.*;

public class TransferFrame extends JFrame {

    JTextField fromField, toField, amountField;
    JButton transferBtn, backBtn;

    public TransferFrame() {

        setTitle("Transfer Money");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ===== MAIN PANEL =====
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 248, 252));

        // ===== TITLE =====
        JLabel title = new JLabel("Transfer Money");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBounds(350, 40, 300, 40);
        panel.add(title);

        // ===== LABELS =====
        JLabel fromLbl = new JLabel("From Account");
        fromLbl.setBounds(200, 140, 150, 30);
        panel.add(fromLbl);

        JLabel toLbl = new JLabel("To Account");
        toLbl.setBounds(200, 200, 150, 30);
        panel.add(toLbl);

        JLabel amtLbl = new JLabel("Amount");
        amtLbl.setBounds(200, 260, 150, 30);
        panel.add(amtLbl);

        // ===== FIELDS =====
        fromField = new JTextField();
        fromField.setBounds(350, 140, 250, 35);
        panel.add(fromField);

        toField = new JTextField();
        toField.setBounds(350, 200, 250, 35);
        panel.add(toField);

        amountField = new JTextField();
        amountField.setBounds(350, 260, 250, 35);
        panel.add(amountField);

        // ===== BUTTONS =====
        transferBtn = new JButton("Transfer");
        transferBtn.setBounds(320, 340, 150, 45);
        transferBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        transferBtn.setBackground(new Color(41, 128, 185));
        transferBtn.setForeground(Color.WHITE);
        panel.add(transferBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(490, 340, 150, 45);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        backBtn.setBackground(new Color(231, 76, 60));
        backBtn.setForeground(Color.WHITE);
        panel.add(backBtn);

        // ===== ACTIONS =====
        backBtn.addActionListener(e -> dispose());

        transferBtn.addActionListener(e -> handleTransfer());

        add(panel);
        setVisible(true);
    }

    // ===== TRANSFER LOGIC =====
    private void handleTransfer() {

        String from = fromField.getText().trim();
        String to = toField.getText().trim();
        String amountText = amountField.getText().trim();

        // ===== VALIDATION =====
        if (from.isEmpty() || to.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        double amount;

        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount!");
            return;
        }

        if (amount <= 0) {
            JOptionPane.showMessageDialog(this, "Amount must be greater than 0!");
            return;
        }

        // ===== CALL TRANSFER =====
        boolean success = performTransfer(from, to, amount);

        if (success) {
            JOptionPane.showMessageDialog(this, "Transfer Successful!");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Transfer Failed (Check balance / account)");
        }
    }

    // ===== DUMMY LOGIC (replace with DB later) =====
    private boolean performTransfer(String from, String to, double amount) {

        // 🔥 For now: simple fake logic
        // Replace this with PostgreSQL update queries later

        if (from.equals(to)) {
            return false;
        }

        // assume transfer success if amount < 100000
        return amount <= 100000;
    }

    private void clearFields() {
        fromField.setText("");
        toField.setText("");
        amountField.setText("");
    }
}