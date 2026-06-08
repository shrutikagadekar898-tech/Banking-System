package ui;

import dao.LoanDAO;
import model.Loan;
import model.LoanOption;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class CustomerLoanFrame extends JFrame {

    private int userId;

    JTable optionTable;
    JTable myLoanTable;
private String username;
    DefaultTableModel optionModel;
    DefaultTableModel loanModel;

    public CustomerLoanFrame(int userId) {

        this.userId = userId;

        setTitle("Loan Application");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JButton backBtn = new JButton("← Back");

        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> {
            dispose();
            new CustomerDashboardFrame(username);
        });
        // -------- TITLE --------
        JLabel title = new JLabel("Apply for Loan", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        // -------- LOAN OPTIONS TABLE --------
        optionModel = new DefaultTableModel();
        optionModel.addColumn("Option ID");
        optionModel.addColumn("Amount");
        optionModel.addColumn("Interest %");
        optionModel.addColumn("Tenure");
        optionModel.addColumn("EMI");
       // optionModel.addColumn("Status");

        optionTable = new JTable(optionModel);

        optionTable.setRowHeight(28);
        optionTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane optionScroll = new JScrollPane(optionTable);

        // -------- APPLY BUTTON --------
        JButton applyBtn = new JButton("Apply Loan");

        applyBtn.setBackground(new Color(0, 102, 204));
        applyBtn.setForeground(Color.WHITE);
        applyBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JPanel applyPanel = new JPanel();
        applyPanel.add(applyBtn);

        // -------- MY LOANS TABLE --------
        loanModel = new DefaultTableModel();

        loanModel.addColumn("Loan ID");
      //  loanModel.addColumn("Loan name");
        loanModel.addColumn("Amount");
        loanModel.addColumn("Interest");
        loanModel.addColumn("Tenure");
        loanModel.addColumn("EMI");
        loanModel.addColumn("Status");

        myLoanTable = new JTable(loanModel);

        myLoanTable.setRowHeight(28);
        myLoanTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
myLoanTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,15));
myLoanTable.getTableHeader().setBackground(new Color(0,102,204));
myLoanTable.getTableHeader().setForeground(Color.WHITE);
        JScrollPane loanScroll = new JScrollPane(myLoanTable);

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                optionScroll, loanScroll);

        split.setDividerLocation(250);

        add(split, BorderLayout.CENTER);
        add(applyPanel, BorderLayout.SOUTH);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // -------- LOAD DATA --------
        loadLoanOptions();
        loadMyLoans();

        // -------- APPLY BUTTON ACTION --------
        applyBtn.addActionListener(e -> applyLoan());

    }

    // ----------------------------
    // LOAD LOAN OPTIONS
    // ----------------------------
    private void loadLoanOptions() {

        optionModel.setRowCount(0);

        LoanDAO dao = new LoanDAO();

        List<LoanOption> list = dao.getLoanOptions();

        for (LoanOption o : list) {

            optionModel.addRow(new Object[]{
                    o.getLoanOptionId(),
                    o.getLoanAmount(),
                    o.getInterestRate(),
                    o.getTenureMonths(),
                    o.getEmi()
            });

        }
    }

    // ----------------------------
    // APPLY LOAN
    // ----------------------------
    private void applyLoan() {

        int row = optionTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(this, "Select Loan Option");
            return;
        }
        try {
            int loanOptionId = Integer.parseInt(optionModel.getValueAt(row, 0).toString());
            double amount = Double.parseDouble(optionModel.getValueAt(row, 1).toString());
            double interestRate = Double.parseDouble(optionModel.getValueAt(row, 2).toString());
            int tenureMonths = Integer.parseInt(optionModel.getValueAt(row, 3).toString());
            double emi = Double.parseDouble(optionModel.getValueAt(row, 4).toString());
            Loan loan = new Loan();
loan.setUserId(userId);
System.out.println("User Id=" +userId);
            loan.setUserId(userId);
            System.out.println("Amount=" +amount);
            System.out.println("Interest=" +interestRate);
            System.out.println("Tenure ="+ tenureMonths);
            System.out.println("EMI=" +emi);
            loan.setLoanOptionId(loanOptionId);

            loan.setAmount(amount);
            loan.setInterestRate(interestRate);
            loan.setTenureMonths(tenureMonths);
            loan.setEmi(emi);
            loan.setApplyDate(new java.sql.Date(System.currentTimeMillis()));
            loan.setStatus("PENDING");
            LoanDAO dao = new LoanDAO();

           // boolean success = dao.applyLoan(loan);

            if (dao.applyLoan(loan)) {

                JOptionPane.showMessageDialog(this, "Loan Applied Successfully");
                loadMyLoans();

            } else {

                JOptionPane.showMessageDialog(this, "Loan Application Failed");

            }

        }catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error");
        }
    }

        // ----------------------------
        // LOAD MY LOANS
        // ----------------------------
        private void loadMyLoans () {

            loanModel.setRowCount(0);

            LoanDAO dao = new LoanDAO();

            List<Loan> list = dao.getUserLoans(userId);

            for (Loan l : list) {

                loanModel.addRow(new Object[]{
                        l.getLoanId(),
                        l.getAmount(),
                        l.getInterestRate(),
                        l.getTenureMonths(),
                        l.getEmi(),
                        l.getStatus()
                });

            }

        }

    }
