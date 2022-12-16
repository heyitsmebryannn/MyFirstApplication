package com.bryan.apartment.users;

import com.bryan.apartment.database.ConnectDatabase;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserInfo extends JDialog {
    JPanel panelTitle,panelMain,panelCategory;
    JButton btnAdd,btnUpdate,btnDelete,btnSearch;
    JTextField txtSearch;
    JPanel panelSearch,panelTable;

    JLabel lblTitle;
    String[] titleTable = {"User ID","Full Name","Username","Position"};
    DefaultTableModel defaultTableModel;
    JTable userTable;
    JScrollPane scrollPane;

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
        btnAdd.addActionListener(e-> new AddUser().setVisible(true));
        btnUpdate = new JButton();
        btnSets(btnUpdate, "Update User");
        btnDelete = new JButton();
        btnSets(btnDelete,"Delete User");

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
        panelSearch.add(txtSearch);

        btnSearch = new JButton();
        btnSearch.setPreferredSize(new Dimension(150,30));
        btnSearch.setText("Search");
        btnSearch.setFocusable(false);
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelSearch.add(btnSearch);

        panelTable = new JPanel();
        panelTable.setPreferredSize(new Dimension(450,500));
        panelTable.setOpaque(false);
        panelTable.setLayout(new FlowLayout());
        panelTable.setBorder(BorderFactory.createEmptyBorder(20,30,20,10));
        panelMain.add(panelTable,BorderLayout.CENTER);

        defaultTableModel = new DefaultTableModel(null,titleTable);
        userTable = new JTable(defaultTableModel){
            public boolean editCellAt(int row, int column, java.util.EventObject e){
                return false;
            }
        };
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < userTable.getColumnModel().getColumnCount(); i++) {
            userTable.getColumnModel().getColumn(i).setResizable(false);
            userTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
            userTable.getColumnModel().getColumn(i).setPreferredWidth(120);
        }
        userTable.getTableHeader().setReorderingAllowed(false);
        userTable.setFont(new Font("Arial",Font.BOLD,12));
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userTable.setRowHeight(20);

        scrollPane = new JScrollPane(userTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(450,400));
        panelTable.add(scrollPane);
        showDataOnTable();

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

    private ArrayList<UserList> getUsersList(){
        ArrayList<UserList> userLists = new ArrayList<>();
        Connection connection = ConnectDatabase.getConnection();
        ResultSet resultSet;
        String userQuery = "SELECT * FROM users";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(userQuery);
            resultSet = preparedStatement.executeQuery();
            UserList userList;
            while (resultSet.next()){
                userList = new UserList(resultSet.getInt("user_id"),resultSet.getString("fullName"),resultSet.getString("username"),resultSet.getString("position"));
                userLists.add(userList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userLists;
    }
    private void showDataOnTable(){
        ArrayList<UserList> userLists = getUsersList();
        getModel(userTable);
        Object[] column = new Object[4];
        for(UserList userList : userLists){
            column[0] = userList.getUserID();
            column[1] = userList.getUserFullName();
            column[2] = userList.getUsername();
            column[3] = userList.getUserPosition();
            defaultTableModel.addRow(column);

        }
    }
    private void getModel(JTable table){
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
    }
}
