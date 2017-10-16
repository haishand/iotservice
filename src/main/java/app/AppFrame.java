package app;

import com.jnrsmcu.sdk.netdevice.*;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AppFrame extends JFrame {

    public static Logger logger = Logger.getLogger(AppFrame.class);

    private JScrollPane scrollPane;
    private JTextArea textArea;
    private JLabel lblPort;
    private JTextField txtPort;
    private JButton btnStart;
    private JButton btnStop;
    private JButton btnSetting;
    private JPanel container;

    private RSServer rsServer;
    private MyDataListener listener;

    public AppFrame() {
        initUI();
    }

    private void initUI() {
        textArea = new JTextArea();
        lblPort = new JLabel("服务器端口：");
        txtPort = new JTextField("2404");
        btnStart = new JButton("启动");
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                rsServer = RSServer.Initiate(Integer.parseInt(txtPort.getText()));
                rsServer.addDataListener(listener);
                try {
                    rsServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error(e);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    logger.error(e);
                }
            }
        });
        btnStop = new JButton("停止");
        btnStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    rsServer.stop();
                } catch (IOException e) {
                    logger.error(e);
                    e.printStackTrace();
                }
            }
        });
        btnSetting = new JButton("设置");
        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(250, 250));

        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1; c.gridx = 0; c.gridy = 0;
        container.add(lblPort, c);
        c.gridx++;
        container.add(txtPort, c);
        c.gridx++;
        container.add(btnStart, c);
        c.gridx++;
        container.add(btnStop, c);
        c.gridx++;
        container.add(btnSetting, c);

        c.gridwidth = 5; c.gridx = 0; c.gridy++;
        container.add(scrollPane, c);

    }

    public void run() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
