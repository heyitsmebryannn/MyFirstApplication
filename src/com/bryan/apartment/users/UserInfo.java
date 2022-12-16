package com.bryan.apartment.users;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserInfo extends JDialog {
    JPanel panelTitle,panelMain,panelCategory;
    JButton btnAdd,btnUpdate,btnDelete,btnSearch;
    JTextField txtSearch;
    JPanel panelSearch,panelTable;

    JLabel lblTitle;

    public UserInfo(){
        setTitle("Users Information");
        setSize(800,600);
        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setUIComponents();
    }
    private void setUIComponents(){
        panelTitle = new JPanel();
        panelTitle.setPreferredSize(new Dimension(800,40));
        panelTitle.setBackground(new Color(210, 167, 137));
        panelTitle.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(panelTitle,BorderLayout.NORTH);

        lblTitle = new JLabel();
        lblTitle.setText("      User Information");
        lblTitle.setPreferredSize(new Dimension(200,30));
        lblTitle.setFont(new Font("Arial",Font.BOLD,20));
        lblTitle.setHorizontalTextPosition(SwingConstants.LEFT);
        lblTitle.setVerticalAlignment(SwingConstants.CENTER);
        panelTitle.add(lblTitle);

        panelCategory = new JPanel();
        panelCategory.setPreferredSize(new Dimension(250,550));
        panelCategory.setLayout(new FlowLayout());
        panelCategory.setBackground(new Color(0xD2AE87));
        panelCategory.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        add(panelCategory, BorderLayout.WEST);

        btnAdd = new JButton();
        btnSets(btnAdd,"Add User");
        btnUpdate = new JButton();
        btnSets(btnUpdate, "Update User");
        btnDelete = new JButton();
        btnSets(btnDelete,"Delete User");

        panelMain = new JPanel();
        panelMain.setPreferredSize(new Dimension(700,550));
        panelMain.setLayout(new BorderLayout());
        panelMain.setBackground(new Color(0xD4BBA3));
        add(panelMain,BorderLayout.CENTER);

        panelSearch = new JPanel();
        panelSearch.setPreferredSize(new Dimension(700,40));
        panelSearch.setLayout(new FlowLayout());
        panelSearch.setOpaque(true);
        panelSearch.setBorder(BorderFactory.createEmptyBorder(0,30,10,10));
        panelMain.add(panelSearch,BorderLayout.NORTH);

        txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(300,30));
        txtSearch.setFont(new Font("Arial",Font.BOLD,15));
        panelSearch.add(txtSearch);

        btnSearch = new JButton();
        btnSearch.setPreferredSize(new Dimension(150,30));
        btnSearch.setText("Search");
        btnSearch.setFocusable(false);
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelSearch.add(btnSearch);
    }

    private void btnSets(JButton btn,String text){
        btn.setPreferredSize(new Dimension(200,30));
        btn.setText(text);
        btn.setFont(new Font("Arial",Font.BOLD,15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBackground(new Color(0xD2AE87));
        btn.setFocusable(false);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(0xDA943C));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(0xD2AE87));
            }
        });
        panelCategory.add(btn);
    }
}
