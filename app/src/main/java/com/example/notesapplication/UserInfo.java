package com.example.notesapplication;

public class UserInfo {
    String userName;
    String email;

    public UserInfo(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
}
