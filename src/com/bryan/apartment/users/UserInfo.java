package com.bryan.apartment.users;

import com.bryan.apartment.database.ConnectDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfo extends JDialog {
    JPanel panelTitle,panelMain,panelCategory;
    JButton btnAdd,btnUpdate,btnDelete,btnSearch;
    JTextField txtSearch;
    JPanel panelSearch,panelTable;

    JLabel lblTitle;
    JScrollPane scrollPane;
    UserListTable userListTable;


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
        panelTitle.setPreferredSize(new Dimension(800,50));
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
        btnAdd.addActionListener(e-> {
            JDialog addUser = new AddUser();
            this.dispose();
            addUser.setVisible(true);
        });
        btnUpdate = new JButton();
        btnSets(btnUpdate, "Update User");
        btnUpdate.addActionListener(e-> updateUser());
        btnDelete = new JButton();
        btnSets(btnDelete,"Delete User");
        btnDelete.addActionListener(e-> deleteUser());

        panelMain = new JPanel();
        panelMain.setPreferredSize(new Dimension(450,550));
        panelMain.setLayout(new BorderLayout());
        panelMain.setBackground(new Color(0xD4BBA3));
        add(panelMain,BorderLayout.CENTER);

        panelSearch = new JPanel();
        panelSearch.setPreferredSize(new Dimension(450,40));
        panelSearch.setLayout(new FlowLayout());
        panelSearch.setOpaque(false);
        panelSearch.setBorder(BorderFactory.createEmptyBorder(0,30,10,10));
        panelMain.add(panelSearch,BorderLayout.NORTH);

        txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(300,30));
        txtSearch.setFont(new Font("Arial",Font.BOLD,15));
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchUser();
            }
        });
        panelSearch.add(txtSearch);

        btnSearch = new JButton();
        btnSearch.setPreferredSize(new Dimension(150,30));
        btnSearch.setText("Search");
        btnSearch.setFocusable(false);
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelSearch.add(btnSearch);

        userListTable= new UserListTable();
        panelTable = new JPanel();
        panelTable.setPreferredSize(new Dimension(450,500));
        panelTable.setOpaque(false);
        panelTable.setLayout(new FlowLayout());
        panelTable.setBorder(BorderFactory.createEmptyBorder(20,30,20,10));
        panelMain.add(panelTable,BorderLayout.CENTER);


        scrollPane = new JScrollPane(userListTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(450,400));
        panelTable.add(scrollPane);


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
    private void updateUser(){
        try {
            int selectedRow = userListTable.getSelectedRow();
            System.out.println(selectedRow);
            Connection connection = ConnectDatabase.getConnection();
            String userID = userListTable.getValueAt(selectedRow,0).toString();
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users where user_ID ='"+userID+"'");
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                UpdateUser updateUser = new UpdateUser();
                updateUser.txtFullName.setText(resultSet.getString("fullName"));
                updateUser.txtContact.setText(resultSet.getString("Contact_No"));
                updateUser.txtUsername.setText(resultSet.getString("username"));
                updateUser.cmbPosition.setSelectedItem(resultSet.getString("position"));
                this.dispose();
                updateUser.setVisible(true);
            }
        }catch (ArrayIndexOutOfBoundsException exception){
            JOptionPane.showMessageDialog(null,"Please select a user you want to update!","Select user",JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void searchUser(){
        String search = txtSearch.getText();
        ShowUser showUser = new ShowUser();
        showUser.searchUser(search,userListTable);
    }
    private void deleteUser(){
        int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this user?","Confirm Delete",JOptionPane.YES_NO_OPTION);
        if(result == 0){
            try{
                int selectedRow = userListTable.getSelectedRow();
                String userID = userListTable.getValueAt(selectedRow,0).toString();
                String deleteQuery = "DELETE FROM users where user_ID = '"+userID+"'";
                Connection connection = ConnectDatabase.getConnection();
                assert connection != null;
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.execute();
                JOptionPane.showMessageDialog(null,"User Deleted Successfully!","Deleted User",JOptionPane.INFORMATION_MESSAGE);
                new ShowUser().showDataOnTable(userListTable);
            }catch (ArrayIndexOutOfBoundsException exception){
                JOptionPane.showMessageDialog(null, "Please select a user you want to Delete","Delete User",JOptionPane.WARNING_MESSAGE);
            }catch (SQLException exception){
                exception.printStackTrace();
            }
        }
    }


}
