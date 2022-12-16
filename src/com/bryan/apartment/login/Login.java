package com.bryan.apartment.login;
import com.bryan.apartment.database.ConnectDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Login extends JDialog {
    JPanel panelTitle,panelImage,panelCredentials,panelFooter;
    JLabel lblTitle,lblImage,lblUsername,lblPassword,lblPosition,lblLogin,lblFooter;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JComboBox <String> cmbPosition;
    String[] position = {"Admin","Co-Admin","Manager"};
    JButton btnLogin,btnExit;
    Font fontTitle = new Font("Arial",Font.BOLD,35);
    Font font = new Font("Arial",Font.BOLD,17);
    ImageIcon imageIcon;

    Connection connection;
    PreparedStatement preparedStatement;
    public Login(){
        setTitle("Apartment Management System");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(700,500);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setUIComponents();
    }
    private void setUIComponents(){
        panelTitle = new JPanel();
        panelTitle.setPreferredSize(new Dimension(700,100));
        panelTitle.setBackground(new Color(210, 167, 137));
        add(panelTitle, BorderLayout.NORTH);

        lblTitle = new JLabel();
        lblTitle.setText("<html><center>Apartment Management<br>System</center></html");
        lblTitle.setFont(fontTitle);
        lblTitle.setForeground(new Color(0x2B3A55));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setVerticalAlignment(SwingConstants.CENTER);
        panelTitle.add(lblTitle);

        panelImage = new JPanel();
        panelImage.setPreferredSize(new Dimension(400,400));
        panelImage.setLayout(new BorderLayout());
        add(panelImage,BorderLayout.CENTER);

        lblImage = new JLabel();
        lblImage.setPreferredSize(new Dimension(400,325));
        panelImage.add(lblImage,BorderLayout.CENTER);

        imageIcon = new ImageIcon(new ImageIcon("asset/images/apartment.jpg").getImage().getScaledInstance(lblImage.getPreferredSize().width,lblImage.getPreferredSize().height,Image.SCALE_SMOOTH));
        lblImage.setIcon(imageIcon);

        panelCredentials = new JPanel();
        panelCredentials.setPreferredSize(new Dimension(300,400));
        panelCredentials.setBackground(new Color(0xD4BBA3));
        panelCredentials.setLayout(new FlowLayout());
        add(panelCredentials,BorderLayout.EAST);

        lblLogin = new JLabel();
        lblLogin.setText("Login Form");
        lblLogin.setFont(fontTitle);
        lblLogin.setForeground(new Color(0x2B3A55));
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setPreferredSize(new Dimension(300,50));
        panelCredentials.add(lblLogin);

        lblUsername = new JLabel();
        lblUsername.setText("       Username");
        lblUsername.setPreferredSize(new Dimension(300,30));
        lblUsername.setFont(font);
        panelCredentials.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setFont(font);
        txtUsername.setPreferredSize(new Dimension(250,30));
        txtUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    login();
                }
            }
        });
        panelCredentials.add(txtUsername);

        lblPassword = new JLabel();
        lblPassword.setText("       Password");
        lblPassword.setFont(font);
        lblPassword.setPreferredSize(new Dimension(300,30));
        panelCredentials.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setFont(font);
        txtPassword.setPreferredSize(new Dimension(250,30));
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    login();
                }
            }
        });
        panelCredentials.add(txtPassword);

        lblPosition = new JLabel();
        lblPosition.setText("       Position");
        lblPosition.setPreferredSize(new Dimension(300,30));
        lblPosition.setFont(font);
        panelCredentials.add(lblPosition);

        cmbPosition = new JComboBox<>(position);
        cmbPosition.setFont(font);
        cmbPosition.setFocusable(false);
        cmbPosition.setPreferredSize(new Dimension(250,30));
        cmbPosition.setEditable(false);
        panelCredentials.add(cmbPosition);
        
        btnLogin = new JButton();
        btnLogin.setText("Login");
        btnLogin.setHorizontalTextPosition(SwingConstants.CENTER);
        btnLogin.setPreferredSize(new Dimension(120,30));
        btnLogin.setFont(font);
        btnLogin.setFocusable(false);
        btnLogin.addActionListener(e -> login());
        panelCredentials.add(btnLogin);

        btnExit = new JButton();
        btnExit.setText("Exit");
        btnExit.setHorizontalTextPosition(SwingConstants.CENTER);
        btnExit.setPreferredSize(new Dimension(120,30));
        btnExit.setFont(font);
        btnExit.setFocusable(false);
        btnExit.addActionListener(e -> System.exit(0));
        panelCredentials.add(btnExit);

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

    private void login(){
        if(txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill out the empty field","Login Failed",JOptionPane.ERROR_MESSAGE);
        }
        else{
            connection = ConnectDatabase.getConnection();
            ResultSet resultSet;
            try{
                String username = txtUsername.getText();
                String password = txtPassword.getText();
                String position = Objects.requireNonNull(cmbPosition.getSelectedItem()).toString();
                String loginQuery = "SELECT * from users where BINARY username = '"+username+"' and BINARY password = '"+password+"' and position = '"+position+"'";
                preparedStatement = connection.prepareStatement(loginQuery);
                resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    JOptionPane.showMessageDialog(null,"Login Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    new MainFrame().setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Username or Password Incorrect","Login Failed",JOptionPane.ERROR_MESSAGE);
                    txtUsername.grabFocus();
                    txtPassword.setText("");
                    txtUsername.setText("");
                }

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

}
