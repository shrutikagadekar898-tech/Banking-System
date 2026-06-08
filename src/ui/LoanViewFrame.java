package ui;

import dao.LoanDAO;
import model.Loan;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class LoanViewFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public LoanViewFrame() {

        setTitle("Admin - View Loans");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= HEADER =================

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(25, 118, 210));
        headerPanel.setPreferredSize(new Dimension(100, 70));

        JLabel title = new JLabel("Loan Records");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        headerPanel.add(title, BorderLayout.WEST);

        add(headerPanel, BorderLayout.NORTH);

        // ================= TABLE =================

        model = new DefaultTableModel();

        model.addColumn("Loan ID");
        model.addColumn("User ID");
        model.addColumn("Amount");
        model.addColumn("Interest %");
        model.addColumn("Tenure");
        model.addColumn("EMI");
        model.addColumn("Status");

        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(35);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        table.setSelectionBackground(new Color(220, 235, 250));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        table.setRowHeight(35);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        setSize(1200,700);
        setLocationRelativeTo(null);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
        header.setPreferredSize(new Dimension(100, 40));
        header.setBackground(new Color(25, 118, 210));
        header.setForeground(Color.WHITE);

        // Center align all columns
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(center);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // ================= BOTTOM PANEL =================

        JPanel bottomPanel = new JPanel();

        JButton refreshBtn = new JButton("Refresh");
        JButton backBtn = new JButton("Back");

        refreshBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));

        bottomPanel.add(refreshBtn);
        bottomPanel.add(backBtn);

        backBtn.addActionListener(e -> dispose());
        add(bottomPanel, BorderLayout.SOUTH);

        // ================= EVENTS =================

        refreshBtn.addActionListener(e -> loadLoans());

        // ================= LOAD DATA =================
        table.getColumnModel().getColumn(6)
                .setCellRenderer(new DefaultTableCellRenderer() {

                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column) {

                        Component c =
                                super.getTableCellRendererComponent(
                                        table, value,
                                        isSelected,
                                        hasFocus,
                                        row,
                                        column);

                        String status = value.toString();

                        if(status.equalsIgnoreCase("APPROVED")) {

                            c.setForeground(new Color(0,128,0));

                        } else if(status.equalsIgnoreCase("REJECTED")) {

                            c.setForeground(Color.RED);

                        } else {

                            c.setForeground(
                                    new Color(255,140,0));
                        }

                        setHorizontalAlignment(
                                SwingConstants.CENTER);

                        return c;
                    }
                });
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        loadLoans();

        setVisible(true);
    }

    private void loadLoans() {

        model.setRowCount(0);

        LoanDAO dao = new LoanDAO();

        List<Loan> loans = dao.getAllLoans();

        for (Loan l : loans) {

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