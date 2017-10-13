package app;

import com.jnrsmcu.sdk.netdevice.*;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {

    private JScrollPane scrollPane;
    private JTextArea textArea;
    private JTextField txtPort;
    private JPanel container;

    private RSServer rsServer;
    private MyDataListener listener;

    public AppFrame() {
        initialize();
    }

    private void initialize() {
        textArea = new JTextArea();
        txtPort = new JTextField("test");
        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(250, 250));

        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0;
        container.add(txtPort, c);
        c.gridy++;
        container.add(scrollPane, c);

    }

    public void display() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
