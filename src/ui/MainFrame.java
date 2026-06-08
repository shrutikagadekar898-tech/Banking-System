package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Bank Management System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255));

        // ===== TITLE =====

        JLabel title = new JLabel("BANK MANAGEMENT SYSTEM", JLabel.CENTER);

        title.setFont(new Font("Algerian", Font.BOLD, 50));
        title.setForeground(Color.BLACK);

        title.setBorder(BorderFactory.createEmptyBorder(70,0,0,0));

        mainPanel.add(title, BorderLayout.NORTH);

        // ===== CENTER PANEL =====

        JPanel center = new JPanel(new GridLayout(1,3,50,50));

        center.setBackground(new Color(240,248,255));
        center.setBorder(BorderFactory.createEmptyBorder(100,150,100,150));

        JButton adminBtn = createButton("ADMIN");
        JButton userBtn = createButton("USER");
        JButton bankBtn = createButton("BANK");

        center.add(adminBtn);
        center.add(userBtn);
        center.add(bankBtn);

        mainPanel.add(center, BorderLayout.CENTER);

        // ===== FOOTER =====

        JLabel footer = new JLabel(
                "Secure Banking Management System",
                JLabel.CENTER);

        footer.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        footer.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));

        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);

        // ===== ACTIONS =====

        adminBtn.addActionListener(e -> {
            dispose();
            new LoginFrame("ADMIN");
        });

        userBtn.addActionListener(e -> {
            dispose();
            new LoginFrame("USER");
        });

        bankBtn.addActionListener(e -> {
            dispose();
            new LoginFrame("BANK");
        });

        setVisible(true);
    }

    private JButton createButton(String text){

        JButton btn = new JButton(text);

        btn.setFont(new Font("Britannic Bold", Font.BOLD, 24));

        btn.setBackground(new Color(32,136,203));

        btn.setForeground(Color.WHITE);

        btn.setFocusPainted(false);

        btn.setPreferredSize(new Dimension(250,120));

        return btn;
    }
}