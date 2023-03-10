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
   JMenu fileMenu,exitMenu,minimizeMenu;
   JMenuItem logoutMenu;
   JPanel panelMenu,panelMain,panelMainTitle,panelMainApt,panelFooter;
   JLabel lblFooter, lblClock,lblDate, lblPosition,lblName;



    public MainFrame(){
        super("Main Frame");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(131, 131, 131));
        setJMenuBar(menuBar);
        setUIComponents();


    }
    private void setUIComponents(){
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        menuBar.add(Box.createGlue());
        minimizeMenu = new JMenu("__");
        minimizeMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        minimizeMenu.setOpaque(true);
        minimizeMenu.setBackground(new Color(131, 131, 131));

        minimizeMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setState(JFrame.ICONIFIED);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                minimizeMenu.setBackground(new Color(192, 192, 192));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                minimizeMenu.setBackground(new Color(131, 131, 131));
            }
        });
        menuBar.add(minimizeMenu);
        exitMenu = new JMenu("X");
        exitMenu.setOpaque(true);
        exitMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitMenu.setBackground(new Color(131, 131, 131));
        menuBar.add(exitMenu);
        exitMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confirm Exit",JOptionPane.YES_NO_OPTION);
                if (result == 0){
                    System.exit(0);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                exitMenu.setBackground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e){
                exitMenu.setBackground(new Color(131, 131, 131));
            }
        });

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
        panelMain.setLayout(new BorderLayout());

        add(panelMain,BorderLayout.CENTER);

        panelMainTitle = new JPanel();
        panelMainTitle.setPreferredSize(new Dimension(800,60));
        panelMainTitle.setLayout(new GridLayout(2,2));
        panelMainTitle.setBorder(BorderFactory.createEmptyBorder(5,25,5,20));
        panelMainTitle.setBackground(new Color(0xD4BBA3));
        panelMain.add(panelMainTitle,BorderLayout.NORTH);

        lblName = new JLabel();
        lblName.setText("Good Day " + Login.fullName+"!");
        lblName.setPreferredSize(new Dimension(200,20));
        lblName.setFont(new Font("Arial",Font.BOLD,15));
        lblName.setHorizontalTextPosition(SwingConstants.LEFT);
        panelMainTitle.add(lblName);

        lblDate = new JLabel();
        lblDate.setText("MM/DD/YYYY");
        lblDate.setPreferredSize(new Dimension(200,20));
        lblDate.setFont(new Font("Arial",Font.BOLD,15));
        panelMainTitle.add(lblDate);

        lblPosition = new JLabel();
        lblPosition.setText(Login.userPosition);
        lblPosition.setPreferredSize(new Dimension(200,20));
        lblPosition.setHorizontalTextPosition(SwingConstants.LEFT);
        lblPosition.setFont(new Font("Arial",Font.BOLD,15));
        panelMainTitle.add(lblPosition);

        lblClock = new JLabel();
        lblClock.setText("2:20:20 AM");
        lblClock.setPreferredSize(new Dimension(200,20));
        lblClock.setFont(new Font("Arial",Font.BOLD,15));
        panelMainTitle.add(lblClock);
        


        panelMainApt = new JPanel();
        panelMainApt.setPreferredSize(new Dimension(800,500));
        panelMainApt.setBackground(new Color(0xD4BBA3));
        panelMainApt.setLayout(new GridLayout(2,4,20,20));
        panelMainApt.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panelMain.add(panelMainApt,BorderLayout.CENTER);


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
        lblFooter.setText("<html><center> ?? Copyright <br> All Rights Reserved 2022<center></html>");
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
        panelMainApt.add(jButton);
    }
}
