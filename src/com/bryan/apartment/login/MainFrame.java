package com.bryan.apartment.login;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
   JButton btnViewUser,btnUtilities,btnMaintenance,btnTransaction,btnAbout,btnContact;
   ImageIcon viewUserIcon,utilitiesIcon,maintenanceIcon,transactionIcon,aboutIcon,contactIcon;
   JMenuBar menuBar;
   JMenu fileMenu;
   JMenuItem logoutMenu;
   JPanel panelMenu;

    public MainFrame(){
        super("Main Frame");
        setSize(720,480);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        setUIComponents();


    }
    private void setUIComponents(){
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        logoutMenu = new JMenuItem("Log out");
        fileMenu.add(logoutMenu);
        logoutMenu.addActionListener(e -> {
            this.dispose();
            new Login().setVisible(true);
        });

        panelMenu = new JPanel();
        panelMenu.setPreferredSize(new Dimension(720,100));
        panelMenu.setBackground(new Color(0x2B3A55));
        panelMenu.setLayout(new GridLayout(1,10,0,0));
        add(panelMenu,BorderLayout.NORTH);

        menuItem(btnViewUser,viewUserIcon,"user.png","View User");
        menuItem(btnUtilities,utilitiesIcon,"home.png","Utilities");
        menuItem(btnMaintenance,maintenanceIcon,"settings.png","Maintenance");
        menuItem(btnTransaction,transactionIcon,"transaction.png","<html><center>Transaction <br>Report</center></html>");
        menuItem(btnAbout,aboutIcon,"information.png","About");
        menuItem(btnContact,contactIcon,"support.png","<html>Contact <br>Support</html>");

}
private void menuItem(JButton jButton,ImageIcon imageIcon,String image,String text){
    jButton = new JButton();
    jButton.setText(text);
    jButton.setPreferredSize(new Dimension(100,50));
    imageIcon = new ImageIcon(new ImageIcon("asset/images/"+image).getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
    jButton.setIcon(imageIcon);
    jButton.setFocusable(false);
    jButton.setVerticalTextPosition(SwingConstants.BOTTOM);
    jButton.setHorizontalTextPosition(SwingConstants.CENTER);
    panelMenu.add(jButton);   
}

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }

}
