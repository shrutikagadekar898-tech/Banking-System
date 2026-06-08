package ui;

import util.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class DashboardPanel extends JPanel {

    JLabel usersLabel,balanceLabel,loanLabel;

    public DashboardPanel(){

        setLayout(new GridLayout(1,3,20,20));

        usersLabel = new JLabel();
        balanceLabel = new JLabel();
        loanLabel = new JLabel();

        add(createCard("Total Users",usersLabel));
        add(createCard("Total Balance",balanceLabel));
        add(createCard("Total Loans",loanLabel));

        loadStats();
    }

    private JPanel createCard(String title,JLabel value){

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel t = new JLabel(title,SwingConstants.CENTER);
        value.setHorizontalAlignment(SwingConstants.CENTER);
        value.setFont(new Font("Arial",Font.BOLD,20));

        panel.add(t,BorderLayout.NORTH);
        panel.add(value,BorderLayout.CENTER);

        return panel;
    }

    private void loadStats(){

        try(Connection con = DBConnection.getConnection()){

            Statement st = con.createStatement();

            ResultSet rs1 = st.executeQuery("SELECT COUNT(*) FROM users");
            if(rs1.next()) usersLabel.setText(""+rs1.getInt(1));

            ResultSet rs2 = st.executeQuery("SELECT SUM(balance) FROM accounts");
            if(rs2.next()) balanceLabel.setText("₹"+rs2.getDouble(1));

            ResultSet rs3 = st.executeQuery("SELECT COUNT(*) FROM loans");
            if(rs3.next()) loanLabel.setText(""+rs3.getInt(1));

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}