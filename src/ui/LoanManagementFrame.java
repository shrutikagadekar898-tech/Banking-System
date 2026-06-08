package ui;

import dao.LoanDAO;
import model.Loan;
import model.LoanOption;
import javax.swing.table.JTableHeader;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;

public class LoanManagementFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public LoanManagementFrame(){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15,15));
        JButton backBtn = new JButton("← Back");

        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> {
            dispose();
            new BankDashboardFrame();
        });
        getContentPane().setBackground(new Color(245,247,250));
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(25,118,210));
        topPanel.setPreferredSize(new Dimension(100,70));

        JLabel title = new JLabel("Loan Management System");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(10,20,10,10));

        topPanel.add(title, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);
        setTitle("Loan Management");
        setSize(700,400);
        setLocationRelativeTo(null);

        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("Loan ID");
        model.addColumn("User ID");
        model.addColumn("Amount");
        model.addColumn("Rate");
        model.addColumn("Months");
        model.addColumn("EMI");
        model.addColumn("Status");

        JScrollPane pane = new JScrollPane(table);
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
        DefaultTableCellRenderer center =
                new DefaultTableCellRenderer();

        center.setHorizontalAlignment(JLabel.CENTER);

        for(int i=0;i<table.getColumnCount();i++){
            table.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(center);
        }
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
        header.setBackground(new Color(25,118,210));
        header.setForeground(Color.WHITE);
        JButton approve = new JButton("Approve Loan");
        JButton reject = new JButton("Reject Loan");
        JButton refresh = new JButton("Refresh");
        approve.setBackground(new Color(46,204,113));
        approve.setForeground(Color.WHITE);
        approve.setFont(new Font("Segoe UI", Font.BOLD, 14));
        approve.setFocusPainted(false);

        reject.setBackground(new Color(231,76,60));
        reject.setForeground(Color.WHITE);
        reject.setFont(new Font("Segoe UI", Font.BOLD, 14));
        reject.setFocusPainted(false);

        refresh.setBackground(new Color(52,152,219));
        refresh.setForeground(Color.WHITE);
        refresh.setFont(new Font("Segoe UI", Font.BOLD, 14));
        refresh.setFocusPainted(false);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,15));
        panel.setBackground(Color.WHITE);

        panel.add(approve);
        panel.add(reject);
        panel.add(refresh);

        add(pane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        loadLoans();

        approve.addActionListener(e->{

            int row = table.getSelectedRow();

            if(row==-1){
                JOptionPane.showMessageDialog(this,"Select Loan");
                return;
            }

            int loanId = (int)model.getValueAt(row,0);

            LoanDAO dao = new LoanDAO();

            if(dao.approveLoan(loanId)){
                JOptionPane.showMessageDialog(this,"Loan Approved");
                loadLoans();
            }

        });

        reject.addActionListener(e->{

            int row = table.getSelectedRow();

            if(row==-1){
                JOptionPane.showMessageDialog(this,"Select Loan");
                return;
            }

            int loanId = (int)model.getValueAt(row,0);

            LoanDAO dao = new LoanDAO();

            if(dao.rejectLoan(loanId)){
                JOptionPane.showMessageDialog(this,"Loan Rejected");
                loadLoans();
            }

        });

        refresh.addActionListener(e->loadLoans());

        setVisible(true);
    }

    private void loadLoans(){

        model.setRowCount(0);

        LoanDAO dao = new LoanDAO();

        List<Loan> list = dao.getAllLoans();

        for(Loan l:list){

            model.addRow(new Object[]{
                    l.getLoanId(),
                    l.getUserId(),
                    l.getAmount(),
                    l.getInterestRate(),
                    l.getTenureMonths(),
                    l.getEmi(),
                    l.getStatus()
            });

        }

    }
}