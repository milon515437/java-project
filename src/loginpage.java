import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

public class loginpage extends JFrame {
    final private Font mainFont = new Font("Segoe Print", Font.BOLD, 18);
    JTextField tfEmail;
    JPasswordField pfPassword;

    public void initialize() {
        // Header Label
        JLabel lbLoginJLabel = new JLabel("Login Page", SwingConstants.CENTER);
        lbLoginJLabel.setFont(mainFont);
        lbLoginJLabel.setForeground(Color.BLUE);

        // Email Label and TextField
        JLabel lbEmail = new JLabel("Email");
        lbEmail.setFont(mainFont);
        lbEmail.setForeground(Color.DARK_GRAY);

        tfEmail = new JTextField();
        tfEmail.setFont(mainFont);

        // Password Label and PasswordField
        JLabel lbPassword = new JLabel("Password");
        lbPassword.setFont(mainFont);
        lbPassword.setForeground(Color.DARK_GRAY);

        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);

        // Form Panel
        JPanel formJPanel = new JPanel();
        formJPanel.setLayout(new GridLayout(0, 1, 10, 10));
        formJPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        formJPanel.add(lbLoginJLabel);
        formJPanel.add(lbEmail);
        formJPanel.add(tfEmail);
        formJPanel.add(lbPassword);
        formJPanel.add(pfPassword);

        // Facebook Logo
        ImageIcon facebookIcon = new ImageIcon("Facebook.png"); // Path to your Facebook logo image
        JLabel fbLabel = new JLabel(facebookIcon);

        // Panel to hold the Facebook logo
        JPanel facebookPanel = new JPanel(new BorderLayout());
        facebookPanel.add(fbLabel, BorderLayout.CENTER);

        // Buttons Panel
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(mainFont);
        btnLogin.setBackground(Color.BLUE);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                User user = getAuthenticatedUser(email, password);

                if (user != null) {
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.initialize(user);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(loginpage.this,
                            "Email or Password Invalid",
                            "Try again",
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
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnCancel);

        // Initialize the Frame
        add(formJPanel, BorderLayout.CENTER);
        add(facebookPanel, BorderLayout.SOUTH); // Add Facebook logo below the form
        add(buttonPanel, BorderLayout.SOUTH);

        setTitle("Login Page");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setMinimumSize(new DimensionUIResource(350, 450));
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(230, 230, 250)); // Light lavender background
        setVisible(true);
    }

    private User getAuthenticatedUser(String email, String password) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME = "root"; // Replace with your DB username
        final String PASSWORD = ""; // Replace with your DB password

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.name = resultSet.getString("name");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone");
                user.address = resultSet.getString("address");
                user.password = resultSet.getString("password");
            }

            preparedStatement.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }

        return user;
    }

    public static void main(String[] args) {
        loginpage loginForm = new loginpage();
        loginForm.initialize();
    }
}

class User {
    String name;
    String email;
    String phone;
    String address;
    String password;
}

class MainFrame extends JFrame {
    public void initialize(User user) {
        // Implement the main frame initialization with user data
        setTitle("Main Frame");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Welcome, " + user.name, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe Print", Font.BOLD, 24));
        add(welcomeLabel, BorderLayout.CENTER);

        setVisible(true);
    }
}
