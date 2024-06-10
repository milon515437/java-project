import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePage extends JFrame {
    private JPanel contentPanel;
    private JTextArea contentTextArea;
    private JLabel statusLabel;
    private JLabel clockLabel;

    public HomePage() {
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem homeMenuItem = new JMenuItem("Home");
        JMenuItem profileMenuItem = new JMenuItem("Profile");
        JMenuItem logoutMenuItem = new JMenuItem("Logout");

        homeMenuItem.setMnemonic('H'); // Alt + H
        profileMenuItem.setMnemonic('P'); // Alt + P
        logoutMenuItem.setMnemonic('L'); // Alt + L

        menu.add(homeMenuItem);
        menu.add(profileMenuItem);
        menu.add(logoutMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Add a "Login" menu item
        JMenuItem loginMenuItem = new JMenuItem("Login");
        loginMenuItem.setMnemonic('G'); // Alt + G
        menu.add(loginMenuItem);

        // Create panels for header, sidebar, content, and footer
        JPanel headerPanel = new JPanel(new BorderLayout());
        JPanel sidebarPanel = new JPanel();
        contentPanel = new JPanel(new BorderLayout());
        JPanel footerPanel = new JPanel(new BorderLayout());

        // Set background colors for panels
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel Blue
        sidebarPanel.setBackground(new Color(245, 245, 245)); // White Smoke
        contentPanel.setBackground(Color.WHITE);
        footerPanel.setBackground(new Color(105, 105, 105)); // Dim Gray

        // Create components for header panel
        JLabel titleLabel = new JLabel("Welcome to My Website", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        JLabel logoLabel = new JLabel(new ImageIcon("path/to/your/logo.png")); // Add your logo image path here
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(logoLabel, BorderLayout.WEST);

        // Create clock label
        clockLabel = new JLabel();
        clockLabel.setForeground(Color.WHITE);
        clockLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        updateClock();
        Timer timer = new Timer(1000, e -> updateClock());
        timer.start();
        headerPanel.add(clockLabel, BorderLayout.EAST);

        // Create components for sidebar panel
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        JButton dashboardButton = new JButton("Dashboard");
        JButton settingsButton = new JButton("Settings");
        JButton helpButton = new JButton("Help");
        JButton notificationsButton = new JButton("Notifications");
        dashboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        helpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        notificationsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebarPanel.add(dashboardButton);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebarPanel.add(settingsButton);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebarPanel.add(helpButton);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebarPanel.add(notificationsButton);

        // Add tooltips for better user guidance
        dashboardButton.setToolTipText("Go to the Dashboard");
        settingsButton.setToolTipText("Open Settings");
        helpButton.setToolTipText("Get Help");
        notificationsButton.setToolTipText("View Notifications");

        // Create components for content panel
        contentTextArea = new JTextArea("This is my homepage");
        contentTextArea.setEditable(false);
        contentTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        contentTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(contentTextArea);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Add an image to the content panel
        JLabel imageLabel = new JLabel(new ImageIcon("path/to/your/image.jpg")); // Add your image path here
        contentPanel.add(imageLabel, BorderLayout.NORTH);

        // Create components for footer panel
        JLabel footerLabel = new JLabel("© 2024 My Website - All rights reserved", SwingConstants.CENTER);
        footerLabel.setForeground(Color.WHITE);
        footerPanel.add(footerLabel, BorderLayout.CENTER);

        JButton aboutButton = new JButton("About");
        JButton contactButton = new JButton("Contact");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(aboutButton);
        buttonPanel.add(contactButton);
        footerPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Status bar
        statusLabel = new JLabel("Status: Ready");
        statusLabel.setForeground(Color.WHITE);
        footerPanel.add(statusLabel, BorderLayout.WEST);

        // Add action listeners for buttons
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HomePage.this,
                        "This is an About dialog.",
                        "About",
                        JOptionPane.INFORMATION_MESSAGE);
                updateStatus("Viewed About");
            }
        });

        contactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HomePage.this,
                        "This is a Contact dialog.",
                        "Contact",
                        JOptionPane.INFORMATION_MESSAGE);
                updateStatus("Viewed Contact");
            }
        });

        // Add action listeners for menu items
        homeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHomeContent();
                updateStatus("Viewed Home");
            }
        });

        profileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProfileContent();
                updateStatus("Viewed Profile");
            }
        });

        loginMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle login functionality here
                JOptionPane.showMessageDialog(HomePage.this,
                        "Please log in to continue.",
                        "Login Required",
                        JOptionPane.INFORMATION_MESSAGE);
                // For simplicity, let's just close the current window
                dispose();
                // You can open your login page here if you have one
                // LoginPage loginPage = new LoginPage();
                // loginPage.setVisible(true);
            }
        });

        logoutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HomePage.this,
                        "You have been logged out.",
                        "Logout",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });

        // Add action listeners for sidebar buttons
        dashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDashboardContent();
                updateStatus("Viewed Dashboard");
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSettingsContent();
                updateStatus("Viewed Settings");
            }
        });

        helpButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHelpContent();
                updateStatus("Viewed Help");
            }
        });
        
        notificationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNotificationsContent();
                updateStatus("Viewed Notifications");
            }
        });
        
        // Add panels to frame
        add(headerPanel, BorderLayout.NORTH);
        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
        
        setVisible(true);
        }
        private void updateClock() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        clockLabel.setText(sdf.format(new Date()));
        }
        
        private void updateStatus(String status) {
        statusLabel.setText("Status: " + status);
        }
        
        private void showHomeContent() {
        contentTextArea.setText("This is my homepage");
        }
        
        private void showProfileContent() {
        contentTextArea.setText("User Profile:\n\nName: MD Milon\nEmail: mdmilon719992@gmail.com\nPhone: 01823024319");
        }
        
        private void showDashboardContent() {
        contentTextArea.setText("Dashboard Content");
        }
        
        private void showSettingsContent() {
        contentTextArea.setText("Settings Content");
        }
        
        private void showHelpContent() {
        contentTextArea.setText("Help Content");
        }
        
        private void showNotificationsContent() {
        contentTextArea.setText("Notifications Content");
        }
        
        public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePage());
        }
        }
        