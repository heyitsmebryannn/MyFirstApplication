package com.bryan.apartment.users;

import com.bryan.apartment.database.ConnectDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class UpdateUser extends JDialog {

    JPanel panelTitle,panelMain;
    JLabel lblTitle,lblFullName,lblContact,lblUsername,lblPassword,lblConfirmPassword,lblPosition;
    JTextField txtUsername,txtFullName,txtContact;
    JPasswordField txtPassword,txtConfirmPassword;
    JComboBox<String> cmbPosition;
    JButton btnUpdate;
    String[] position = {"Admin","Co-Admin","Manager"};
    Font font = new Font("Arial",Font.BOLD,15);

    public UpdateUser(){
        setTitle("Add User");
        setSize(400,600);
        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setUIComponents();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                new UserInfo().setVisible(true);
            }
        });
    }

    private void setUIComponents(){
        panelTitle = new JPanel();
        panelTitle.setPreferredSize(new Dimension(400,50));
        panelTitle.setBackground(new Color(210, 167, 137));
        panelTitle.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(panelTitle,BorderLayout.NORTH);

        lblTitle = new JLabel();
        lblTitle.setText("      Add User");
        lblTitle.setPreferredSize(new Dimension(200,30));
        lblTitle.setFont(new Font("Arial",Font.BOLD,20));
        lblTitle.setHorizontalTextPosition(SwingConstants.LEFT);
        lblTitle.setVerticalAlignment(SwingConstants.CENTER);
        panelTitle.add(lblTitle);

        panelMain = new JPanel();
        panelMain.setPreferredSize(new Dimension(400,560));
        panelMain.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelMain.setBorder(BorderFactory.createEmptyBorder(30,70,0,20));
        panelMain.setBackground(new Color(0xD4BBA3));
        add(panelMain,BorderLayout.CENTER);

        lblFullName = new JLabel();
        lblFullName.setText("Full Name");
        lblFullName.setFont(font);
        lblFullName.setVerticalAlignment(SwingConstants.BOTTOM);
        lblFullName.setPreferredSize(new Dimension(250,30));
        panelMain.add(lblFullName);

        txtFullName = new JTextField();
        txtFullName.setFont(font);
        txtFullName.setPreferredSize(new Dimension(250,30));
        txtFullName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                final boolean letterOnly = Character.isLetter(c) || Character.isISOControl(c) || e.getExtendedKeyCode() == KeyEvent.VK_SPACE;
                final boolean backSpace = e.getExtendedKeyCode() == KeyEvent.VK_DELETE || e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE;
                txtFullName.setEditable(letterOnly || backSpace);
            }
        });
        panelMain.add(txtFullName);

        lblContact = new JLabel();
        lblContact.setText("Contact No.");
        lblContact.setFont(font);
        lblContact.setVerticalAlignment(SwingConstants.BOTTOM);
        lblContact.setPreferredSize(new Dimension(250,30));
        panelMain.add(lblContact);

        txtContact = new JTextField();
        txtContact.setFont(font);
        txtContact.setPreferredSize(new Dimension(250,30));
        txtContact.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int contactLength = txtContact.getText().length();

                if(e.getKeyChar()>='0' && e.getKeyChar()<='9'){
                    txtContact.setEditable(contactLength<11);
                }else{
                    txtContact.setEditable(e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || e.getExtendedKeyCode() == KeyEvent.VK_DELETE);
                }
            }
        });
        panelMain.add(txtContact);

        lblUsername = new JLabel();
        lblUsername.setText("Username");
        lblUsername.setFont(font);
        lblUsername.setVerticalAlignment(SwingConstants.BOTTOM);
        lblUsername.setPreferredSize(new Dimension(250,30));
        panelMain.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setFont(font);
        txtUsername.setPreferredSize(new Dimension(250,30));
        txtUsername.setEditable(false);
        panelMain.add(txtUsername);

        lblPosition = new JLabel();
        lblPosition.setText("Position");
        lblPosition.setFont(font);
        lblPosition.setVerticalAlignment(SwingConstants.BOTTOM);
        lblPosition.setPreferredSize(new Dimension(250,30));
        panelMain.add(lblPosition);

        cmbPosition = new JComboBox<>(position);
        cmbPosition.setEditable(false);
        cmbPosition.setFont(font);
        cmbPosition.setPreferredSize(new Dimension(250,30));
        panelMain.add(cmbPosition);

        lblPassword = new JLabel();
        lblPassword.setText("Password");
        lblPassword.setFont(font);
        lblPassword.setVerticalAlignment(SwingConstants.BOTTOM);
        lblPassword.setPreferredSize(new Dimension(250,30));
        panelMain.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setFont(font);
        txtPassword.setPreferredSize(new Dimension(250,30));
        panelMain.add(txtPassword);

        lblConfirmPassword = new JLabel();
        lblConfirmPassword.setText("Confirm Password");
        lblConfirmPassword.setFont(font);
        lblConfirmPassword.setVerticalAlignment(SwingConstants.BOTTOM);
        lblConfirmPassword.setPreferredSize(new Dimension(250,30));
        panelMain.add(lblConfirmPassword);

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setFont(font);
        txtConfirmPassword.setPreferredSize(new Dimension(250,30));
        panelMain.add(txtConfirmPassword);

        btnUpdate = new JButton();
        btnUpdate.setText("Update");
        btnUpdate.setPreferredSize(new Dimension(250,30));
        btnUpdate.setFont(font);
        btnUpdate.setBackground(new Color(0xD4BBA3));
        btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnUpdate.setBackground(new Color(0xD09C6D));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnUpdate.setBackground(new Color(0xD4BBA3));
            }
        });
        btnUpdate.addActionListener(e-> updateUser());

        panelMain.add(btnUpdate);
    }
    private void updateUser(){
        String fullName = txtFullName.getText();
        String contact = txtContact.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String position = Objects.requireNonNull(cmbPosition.getSelectedItem()).toString();
        String confirmPassword = txtConfirmPassword.getText();

        if (fullName.isEmpty() || contact.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null,"Please Fill Out Empty Field!","Add User Failed",JOptionPane.WARNING_MESSAGE);

        }
        else{
            if(!password.equals(confirmPassword)){
                JOptionPane.showMessageDialog(null,"Password not match!","Add User Failed",JOptionPane.WARNING_MESSAGE);

            }
            else {
                String updateQuery = "UPDATE users SET fullName = '"+fullName+"', contact_no = '"+contact+"', password = '"+password+"', position = '"+position+"' where username = '"+username+"'";

                Connection connection = ConnectDatabase.getConnection();
                try {
                    assert connection != null;
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                    preparedStatement.execute();
                    JOptionPane.showMessageDialog(null,"User Updated Successfully!","Updated User",JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);
                    new UserInfo().setVisible(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
