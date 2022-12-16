package com.bryan.apartment.login;

import com.bryan.apartment.users.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
   JButton btnViewUser,btnUtilities,btnMaintenance,btnTransaction,btnAbout,btnContact,btnInventory;
   JButton btnAptOne,btnAptTwo,btnAptThree,btnAptFour,btnAptFive,btnAptSix,btnAptSeven,btnAptEight;
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

        btnViewUser = new JButton();
        menuItem(btnViewUser,"user.png","View User");
        btnUtilities = new JButton();
        menuItem(btnUtilities,"home.png","Utilities");
        btnMaintenance = new JButton();
        menuItem(btnMaintenance,"settings.png","Maintenance");
        btnTransaction = new JButton();
        menuItem(btnTransaction,"transaction.png","<html><center>Transaction <br>Report</center></html>");
        btnInventory = new JButton();
        menuItem(btnInventory,"inventory.png","Inventory");
        btnAbout = new JButton();
        menuItem(btnAbout,"information.png","About");
        btnContact = new JButton();
        menuItem(btnContact,"support.png","<html>Contact <br>Support</html>");

        btnViewUser.addActionListener(e -> new UserInfo().setVisible(true));


        panelMain = new JPanel();
        panelMain.setPreferredSize(new Dimension(800,500));
        panelMain.setBackground(new Color(0xD4BBA3));
        panelMain.setLayout(new GridLayout(2,4,20,20));
        panelMain.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        add(panelMain,BorderLayout.CENTER);

        btnAptOne = new JButton();
        btnApartment(btnAptOne,"Apartment 1");
        btnAptTwo = new JButton();
        btnApartment(btnAptTwo,"Apartment 2");
        btnAptThree = new JButton();
        btnApartment(btnAptThree,"Apartment 3");
        btnAptFour = new JButton();
        btnApartment(btnAptFour,"Apartment 4");
        btnAptFive = new JButton();
        btnApartment(btnAptFive,"Apartment 5");
        btnAptSix = new JButton();
        btnApartment(btnAptSix,"Apartment 6");
        btnAptSeven = new JButton();
        btnApartment(btnAptSeven,"Apartment 7");
        btnAptEight = new JButton();
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
    private void menuItem(JButton jButton,String image,String text){
        jButton.setText(text);
        jButton.setPreferredSize(new Dimension(100,50));
        jButton.setIcon(new ImageIcon(new ImageIcon("asset/images/"+image).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        jButton.setFont(new Font("Arial",Font.BOLD,12));
        jButton.setBackground(new Color(210, 167, 137));
        jButton.setFocusable(false);
        jButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        jButton.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelMenu.add(jButton);
    }
    private void btnApartment(JButton jButton,String text){

        jButton.setText(text);
        jButton.setFont(new Font("Arial",Font.BOLD,15));
        jButton.setBackground(new Color(0xD4BBA3));
        jButton.setIcon(new ImageIcon(new ImageIcon("asset/images/apartment.png").getImage().getScaledInstance(70,70,Image.SCALE_SMOOTH)));
        jButton.setFocusable(false);
        jButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        jButton.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jButton.setBackground(new Color(0xC4915E));
            }
            @Override
            public void mouseExited(MouseEvent e){
                jButton.setBackground(new Color(0xD4BBA3));
            }
        });
        panelMain.add(jButton);
    }
}
