package app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TrayAppTest extends JFrame {
    TrayIcon trayIcon;

    public TrayAppTest() {
        try {
            System.out.println(getClass().getClassLoader().getResource(""));
            trayIcon = new TrayIcon(ImageIO.read(ClassLoader.getSystemResource("images/bulb.gif")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TrayIcon trayIcon = null;
        try {
//            System.out.println(getClass().getClassLoader().getResource(""));
            trayIcon = new TrayIcon(ImageIO.read(ClassLoader.getSystemResource("images/bulb.gif")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        SystemTray tray = SystemTray.getSystemTray();
        PopupMenu popup = new PopupMenu();

        MenuItem aboutItem = new MenuItem("About");
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "About");

            }
        });
        popup.add(aboutItem);


        trayIcon.setPopupMenu(popup);
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

}
