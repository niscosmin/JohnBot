import javax.swing.*;
import java.awt.*;

public class StartPage extends JFrame {

    private JButton jButton;

    StartPage(){
        setTitle("JohnBot");

        setInitComponents();

        setSize(550, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setInitComponents(){
        jButton = new JButton("Sarting conversation with JohnBot");
        JPanel jPanel = new JPanel();
        jPanel.setBorder(BorderFactory.createEmptyBorder(80,80,80,80));
        jPanel.setLayout(new BorderLayout());
        jPanel.add(jButton, BorderLayout.CENTER);
        this.add(jPanel);

        jButton.addActionListener(e-> {
            ConversationPage conversationPage = new ConversationPage();
            dispose();
        });
    };
}
