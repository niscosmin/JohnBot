import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

public class ConversationPage extends JFrame {

    private JButton sendButton;
    private JTextField jTextField;
    private JTextArea textArea;
    public Stack<String> stack = new Stack<>();

    ConversationPage() {
        setTitle("Conversation with JohnBot");

        setMessageComponents();
        sendSendButtonAcctionAndEnterKey();

        setSize(550, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setMessageComponents() {
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BorderLayout());

        sendButton = new JButton("Send");
        jTextField = new JTextField("Message...");
        JPanel bottomJPanel = new JPanel();
        bottomJPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomJPanel.setLayout(new BorderLayout());
        bottomJPanel.add(sendButton, BorderLayout.EAST);
        bottomJPanel.add(jTextField, BorderLayout.CENTER);

        JPanel topJPanel = new JPanel();
        topJPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topJPanel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        topJPanel.add(textArea);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        topJPanel.add(scroll);
        settingsText();

        centralPanel.add(topJPanel, BorderLayout.CENTER);
        centralPanel.add(bottomJPanel, BorderLayout.SOUTH);
        this.add(centralPanel);
    }

    private void settingsText() {
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Serif", Font.BOLD, 16));
        textArea.setEnabled(false);
    }

    private void sendSendButtonAcctionAndEnterKey() {
        jTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    memorizeText();
                    String sc = "Me:" + jTextField.getText();
                    textArea.append(sc + '\n');
                    setJohnBotAnswear();
                    jTextField.setText("");
                    jTextField.requestFocus();
                }
            }
        });

        sendButton.addActionListener(e -> {
            String sc = "Me:" + jTextField.getText();
            textArea.append(sc + '\n');
            memorizeText();
            setJohnBotAnswear();
            jTextField.setText("");
            jTextField.requestFocus();
        });

        jTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_PAGE_UP) {
                    String comanda = stack.pop();
                    jTextField.setText(comanda);
                    jTextField.requestFocus();
                }
            }
        });
    }

    private void setJohnBotAnswear() {
        String input = jTextField.getText();
        String[] output = input.split("\\s+");
        List<String> list1 = new ArrayList<>(Arrays.asList(output));

        List<String> list2 = new ArrayList<>();
        list2.add("hey");
        list2.add("hello");
        list2.add("good morning");
        list2.add("good day");
        list2.add("good afternoon");

        if (!Collections.disjoint(list1, list2)) {
            textArea.append("JohnBot: Hello!!" + '\n' + '\n');
        }

        if (jTextField.getText().equals("who are you?")) {
            textArea.append("JohnBot: Just a shor introduction: I'm just a bot-> JhonBot is my name, and I can help you with some information. Please type ACTIONS to see what can I do for you!" + '\n' + '\n');
        }

        if (jTextField.getText().equals("how are you?")) {
            textArea.append("JohnBot: I'm fine, thank you!" + '\n' + '\n');
        }

        if (Arrays.asList(output).contains("help")) {
            textArea.append("JohnBot: Yes, I can help you. Just tape ACTIONS to see what I can do for you." + '\n' + '\n');
        }

        if (jTextField.getText().equalsIgnoreCase("actions")) {
            textArea.append("JohnBot: List with things:" +
                    '\n' + "1. time;" +
                    '\n' + "2. sum + 1st number + 2nd number;" +
                    '\n' + "3. reduction + 1st number + 2nd number;" +
                    '\n' + "4. multiplication + 1st number + 2nd number;" +
                    '\n' + "5. divison + 1st number + 2nd number;" +
                    '\n' + "6. sqr + number;" +
                    '\n');
        }

        if (Arrays.asList(output).contains("sum")) {
            String[] output1 = input.split("\\s+");
            double result = Double.parseDouble(output1[1]) + Double.parseDouble(output1[3]);
            textArea.append("JohnBot: The sum is:" + result + '\n' + '\n');
        }

        if (Arrays.asList(output).contains("time")) {
            textArea.append("JohnBot: Time and date :" + LocalDateTime.now() + '\n' + '\n');
        }

        if (Arrays.asList(output).contains("reduction")) {
            String[] output1 = input.split("\\s+");
            double result = Double.parseDouble(output1[1]) - Double.parseDouble(output1[3]);
            textArea.append("JohnBot: The reduction is: " + result + '\n' + '\n');
        }

        if (Arrays.asList(output).contains("multiplication")) {
            String[] output1 = input.split("\\s+");
            double result = Double.parseDouble(output1[1]) * Double.parseDouble(output1[3]);
            textArea.append("JohnBot: The result of multiplication is: " + result + '\n' + '\n');
        }

        if (Arrays.asList(output).contains("divison")) {
            String[] output1 = input.split("\\s+");
            double result = Double.parseDouble(output1[1]) / Double.parseDouble(output1[3]);
            textArea.append("JohnBot: The result of divison is: " + result + '\n' + '\n');
        }

        if (Arrays.asList(output).contains("sqr")) {
            String[] output1 = input.split("\\s+");
            double number = Double.parseDouble(output1[1]);
            float result = (float) Math.sqrt(number);
            textArea.append("JohnBot: The square root is : " + result + '\n' + '\n');
        }
    }

    private void memorizeText() {
        String text = jTextField.getText();
        stack.push(text);
    }
}





