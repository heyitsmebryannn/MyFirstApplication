package com.bryan.apartment.users;

import com.bryan.apartment.database.ConnectDatabase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ShowUser {
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

    protected void showDataOnTable(JTable table){
        ArrayList<UserList> userLists = getUsersList();
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();

        getModel(table);
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

    public void searchUser(String txtSearch,JTable table){
        ArrayList<UserList> userLists = new ArrayList<>();

        Connection connection = ConnectDatabase.getConnection();
        ResultSet resultSet;
        String userQuery = "SELECT * FROM users where username like '%"+txtSearch+"%' or contact_no like '%"+txtSearch+"%' or fullName like '%"+txtSearch+"%' or position like '%"+txtSearch+"%'";
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
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);
        Object[] column = new Object[4];
        for(UserList userList : userLists){
            column[0] = userList.getUserID();
            column[1] = userList.getUserFullName();
            column[2] = userList.getUsername();
            column[3] = userList.getUserPosition();
            defaultTableModel.addRow(column);

        }
    }
}
