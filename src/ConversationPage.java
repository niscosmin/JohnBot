
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Arrays;


public class ConversationPage extends JFrame {

    private JButton sendButton;
    private JTextField jTextField;
    private JTextArea textArea;

    ConversationPage(){
        setTitle("Conversation with JohnBot");

        setMessageComponents();
        sendSendButtonAcction();

        setSize(550, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setMessageComponents(){
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BorderLayout());

        sendButton = new JButton("Send");
        jTextField = new JTextField("Message...");
        JPanel bottomJPanel = new JPanel();
        bottomJPanel.setBorder(BorderFactory.createEmptyBorder(10,10, 10, 10));
        bottomJPanel.setLayout(new BorderLayout());
        bottomJPanel.add(sendButton,BorderLayout.EAST);
        bottomJPanel.add(jTextField, BorderLayout.CENTER);

        JPanel topJPanel = new JPanel();
        topJPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        topJPanel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        topJPanel.add(textArea);
        JScrollPane scroll = new JScrollPane (textArea);
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        topJPanel.add(scroll);
        settingsText();

        centralPanel.add(topJPanel, BorderLayout.CENTER);
        centralPanel.add(bottomJPanel, BorderLayout.SOUTH);
        this.add(centralPanel);
    }

    private void settingsText(){
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Serif", Font.BOLD, 16  ));
        textArea.setEnabled(false);
    }

    private void sendSendButtonAcction(){
        sendButton.addActionListener(e->{
            String sc= "Me:" + jTextField.getText();
            textArea.append(sc+'\n');
            setJohnBotAnswear();
            jTextField.setText("");
            jTextField.requestFocus();
        });
    }


    private void setJohnBotAnswear(){
        String input = jTextField.getText();
        String[] output= input.split("\\s+");

        if (Arrays.asList(output).contains("hey")){
            textArea.append("JohnBot: Hello!!"+'\n'+'\n');
        }

        if(jTextField.getText().equals("who are you?")){
            textArea.append("JohnBot: Just a shor introduction: I'm just a bot-> JhonBot is my name, and I can help you with some information. Please type ACTION to see what can I do for you!"+'\n'+'\n');
        }

        if (Arrays.asList(output).contains("ACTIONS")) {
            textArea.append("JohnBot: List with things:" +
                    '\n'+"1. time;" +
                    '\n'+"2. sum + 1st number + 2nd number;"+'\n'+'\n');
        }

        if (Arrays.asList(output).contains("sum")) {
            String[] output1= input.split("\\s+");
            int result = Integer.parseInt(output1[1]) + Integer.parseInt(output1[2]);
            textArea.append("JohnBot: The sum is:"+ result+'\n'+'\n');
        }

        if (Arrays.asList(output).contains("time")){
            textArea.append("JohnBot: Time and date :"+ LocalDateTime.now() +'\n'+'\n');
        }

    }
}



