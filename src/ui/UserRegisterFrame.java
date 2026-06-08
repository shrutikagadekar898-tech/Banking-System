package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UserRegisterFrame extends JFrame {

    private JTextField fullNameField, dobField, mobileField,
            emailField, addressField, usernameField;

    private JPasswordField passwordField;

    private JRadioButton maleBtn, femaleBtn;
    private ButtonGroup genderGroup;

    public UserRegisterFrame() {

        setTitle("User Registration");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel backgroundPanel = new JPanel(new GridBagLayout());
        backgroundPanel.setBackground(new Color(235, 242, 250));

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(850, 700));
        card.setBackground(Color.WHITE);
        card.setLayout(new GridBagLayout());

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(220,220,220),1),
                new EmptyBorder(25,25,25,25)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12,12,12,12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel("USER REGISTRATION");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(25,118,210));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        card.add(title, gbc);

        gbc.gridwidth = 1;

        // Full Name
        gbc.gridx = 0;
        gbc.gridy = 1;
        card.add(createLabel("Full Name"), gbc);

        fullNameField = createTextField();
        gbc.gridx = 1;
        card.add(fullNameField, gbc);

        // DOB
        gbc.gridx = 0;
        gbc.gridy = 2;
        card.add(createLabel("DOB"), gbc);

        dobField = createTextField();
        gbc.gridx = 1;
        card.add(dobField, gbc);

        // Gender
        gbc.gridx = 0;
        gbc.gridy = 3;
        card.add(createLabel("Gender"), gbc);

        JPanel genderPanel = new JPanel(new FlowLayout(
                FlowLayout.LEFT));

        maleBtn = new JRadioButton("Male");
        femaleBtn = new JRadioButton("Female");

        genderGroup = new ButtonGroup();
        genderGroup.add(maleBtn);
        genderGroup.add(femaleBtn);

        genderPanel.add(maleBtn);
        genderPanel.add(femaleBtn);

        gbc.gridx = 1;
        card.add(genderPanel, gbc);

        // Mobile
        gbc.gridx = 0;
        gbc.gridy = 4;
        card.add(createLabel("Mobile"), gbc);

        mobileField = createTextField();
        gbc.gridx = 1;
        card.add(mobileField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 5;
        card.add(createLabel("Email"), gbc);

        emailField = createTextField();
        gbc.gridx = 1;
        card.add(emailField, gbc);

        // Address
        gbc.gridx = 0;
        gbc.gridy = 6;
        card.add(createLabel("Address"), gbc);

        addressField = createTextField();
        gbc.gridx = 1;
        card.add(addressField, gbc);

        // Username
        gbc.gridx = 0;
        gbc.gridy = 7;
        card.add(createLabel("Username"), gbc);

        usernameField = createTextField();
        gbc.gridx = 1;
        card.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 8;
        card.add(createLabel("Password"), gbc);

        passwordField = new JPasswordField();
        passwordField.setFont(
                new Font("Segoe UI", Font.PLAIN, 18));
        passwordField.setPreferredSize(
                new Dimension(350,45));

        gbc.gridx = 1;
        card.add(passwordField, gbc);

        // Buttons
        JButton registerBtn = new JButton("REGISTER");
        styleButton(registerBtn,
                new Color(46,125,50));

        JButton backBtn = new JButton("BACK");
        styleButton(backBtn,
                new Color(211,47,47));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);

        buttonPanel.add(registerBtn);
        buttonPanel.add(backBtn);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;

        card.add(registerBtn, gbc);
        gbc.gridy=10;
        card.add(backBtn, gbc);
        backgroundPanel.add(card);

        add(backgroundPanel);

        registerBtn.addActionListener(e -> registerUser());

        backBtn.addActionListener(e -> {
            dispose();
            new LoginFrame("USER");
        });

        setVisible(true);
    }

    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);
        label.setFont(
                new Font("Segoe UI", Font.BOLD, 18));

        return label;
    }

    private JTextField createTextField() {

        JTextField field = new JTextField();

        field.setFont(
                new Font("Segoe UI", Font.PLAIN, 18));

        field.setPreferredSize(
                new Dimension(350,45));

        return field;
    }

    private void styleButton(JButton button, Color color) {

        button.setBackground(color);
        button.setForeground(Color.WHITE);

        button.setFont(
                new Font("Segoe UI", Font.BOLD, 18));

        button.setPreferredSize(
                new Dimension(160,50));

        button.setFocusPainted(false);
    }

    private void registerUser() {

        String fullName = fullNameField.getText().trim();
        String dob = dobField.getText().trim();
        String mobile = mobileField.getText().trim();
        String email = emailField.getText().trim();
        String address = addressField.getText().trim();
        String username = usernameField.getText().trim();

        String password =
                new String(passwordField.getPassword());

        String gender = maleBtn.isSelected()
                ? "Male"
                : femaleBtn.isSelected()
                ? "Female"
                : "";

        if(fullName.isEmpty() || dob.isEmpty() ||
                mobile.isEmpty() || email.isEmpty() ||
                address.isEmpty() || username.isEmpty() ||
                password.isEmpty() || gender.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields"
            );
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "User Registered Successfully!"
        );
    }
}