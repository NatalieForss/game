package com.example.Game;

import java.util.List;

public class UserInfo {

        private int id;
        private String userName;
        private String password;
        private String mail;
        private Double rating;


    public UserInfo(int id, String userName, String password, String mail, Double rating) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.rating = rating;
    }

    public UserInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}

