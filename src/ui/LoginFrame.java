package ui;

import dao.UserDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private String role;

    public LoginFrame(String role) {

        this.role = role;

        setTitle("Bank Management System - " + role + " Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Background
        JPanel backgroundPanel = new JPanel(new GridBagLayout());
        backgroundPanel.setBackground(new Color(235, 242, 250));

        // Login Card
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(750, 550));
        card.setBackground(Color.WHITE);
        card.setLayout(new GridBagLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220),1),
                new EmptyBorder(30,40,30,40)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12,12,12,12);
        gbc.weightx=1;
        gbc.anchor= GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel(role + " LOGIN");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(new Color(25,118,210));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        card.add(title, gbc);

        // Username Label
        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        card.add(userLabel, gbc);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        usernameField.setPreferredSize(new Dimension(350,55));

        gbc.gridx = 1;
        card.add(usernameField, gbc);

        // Password Label
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        gbc.gridx = 0;
        gbc.gridy = 2;
        card.add(passLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        passwordField.setPreferredSize(new Dimension(350,55));

        gbc.gridx = 1;
        card.add(passwordField, gbc);

        // Login Button
        JButton loginBtn = new JButton("Login");
        styleButton(loginBtn,
                new Color(25,118,210));

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        card.add(loginBtn, gbc);

        // Register Button
        JButton registerBtn = new JButton("Register");
        styleButton(registerBtn,
                new Color(46,125,50));

        gbc.gridy = 4;
        card.add(registerBtn, gbc);

        // Back Button
        JButton backBtn = new JButton("Back");
        styleButton(backBtn,
                new Color(211,47,47));

        gbc.gridy = 5;
        card.add(backBtn, gbc);

        // Footer
        JLabel footer = new JLabel("Secure Banking Portal");
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        footer.setForeground(Color.GRAY);
        footer.setFont(new Font("Segoe UI", Font.ITALIC, 14));

        gbc.gridy = 6;
        card.add(footer, gbc);

        backgroundPanel.add(card);

        add(backgroundPanel);

        // Actions
        loginBtn.addActionListener(e -> loginUser());

        registerBtn.addActionListener(e -> {

            dispose();

            if (role.equalsIgnoreCase("USER")) {
                new UserRegisterFrame();
            }
            else if (role.equalsIgnoreCase("BANK")) {
                new BankRegisterFrame();
            }
            else {
                JOptionPane.showMessageDialog(this,
                        "Admin registration is not allowed!");
            }
        });

        backBtn.addActionListener(e -> {
            dispose();
            new MainFrame();
        });

        setVisible(true);
    }

    private void styleButton(JButton button, Color color) {

        button.setBackground(color);
        button.setForeground(Color.WHITE);

        button.setFont(new Font("Segoe UI", Font.BOLD, 18));

        button.setFocusPainted(false);

        button.setPreferredSize(new Dimension(250,55));

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
    }

    private void loginUser() {

        String username = usernameField.getText().trim();

        String password =
                new String(passwordField.getPassword());

        if(username.isEmpty() || password.isEmpty()){

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields"
            );

            return;
        }

        UserDAO dao = new UserDAO();

        boolean valid =
                dao.loginUser(username,password,role);

        if(valid){

            JOptionPane.showMessageDialog(
                    this,
                    "Login Successful"
            );

            dispose();

            if(role.equals("ADMIN")){

                new AdminDashboardFrame(username);

            }else if(role.equals("USER")){

                new CustomerDashboardFrame(username);

            }else if(role.equals("BANK")){

                new BankDashboardFrame();

            }

        }else{

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Username or Password"
            );
        }
    }
}