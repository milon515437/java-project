import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {
    public HomePage() {
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create panels for header, content, and footer
        JPanel headerPanel = new JPanel();
        JPanel contentPanel = new JPanel();
        JPanel footerPanel = new JPanel();

        // Set background colors for panels
        headerPanel.setBackground(Color.BLUE);
        contentPanel.setBackground(Color.WHITE);
        footerPanel.setBackground(Color.GRAY);

        // Create components for header panel
        JLabel titleLabel = new JLabel("Welcome to My Website");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);

        // Create components for content panel
        JTextArea contentTextArea = new JTextArea();
        contentTextArea.setText("This my homepage");
        contentTextArea.setEditable(false);
        contentPanel.add(contentTextArea);

        // Create components for footer panel
        JButton aboutButton = new JButton("About");
        JButton contactButton = new JButton("Contact");
        footerPanel.add(aboutButton);
        footerPanel.add(contactButton);

        // Add panels to frame
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePage());
    }
}
