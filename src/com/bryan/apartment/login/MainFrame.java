package com.bryan.apartment.login;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
   JButton btnViewUser,btnUtilities,btnMaintenance,btnTransaction,btnAbout,btnContact,btnInventory;
   JButton btnAptOne,btnAptTwo,btnAptThree,btnAptFour,btnAptFive,btnAptSix,btnAptSeven,btnAptEight;
   ImageIcon viewUserIcon,utilitiesIcon,maintenanceIcon,transactionIcon,aboutIcon,contactIcon,inventoryIcon;
   JMenuBar menuBar;
   JMenu fileMenu;
   JMenuItem logoutMenu;
   JPanel panelMenu,panelMain,panelFooter;
   JLabel lblFooter;

    public MainFrame(){
        super("Main Frame");
        setSize(800,600);
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
        panelMenu.setPreferredSize(new Dimension(800,70));
        panelMenu.setBackground(new Color(0x2B3A55));
        panelMenu.setLayout(new GridLayout(1,10,0,0));
        add(panelMenu,BorderLayout.NORTH);

        menuItem(btnViewUser,viewUserIcon,"user.png","View User");
        menuItem(btnUtilities,utilitiesIcon,"home.png","Utilities");
        menuItem(btnMaintenance,maintenanceIcon,"settings.png","Maintenance");
        menuItem(btnTransaction,transactionIcon,"transaction.png","<html><center>Transaction <br>Report</center></html>");
        menuItem(btnInventory,inventoryIcon,"inventory.png","Inventory");
        menuItem(btnAbout,aboutIcon,"information.png","About");
        menuItem(btnContact,contactIcon,"support.png","<html>Contact <br>Support</html>");

        panelMain = new JPanel();
        panelMain.setPreferredSize(new Dimension(800,500));
        panelMain.setBackground(new Color(0xD4BBA3));
        panelMain.setLayout(new GridLayout(2,4,20,20));
        add(panelMain,BorderLayout.CENTER);

        btnApartment(btnAptOne,"Apartment 1");
        btnApartment(btnAptTwo,"Apartment 2");
        btnApartment(btnAptThree,"Apartment 3");
        btnApartment(btnAptFour,"Apartment 4");
        btnApartment(btnAptFive,"Apartment 5");
        btnApartment(btnAptSix,"Apartment 6");
        btnApartment(btnAptSeven,"Apartment 7");
        btnApartment(btnAptEight,"Apartment 8");


        panelFooter = new JPanel();
        panelFooter.setPreferredSize(new Dimension(700,40));
        panelFooter.setBackground(new Color(0x2B3A55));
        add(panelFooter,BorderLayout.SOUTH);

        lblFooter = new JLabel();
        lblFooter.setText("<html><center> © Copyright <br> All Rights Reserved 2022<center></html>");
        lblFooter.setHorizontalTextPosition(SwingConstants.CENTER);
        lblFooter.setHorizontalAlignment(SwingConstants.CENTER);
        lblFooter.setVerticalAlignment(SwingConstants.TOP);
        lblFooter.setForeground(Color.WHITE);
        lblFooter.setPreferredSize(new Dimension(700,40));
        panelFooter.add(lblFooter);

}
    private void menuItem(JButton jButton,ImageIcon imageIcon,String image,String text){
        jButton = new JButton();
        jButton.setText(text);
        jButton.setPreferredSize(new Dimension(100,50));
        imageIcon = new ImageIcon(new ImageIcon("asset/images/"+image).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        jButton.setIcon(imageIcon);
        jButton.setFont(new Font("Arial",Font.BOLD,12));
        jButton.setBackground(new Color(210, 167, 137));
        jButton.setFocusable(false);
        jButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        jButton.setHorizontalTextPosition(SwingConstants.CENTER);
        panelMenu.add(jButton);
    }
    private void btnApartment(JButton jButton,String text){
        jButton = new JButton();
        jButton.setText(text);
        jButton.setFont(new Font("Arial",Font.BOLD,12));
//        jButton.setBackground(new Color(210, 167, 137));
        jButton.setFocusable(false);
        jButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        jButton.setHorizontalTextPosition(SwingConstants.CENTER);
        panelMain.add(jButton);
    }



    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }

}
