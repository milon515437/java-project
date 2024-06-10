import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

public class RegistrationPage extends JFrame {
    final private Font mainFont = new Font("Segoe Print", Font.BOLD, 18);
    JTextField tfName, tfEmail, tfPhone, tfAddress;
    JPasswordField pfPassword;

    public void initialize() {
        // Header Label
        JLabel lbRegister = new JLabel("Registration Page", SwingConstants.CENTER);
        lbRegister.setFont(mainFont);
        lbRegister.setForeground(Color.BLUE);
        lbRegister.setOpaque(true);
        lbRegister.setBackground(new Color(173, 216, 230)); // Light Blue background

        // Name Label and TextField
        JLabel lbName = new JLabel("Name");
        lbName.setFont(mainFont);
        lbName.setForeground(Color.DARK_GRAY);
        lbName.setOpaque(true);
        lbName.setBackground(new Color(240, 248, 255)); // Alice Blue background

        tfName = new JTextField();
        tfName.setFont(mainFont);
        tfName.setBackground(new Color(255, 248, 220)); // Cornsilk background

        // Email Label and TextField
        JLabel lbEmail = new JLabel("Email");
        lbEmail.setFont(mainFont);
        lbEmail.setForeground(Color.DARK_GRAY);
        lbEmail.setOpaque(true);
        lbEmail.setBackground(new Color(240, 248, 255)); // Alice Blue background

        tfEmail = new JTextField();
        tfEmail.setFont(mainFont);
        tfEmail.setBackground(new Color(255, 248, 220)); // Cornsilk background

        // Phone Label and TextField
        JLabel lbPhone = new JLabel("Phone");
        lbPhone.setFont(mainFont);
        lbPhone.setForeground(Color.DARK_GRAY);
        lbPhone.setOpaque(true);
        lbPhone.setBackground(new Color(240, 248, 255)); // Alice Blue background

        tfPhone = new JTextField();
        tfPhone.setFont(mainFont);
        tfPhone.setBackground(new Color(255, 248, 220)); // Cornsilk background

        // Address Label and TextField
        JLabel lbAddress = new JLabel("Address");
        lbAddress.setFont(mainFont);
        lbAddress.setForeground(Color.DARK_GRAY);
        lbAddress.setOpaque(true);
        lbAddress.setBackground(new Color(240, 248, 255)); // Alice Blue background

        tfAddress = new JTextField();
        tfAddress.setFont(mainFont);
        tfAddress.setBackground(new Color(255, 248, 220)); // Cornsilk background

        // Password Label and PasswordField
        JLabel lbPassword = new JLabel("Password");
        lbPassword.setFont(mainFont);
        lbPassword.setForeground(Color.DARK_GRAY);
        lbPassword.setOpaque(true);
        lbPassword.setBackground(new Color(240, 248, 255)); // Alice Blue background

        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);
        pfPassword.setBackground(new Color(255, 248, 220)); // Cornsilk background

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        formPanel.setBackground(new Color(245, 245, 245)); // White Smoke background
        formPanel.add(lbRegister);
        formPanel.add(lbName);
        formPanel.add(tfName);
        formPanel.add(lbEmail);
        formPanel.add(tfEmail);
        formPanel.add(lbPhone);
        formPanel.add(tfPhone);
        formPanel.add(lbAddress);
        formPanel.add(tfAddress);
        formPanel.add(lbPassword);
        formPanel.add(pfPassword);

        // Buttons Panel
        JButton btnRegister = new JButton("Register");
        btnRegister.setFont(mainFont);
        btnRegister.setBackground(Color.PINK); // Pink background
        btnRegister.setForeground(Color.WHITE);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = tfName.getText();
                String email = tfEmail.getText();
                String phone = tfPhone.getText();
                String address = tfAddress.getText();
                String password = String.valueOf(pfPassword.getPassword());

                if (registerUser(name, email, phone, address, password)) {
                    JOptionPane.showMessageDialog(RegistrationPage.this,
                            "User Registered Successfully",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    LoginPage loginPage = new LoginPage();
                    loginPage.initialize();
                } else {
                    JOptionPane.showMessageDialog(RegistrationPage.this,
                            "Error Registering User",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(mainFont);
        btnCancel.setBackground(Color.RED);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.setBackground(new Color(245, 245, 245)); // White Smoke background
        buttonPanel.add(btnRegister);
        buttonPanel.add(btnCancel);

        // Initialize the Frame
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setTitle("Registration Page");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(800, 600); // Set the width to 800 and height to 600
        setMinimumSize(new DimensionUIResource(800, 600)); // Ensure the minimum size is also set
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(180, 200, 150)); // Light lavender background
        setVisible(true);
    }

    private boolean registerUser(String name, String email, String phone, String address, String password) {
        final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME = "root";  // Replace with your DB username
        final String PASSWORD = "";      // Replace with your DB password

        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO user (name, email, phone, address, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, password);

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        RegistrationPage registrationForm = new RegistrationPage();
        registrationForm.initialize();
    }
}
