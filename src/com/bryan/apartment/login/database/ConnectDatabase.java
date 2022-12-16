package com.bryan.apartment.login.database;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectDatabase {
    static String dbname = "apartmentdb";
    static String username = "root";
    static String password = "password";
    static String hostname = "localhost";
    static int port = 3306;


    public static Connection getConnection(){
        Connection connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+hostname+":"+port+"/"+dbname,username,password);
            return connection;

        }catch (Exception exception){
            exception.printStackTrace();
        }

        return null;
    }
}
