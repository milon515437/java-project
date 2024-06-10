import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class MainFrame extends JFrame {
    public void initialize(User user){
        // info Panel
        JPanel infoJPanel = new JPanel();
        infoJPanel.setLayout(new GridLayout(0,2,5,5));
       infoJPanel.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));
        infoJPanel.add(new JLabel("Name"));
        infoJPanel.add(new JLabel(user.name));
        infoJPanel.add(new JLabel("Email"));
        infoJPanel.add(new JLabel("Phone"));
        infoJPanel.add(new JLabel(user.phone));
        infoJPanel.add(new JLabel("Address"));
        infoJPanel.add(new JLabel(user.address));
       
        add(infoJPanel,BorderLayout.NORTH);




        setTitle("Dashboard");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1100,650);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
