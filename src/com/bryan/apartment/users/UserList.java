package com.bryan.apartment.users;

public class UserList {

    private int userID;
    private String userFullName;
    private String username;
    private String userPosition;

    public UserList(int userID,String userFullName ,String username, String userPosition) {
        this.userID = userID;
        this.userFullName = userFullName;
        this.username = username;
        this.userPosition = userPosition;
    }



    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }
}
