package app;

import com.jnrsmcu.sdk.netdevice.*;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {

    private JTextArea textArea;
    private JTextField txtPort;
    private JPanel pane;

    private RSServer rsServer;
    private MyDataListener listener;

    public AppFrame() {
        initialize();
    }

    private void initialize() {
        textArea = new JTextArea();
        txtPort = new JTextField();

        pane = new JPanel();

        GridBagLayout gbl = new GridBagLayout();
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

    }

}
