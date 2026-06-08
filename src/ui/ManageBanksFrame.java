package ui;

import dao.BankDAO;
import model.Bank;
import javax.swing.table.JTableHeader;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManageBanksFrame extends JFrame {

    JTable table;
    DefaultTableModel model;
    JTextField nameField,branchField,ifscField;

    public ManageBanksFrame(){

        setTitle("Manage Banks");
        setSize(800,500);
        setLocationRelativeTo(null);
        JButton backBtn = new JButton("← Back");

        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> {
            dispose();
            new BankDashboardFrame();
        });
        setLayout(new BorderLayout());

        JPanel form=new JPanel(new GridLayout(4,2));

        nameField=new JTextField();
        branchField=new JTextField();
        ifscField=new JTextField();

        form.add(new JLabel("Bank Name"));
        form.add(nameField);

        form.add(new JLabel("Branch"));
        form.add(branchField);

        form.add(new JLabel("IFSC"));
        form.add(ifscField);

        JButton addBtn=new JButton("Add Bank");
        JButton updateBtn= new JButton("Update Bank");
        JButton deleteBtn = new JButton("Delete Bank");
        form.add(addBtn);
        form.add(updateBtn);
        form.add(deleteBtn);
        add(form,BorderLayout.NORTH);

        model=new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Bank Name");
        model.addColumn("Branch");
        model.addColumn("IFSC");

        table=new JTable(model);

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
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
        header.setBackground(new Color(25,118,210));
        header.setForeground(Color.WHITE);
        loadBanks();

        addBtn.addActionListener(e -> {

            String name=nameField.getText();
            String branch=branchField.getText();
            String ifsc=ifscField.getText();

            BankDAO dao=new BankDAO();

            if(dao.addBank(name,branch,ifsc)){

                JOptionPane.showMessageDialog(this,"Bank Added");
                loadBanks();
            }
        });
        updateBtn.addActionListener(e -> {

            int row = table.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(this,"Select Bank");
                return;
            }

            int id = (int) model.getValueAt(row,0);

            BankDAO dao = new BankDAO();

            dao.updateBank(
                    id,
                    nameField.getText(),
                    branchField.getText(),
                    ifscField.getText()
            );

            loadBanks();

        });

        // ===== Delete Bank =====

        deleteBtn.addActionListener(e -> {

            int row = table.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(this,"Select Bank");
                return;
            }

            int id = (int) model.getValueAt(row,0);

            BankDAO dao = new BankDAO();

            dao.deleteBank(id);

            loadBanks();

        });

        // ===== Refresh =====
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //refreshBtn.addActionListener(e -> loadBanks());
        setVisible(true);
    }
  /*  table.getSelectionModel().addListSelectionListener(e -> {

        int row = table.getSelectedRow();

        if(row != -1){

            nameField.setText(model.getValueAt(row,1).toString());
            branchField.setText(model.getValueAt(row,2).toString());
            ifscField.setText(model.getValueAt(row,3).toString());

        }

    });

}
*/
    private void loadBanks(){

        model.setRowCount(0);

        BankDAO dao=new BankDAO();

        List<Bank> list=dao.getAllBanks();

        for(Bank b:list){

            model.addRow(new Object[]{
                    b.getBankId(),
                    b.getBankName(),
                    b.getBranch(),
                    b.getIfsc()
            });
        }
    }
}