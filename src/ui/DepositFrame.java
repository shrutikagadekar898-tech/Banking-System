package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import dao.AccountDAO;
import model.Account;
import java.util.List;
import javax.swing.table.JTableHeader;

public class DepositFrame extends JFrame {

    JTextField accountIdField, amountField;
    JTable table;
    DefaultTableModel model;
    String username;
    public DepositFrame(){

        setTitle("Deposit Money");
        setSize(600,400);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        JButton backBtn = new JButton("← Back");

        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> {
            dispose();
            new CustomerDashboardFrame(username);
        });
        // FORM PANEL
        JPanel form = new JPanel(new GridLayout(3,2));

        accountIdField = new JTextField();
        amountField = new JTextField();

        form.add(new JLabel("Account ID"));
        form.add(accountIdField);

        form.add(new JLabel("Amount"));
        form.add(amountField);

        JButton depositBtn = new JButton("Deposit");

        form.add(depositBtn);

        add(form,BorderLayout.NORTH);

        // TABLE
        model = new DefaultTableModel();

        model.addColumn("Account ID");
        model.addColumn("User ID");
        model.addColumn("Bank ID");
        model.addColumn("Balance");

        table = new JTable(model);

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
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        loadAccounts();

        // Deposit button action
        depositBtn.addActionListener(e -> {

            int accId = Integer.parseInt(accountIdField.getText());
            double amount = Double.parseDouble(amountField.getText());

            AccountDAO dao = new AccountDAO();

            boolean success = dao.deposit(accId, amount);

            if(success){
                JOptionPane.showMessageDialog(this,"Deposit Successful");
                loadAccounts();
            }else{
                JOptionPane.showMessageDialog(this,"Deposit Failed");
            }

        });

        setVisible(true);
    }

    private void loadAccounts(){

        model.setRowCount(0);

        AccountDAO dao = new AccountDAO();

        List<Account> list = dao.getAllAccounts();

        for(Account a : list){

            model.addRow(new Object[]{
                    a.getAccountId(),
                    a.getUserId(),
                    a.getBankId(),
                    a.getBalance()
            });
        }
    }
}